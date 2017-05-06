package com.qq.weixin.qy.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.suisrc.jaxrsapi.core.annotation.RemoteApi;
import com.suisrc.weixin.core.bean.WxAccessToken;

/**
 * 主动调用是最基本的连接模式，当你的应用调用企业号时，需使用Https协议、Json数据格式、UTF8编码，访问域名为https://qyapi.weixin.qq.com，数据包不需要加密。
 * 在每次主动调用企业号接口时需要带上AccessToken参数。AccessToken参数由CorpID和Secret换取。
 * CorpID是企业号的标识，每个企业号拥有一个唯一的CorpID；Secret是管理组凭证密钥。
 * 系统管理员可通过管理端的权限管理功能创建管理组，分配管理组对应用、通讯录的访问权限。完成后，管理组即可获得唯一的secret。
 * 系统管理员可通过权限管理查看所有管理组的secret，其他管理员可通过设置中的开发者凭据查看。
 * 当企业应用调用企业号接口时，企业号后台为根据此次访问的AccessToken,校验访问的合法性以及所对应的管理组的管理权限以返回相应的结果。
 * 注：你应该审慎配置管理组的权限，够用即好，权限过大会增加误操作可能性及信息安全隐患。
 * 
 * AccessToken是企业号的全局唯一票据，调用接口时需携带AccessToken。
 * AccessToken需要用CorpID和Secret来换取，不同的Secret会返回不同的AccessToken。正常情况下AccessToken有效期为7200秒，有效期内重复获取返回相同结果。
 * access_token至少保留512字节的存储空间。
 * 当你获取到AccessToken时，你的应用就可以成功调用企业号后台所提供的各种接口以管理或访问企业号后台的资源或给企业号成员发消息。
 * 
 * 为了防止企业应用的程序错误而引发企业号服务器负载异常，默认情况下，每个企业号调用接口都有一定的频率限制，当超过此限制时，调用对应接口会收到相应错误码。
 * 以下是当前默认的频率限制，企业号后台可能会根据运营情况调整此阈值：
 * 
 * 基础频率
 * 每企业调用单个cgi/api不可超过1000次/分，30000次/小时
 * 每企业调用接口的并发数不可超过300
 * 企业每ip调用接口不可超过20000次/分，600000次/小时
 * 第三方应用提供商每ip调用接口不可超过40000次/分，1200000次/小时
 * 
 * 发消息频率
 * 每企业不可超过帐号上限数*30人次/天，发消息频率不计入基础频率
 *  * 
 * 创建帐号频率
 * 每企业创建帐号数不可超过帐号上限数*3/月
 * 
 * 创建应用频率
 * 每企业最大应用数限制为30个，创建应用次数不可超过30*3/月
 * 
 * 创建群聊频率
 * 每个企业号成员（群的创建者）创建群聊个数不可超过500/天
 * 
 * *以上所有频率，按天拦截则被屏蔽一天（自然天），按月拦截则屏蔽一个月（30天，非自然月），按分钟拦截则被屏蔽60秒，按小时拦截则被屏蔽60分钟。
 * 注：身份验证接口的"根据code获取成员信息/user/getuserinfo"，若code合法则不受上述频率限制。
 * @author Y13
 *
 */
@RemoteApi
public interface AccessTokenRest {

	/**
	 * https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=id&corpsecret=secrect
	 * @param appid       是必须, 企业Id
	 * @param secret      是必须, 管理组的凭证密钥
	 * @return
	 */
	@GET
	@Path("cgi-bin/gettoken")
	@Produces(MediaType.APPLICATION_JSON)
	WxAccessToken getToken(@QueryParam("corpid") String corpid, @QueryParam("corpsecret") String secret);
}
