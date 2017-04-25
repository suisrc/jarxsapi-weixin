package com.qq.weinxi.mp;

import java.io.IOException;
import java.util.Arrays;

import com.qq.weixin.mp.MpServerActivator;
import com.qq.weixin.mp.MpWxConsts;
import com.suisrc.jaxrsapi.core.runtime.NSCFactory;
import com.suisrc.weixin.core.api.AccessTokenRest;
import com.suisrc.weixin.core.api.WxServerInfoRest;
import com.suisrc.weixin.core.bean.ServerIpResult;
import com.suisrc.weixin.core.message.ErrCodeMsg;

/**
 * 
 * @author Y13
 *
 */
public class TMain_T {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException { 
		System.setProperty(MpWxConsts.KEY_APP_ID, "wxb252278612fc8647");
		System.setProperty(MpWxConsts.KEY_APP_SECRET, "824edbf6928b623ef62fa9754041de33");
		System.setProperty(MpWxConsts.KEY_TOKEN, "testtoken");
		System.setProperty(MpWxConsts.KEY_ENCODING_AES_KEY, "oF1gNW2AvZBrQWO4mHNQ0GQ5fM8ls7dUFTjMT1G2VCb");
		// 初始化工厂
		NSCFactory.build(MpServerActivator.class);
		// 设定AccessToken远程访问
		MpServerActivator serverActivator = NSCFactory.get(MpServerActivator.class);
		serverActivator.setAdapter(AccessTokenRest.class, NSCFactory.get(AccessTokenRest.class));
		// 获取远程访问接口
		WxServerInfoRest rest = NSCFactory.get(WxServerInfoRest.class);
//		// 执行访问
		ServerIpResult content = rest.getCallbackIp(null);
		if( content.getErrcode() != null ) {
			System.out.println(ErrCodeMsg.getErrCodeMsg(content.getErrcode(), content.getErrmsg()));
		} else {
			Arrays.asList(content.getIpList()).forEach(System.out::println);
		}
	}
	
	

}
