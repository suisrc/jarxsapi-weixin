package com.qq.weixin.pay;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;

import com.google.common.collect.Sets;
import com.qq.weixin.pay.api.MmpaymkttransfersRest;
import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.weixin.core.AbstractWeixinActivator;
import com.suisrc.weixin.core.WxConfig;
import com.suisrc.weixin.core.bean.WxAccessToken;
import com.suisrc.weixin.core.crypto.WxCrypto;

/**
 * 程序入口配置
 * 
 * @author Y13 https://api.mch.weixin.qq.com
 */
@Named(PayWxConsts.NAMED)
@ApplicationScoped
public class PayServerActivator extends AbstractWeixinActivator implements ApiActivator, WxConfig {

	/**
	 * 暴露给外部远程访问接口 这里保护了系统访问的两个接口AccessToken接口 如何企业号和公众号同时使用的时候，接口可能出现问题，请注意
	 */
	public Set<Class<?>> getClasses() {
		return Sets.newHashSet(MmpaymkttransfersRest.class);

	}
	
	/**
	 * 证书的位置
	 */
	private String keyStorePath;
	
	/**
	 * 商铺ID
	 */
	private String mchId;
	
	/**
	 * 商铺密钥
	 */
	private String mchKey;
	
	/**
	 * 商铺名称
	 */
	private String sendName;
	
	/**
	 * 客户端IP
	 */
	private String clientIp;

	/**
	 * 构造后被系统调用 进行内容初始化
	 */
	@PostConstruct
	@Override
	public void init() {
		String value =  System.getProperty(PayWxConsts.KEY_APP_ID);
		if( value != null ) { appId = value; }
		baseUrl = System.getProperty(PayWxConsts.BASE_URL, "https://api.mch.weixin.qq.com");
		// 附加
		keyStorePath = System.getProperty(PayWxConsts.KEY_API_CLIENT_CERT_P12_PATH, "");
		mchId = System.getProperty(PayWxConsts.KEY_MCH_ID);
		mchKey = System.getProperty(PayWxConsts.KEY_MCH_KEY);
		sendName = System.getProperty(PayWxConsts.KEY_SEND_NAME);
		clientIp = System.getProperty(PayWxConsts.KEY_CLIENT_IP, "");
		if( clientIp.isEmpty() ) {
			try { clientIp = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) { e.printStackTrace(); }
		}
		// 构建缓存线程池
		executor = Executors.newFixedThreadPool(Integer.valueOf(System.getProperty(PayWxConsts.KEY_ACTIVATOR_THREAD_COUNT, "10")));

		super.init();
	}
	
	//------------------------access token---------------------------//
	/**
	 * 支付平台不需要access token
	 */
	@Override
	protected void initAccessToken() {
	}
	
	@Override
	public String getAccessToken() {
		// do nothing
		return null;
	}
	
	@Override
	public void clearAccessToken() {
		// do nothing
	}
	//------------------------access token---------------------------//

	/**
	 * 初始化远程访问的客户端
	 */
	protected ClientBuilder createClientBuilder() {
		ClientBuilder builder = super.createClientBuilder();// 配置网络通信内容
		File keyStoreFile = new File(getKeyStorePath());
		if( !keyStoreFile.exists() ) {
			// 商铺证书不存在
			throw new RuntimeException("商户证书无法找到，请确认：" + keyStoreFile.getAbsolutePath() );
		}
		FileInputStream instream = null;
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			instream = new FileInputStream(keyStoreFile);
			keyStore.load(instream, getMchId().toCharArray());
			builder.keyStore(keyStore, getMchId());
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			throw new RuntimeException(e); // 读取证书的时候发生异常
		} finally {
			try { instream.close();
			} catch (IOException e) { }
		}
		instream = null;
		return builder;
	}

	/**
	 * 获取商品ID
	 * @return
	 */
	public String getMchId() {
		return mchId;
	}
	
	/**
	 * 获取商铺名称
	 * @return
	 */
	public String getSendName() {
		return sendName;
	}
	
	/**
	 * 获取客户端IP
	 * @return
	 */
	public String getClientIp() {
		return clientIp;
	}
	
	/**
	 * 获取商铺密钥
	 * @return
	 */
	public String getMchKey() {
		return mchKey;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getAdapter(String key) {
		switch (key) {
		case PayWxConsts.MCH_ID:
			return (T) getMchId();
		case PayWxConsts.MCH_KEY:
			return (T) getMchKey();
		case PayWxConsts.SEND_NAME:
			return (T) getSendName();
		case PayWxConsts.CLIENT_IP:
			return (T) getClientIp();
		case PayWxConsts.AUTO_MCH_BILLNO:
			return (T) getAutoMchBillno();
		case PayWxConsts.AUTO_RANDOM_STR:
			return (T) WxCrypto.genRandomStr();
		case PayWxConsts.BASE_URL:
			return (T) getBaseUrl();
		default: 
			return super.getAdapter(key);
		}
	}
	
	/**
	 * 获取证书位置
	 * @return
	 */
	protected String getKeyStorePath() {
		return keyStorePath;
	}

	@Override
	protected WxAccessToken getWxAccessToken() {
		return null;
	}
	
//	@Override
//	protected String getTempFileName() {
//		return "auto_mch_id.obj";
//	}
	
	//-------------------------------------auto mch billno--------------------------//
	
	/**
	 * 17个字节长度
	 */
	private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	private String mchIdTemp = null;
	/**
	 * 获取订单编号
	 * 必须记性同步获取
	 * @return
	 */
	public synchronized String getAutoMchBillno() {
		try { Thread.sleep(1);
		} catch (InterruptedException e) { 
		}
		if( mchIdTemp == null ) {
			mchIdTemp = mchId.length() <= 11 ? mchId : mchId.substring(mchId.length() - 11);
		}
		return mchIdTemp + LocalDateTime.now().format(dtf);
	}
	
}
