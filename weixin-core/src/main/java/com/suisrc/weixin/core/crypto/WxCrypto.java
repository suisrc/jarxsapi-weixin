/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 *
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 * <p>
 * 针对org.apache.commons.codec.binary.Base64，
 * 需要导入架包commons-codec-1.9（或commons-codec-1.8等其他版本）
 * 官方下载地址：http://commons.apache.org/proper/commons-codec/download_codec.cgi
 */
package com.suisrc.weixin.core.crypto;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 数据加密和解密
 * @author Y13
 *
 */
public class WxCrypto {

	private static final Base64 base64 = new Base64();
	private static final Charset CHARSET = Charset.forName("utf-8");

	protected byte[] aesKey;
	protected String token;
	protected String appidOrCorpid;

	public WxCrypto() {
		super();
	}

	/**
	 * 构造函数
	 * @param token 公众平台上，开发者设置的token
	 * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
	 * @param appidOrCorpid 公众平台appid/corpid
	 */
	public WxCrypto(String token, String encodingAesKey, String appidOrCorpid) {
		this.token = token;
		this.appidOrCorpid = appidOrCorpid;
		this.aesKey = Base64.decodeBase64(encodingAesKey + "=");
	}
	
	public String getToken() {
		return token;
	}
	
	public String getAppidOrCorpid() {
		return appidOrCorpid;
	}
	
	public byte[] getAesKey() {
		return aesKey;
	}

	/**
	 * 对明文进行加密.
	 * @param plainText 需要加密的明文
	 * @return 加密后base64编码的字符串
	 */
	public String encrypt(String plainText) {
		// 产生随机数
		String randomStr = genRandomStr();
		
		ByteGroup byteCollector = new ByteGroup();
		byte[] randomStringBytes = randomStr.getBytes(CHARSET);
		byte[] plainTextBytes = plainText.getBytes(CHARSET);
		byte[] bytesOfSizeInNetworkOrder = number2BytesInNetworkOrder(plainTextBytes.length);
		byte[] appIdBytes = this.appidOrCorpid.getBytes(CHARSET);

		// randomStr + networkBytesOrder + text + appid
		byteCollector.addBytes(randomStringBytes);
		byteCollector.addBytes(bytesOfSizeInNetworkOrder);
		byteCollector.addBytes(plainTextBytes);
		byteCollector.addBytes(appIdBytes);

		// ... + pad: 使用自定义的填充方式对明文进行补位填充
		byte[] padBytes = PKCS7Encoder.encode(byteCollector.size());
		byteCollector.addBytes(padBytes);

		// 获得最终的字节流, 未加密
		byte[] unencrypted = byteCollector.toBytes();

		try {
			// 设置加密模式为AES的CBC模式
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keySpec = new SecretKeySpec(this.aesKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(this.aesKey, 0, 16);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
			// 加密
			byte[] encrypted = cipher.doFinal(unencrypted);
			// 使用BASE64对加密后的字符串进行编码
			String base64Encrypted = base64.encodeToString(encrypted);

			return base64Encrypted;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对密文进行解密.
	 *
	 * @param cipherText 需要解密的密文
	 * @return 解密得到的明文
	 */
	public String decrypt(String cipherText) {
		byte[] original;
		try {
			// 设置解密模式为AES的CBC模式
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec key_spec = new SecretKeySpec(this.aesKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(this.aesKey, 0, 16));
			cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);
			// 使用BASE64对密文进行解码
			byte[] encrypted = Base64.decodeBase64(cipherText);
			// 解密
			original = cipher.doFinal(encrypted);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		String xmlContent, from_appid;
		try {
			// 去除补位字符
			byte[] bytes = PKCS7Encoder.decode(original);

			// 分离16位随机字符串,网络字节序和AppId
			byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

			int xmlLength = bytesNetworkOrder2Number(networkOrder);

			xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
			from_appid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length), CHARSET);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// appid不相同的情况
		if (!from_appid.equals(this.appidOrCorpid)) {
			throw new RuntimeException("AppID不正确");
		}
		return xmlContent;
	}
	
	/**
	 * 将一个数字转换成生成4个字节的网络字节序bytes数组
	 *
	 * @param number
	 */
	private static byte[] number2BytesInNetworkOrder(int number) {
		byte[] orderBytes = new byte[4];
		orderBytes[3] = (byte) (number & 0xFF);
		orderBytes[2] = (byte) (number >> 8 & 0xFF);
		orderBytes[1] = (byte) (number >> 16 & 0xFF);
		orderBytes[0] = (byte) (number >> 24 & 0xFF);
		return orderBytes;
	}

	/**
	 * 4个字节的网络字节序bytes数组还原成一个数字
	 *
	 * @param bytesInNetworkOrder
	 */
	private static int bytesNetworkOrder2Number(byte[] bytesInNetworkOrder) {
		int sourceNumber = 0;
		for (int i = 0; i < 4; i++) {
			sourceNumber <<= 8;
			sourceNumber |= bytesInNetworkOrder[i] & 0xff;
		}
		return sourceNumber;
	}

	/**
	 * 随机生成16位字符串
	 */
	public static String genRandomStr() {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 16; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * 排序,串接arr参数，生成sha1 digest
	 */
	public static String genSHA1(String... arr) {
		StringBuilder sb = new StringBuilder();
		Arrays.asList(arr).stream().sorted().forEach(sb::append);
		return DigestUtils.sha1Hex(sb.toString());
	}

	/**
	 * 排序，加密, 用于红包数据签名
	 */
	public static String genMD5(TreeMap<String, String> datas, String signKey) {
		StringBuilder sb = new StringBuilder();
		for( Entry<String, String> entry : datas.entrySet() ) {
			sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
		}
		sb.append("key=").append(signKey);
		return DigestUtils.md5Hex(sb.toString()).toUpperCase();
	}
}
