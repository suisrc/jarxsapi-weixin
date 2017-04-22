# jaxrsapi

  这是一个神奇的框架，帮助系统之间通过restful通信提供遍历。
### 有什么作用？
  项目来自wildfly-swarm中的jarsapi组件，通过集群或服务器之间直接通过restapi接口通信模式，但是处
 于其目前实验性和对非wildfly框架的不友好以及数据访问时候，对资源的浪费，所以我打算自己写一个。
### 有什么优势？
  项目的起因是，需要写一个腾讯公众号api的java版，我首先先到了aliyun的api,我一起用过，感觉很好，
 但是当我仔细研究其工作原理后，我不仅要问，这是我们需要的代码结构吗？生硬且没有共通性可言，
 在撰写java版api接口的时候，我们不得不实时注意我们需要访问的url和request参数以及返回的结果集。
 我们需要的是一个程序员只关注接口，无需考虑客户端和服务器直接如何通信的，甚至，我们需要考虑的
 只是接口的描述，把url版的api描述修改为java版的。而这个也是我提出的基本准则。
  在我们java开发过程中基本上都会包restful api单独构建一个工程，这样，通过该框架很容易把现有的
 api进行扩展，需要restful api符合javaaee7.0标准就可以了。
### 如何使用和扩展？
  这里我以微信公众号进行说明。
  1.定义远程服务器激活器：
  
	@Named("com.qq.weixin.api")
	@ApplicationScoped
 	public class ServerActivator implements ApiActivator {
 	}
  
  定义一个服务器激活器，实现API接口适配器，详情请看weinxin-mp中的代码。
  2.构建java api
  
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
    public class UserInfoParam {
	
	@QueryParam("access_token")
	@SystemValue(WxConsts.ACCESS_TOKEN) 
	private String accessToken;
	
	@QueryParam("openid")
	private String openid;
	
	@QueryParam("lang") 
	private String lang;
     }
 
 构建的接口是不需要实现的，只需要在ServerActivator中的getClasses方法中指定就可以了。框架会实现接口。完成调用，同时我们可以直接
 使用 @Inject对接口注入，得到系统实现的实体内容。
 
 当没有支持javaee7.0的系统使用该框架，应该如何做，这里我们提供NSCFactory（Native Service Client Factory）工厂，可以使用其初始化
通信系统，然后通过UserRest userRest = NSCFactory.get(UserRest.class);的方式获取相应的接口实现。
 ## 总结
  我希望提出一个通用的符合javaee7.0的restful api远程调用框架，把所有的通信和对象转换的业务都封装起来，程序员只需要关注业务需求的产品。
 为程序员开发，再在巨人的肩上看远方。还有我是jboss社区的忠实粉丝，目前在研究和使用wildfly-swarm框架，为公司节省人力成本。
 
 ## 框架使用开源清单
   resteasy(网络通信) 
   reflections(反射控制)
   jandex(代码自分析，比通过反射方式访问代码的结构和注解更加方便)
   fasterxml(结果集转换为对象)
   
