package com.suisrc.weixin.test;

import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.open.intercept.WxOAuth2;
import com.suisrc.weixin.core.media.ArticlesMedia;
import com.suisrc.weixin.core.media.MediaInfo;
import com.suisrc.weixin.core.media.MusicMedia;
import com.suisrc.weixin.core.msg.r.ReplyMusicMessage;
import com.suisrc.weixin.core.msg.r.ReplyNewsMessage;
import com.suisrc.weixin.core.msg.r.ReplyVideoMessage;
import com.suisrc.weixin.core.msg.rkf.ReplyNewsMessage4Json;

/**
 * 接口测试
 * @author Y13
 *
 */
@Path("test")
public class TApi {
	
	@GET
	@Path("test1")
	@Produces(MediaType.TEXT_PLAIN)
	@WxOAuth2
	public String test1() {
		return "test1";
	}
	
	@GET
	@Path("test2")
	@Produces(MediaType.APPLICATION_JSON)
	public ReplyNewsMessage test2() {
		ReplyNewsMessage reply = new ReplyNewsMessage4Json();
		reply.setToUserName("xxx");
		reply.setFromUserName("ssss");

		ArticlesMedia media = new ArticlesMedia();
		media.setDescription("hello");
		media.setTitle("word");
		reply.addArticles(media);
		
		media = new ArticlesMedia();
		media.setDescription("hello2");
		media.setTitle("word3");
		reply.addArticles(media);
		
		return reply;
	}
	
	@GET
	@Path("test3")
	@Produces(MediaType.APPLICATION_JSON)
	public ReplyMusicMessage test3() {
		ReplyMusicMessage reply = new ReplyMusicMessage();
		MusicMedia media = new MusicMedia();
		media.setMediaId("123");
		reply.setMusic(media);
		return reply;
	}
	
	@GET
	@Path("test4")
	@Produces(MediaType.APPLICATION_JSON)
	public ReplyVideoMessage test4() {
		ReplyVideoMessage reply = new ReplyVideoMessage();
		MediaInfo media = new MediaInfo();
		media.setMediaId("123");
		reply.setVideo(media);
		return reply;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception {
//		TApi api = new TApi();
//		ObjectMapper mapper = new ObjectMapper();
//		String json = mapper.writeValueAsString(api.test2()); // java 2 json
//		System.out.println(json);
//		String xml = MessageFactory.beanToXml(api.test2());
//		System.out.println(xml);

		Properties props = new Properties();
		props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		props.setProperty("java.naming.provider.url", "localhost:1099");
		InitialContext ctx = new InitialContext(props);
		Map map = (Map) ctx.lookup("java://ee/name");
		map.put("s", "hello");
	}
	
}
