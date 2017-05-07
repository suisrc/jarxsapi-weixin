# jaxrsapi-weixin
>该项目是使用远程restful规范远程访问框架jaxrsapi构建的微信API的java版
## 项目作用
>对jaxrsapi框架的可行性的一种验证
## 项目优势
>腾讯公众号api是不提供java版，我开始想借鉴aliyun的api的java版,我表示用过，感觉很好，很简单。
但是当我通过源代码仔细研究其工作原理后，我不仅要问，这是我们需要的代码结构吗？为每一个远程
接口需要写很多的通信实现，然后处理参数，解析结果，反反复复做着重复的事情。有意思的是aliyun
把httpclient的也已经了自己的框架中，这样就不需要第三方jar包，个人感觉没有这个必要。<br>
常规思路在撰写java版api接口的时候，我们不得不实时考虑我们需要访问的url和request参数以及返回的
结果集。而我的思路是，定义号接口，告诉系统接口是那台服务器的，然后就没有程序员的任务了。
至于系统如何通过接口访问远程服务器获取数据，那是系统的任务，也是框架的任务，跟业务程序员已经
没有关系了。这样我们可以把网页版的api和java接口一一对应上，就算完成了所有事情了。
## 使用和扩展
这里我以微信公众号进行说明。<br>
1.定义远程服务器激活器：<br>

```java
	@Named("com.qq.weixin.api")
	@ApplicationScoped
	public class ServerActivator implements ApiActivator {
	}
```
2.构建java api

```java

	/**
	* 接口定义
	*/
	@Path("user")
	public interface UserRest {
		/**
		* http请求方式: GET https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN 
		*/
		@GET
		@Path("info")
		@Produces(MediaType.APPLICATION_JSON)
		String getUserInfo(@BeanParam UserInfoParam userInfo);
	}
```
3.参数定义
```java

	/**
	* 参数定义
	*/
	public class UserInfoParam {
	
		@QueryParam("access_token")
		@SystemValue(WxConsts.ACCESS_TOKEN) 
		private String accessToken;
	
		@QueryParam("openid")
		private String openid;
	
		@QueryParam("lang") 
		private String lang;
	}
```

构建的接口是不需要实现的，只需要在ServerActivator中的getClasses方法中指定就可以了。框架会实现接口。

## 总结
>微信部分我希望以一种全新的方式，完成对微信API的构建，也是对未来restful在服务器之间通信提供一种思路和点子。
