package com.suisrc.weixin.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	public String test1() {
		return "test1";
	}

}
