package com.suisrc.jaxrsapi.core;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;

/**
 * 扩展接口以创建基于CDI的JAXRS客户端。
 * @author Y13
 * @see org.wildfly.swarm.client.jaxrs.ServiceClient
 * @param <T>
 */
public interface ServiceClient {
	
	//------------------------------------初始化-------------------------------------//
	void initialized();
	
	//------------------------------------配置信息------------------------------------//
	/**
	 * 获取激活器,激活器存放远程服务器信息
	 * @return
	 */
	ApiActivator getActivator();
	
	/**
	 * 重置激活器
	 * @param activator
	 */
	void setActivator( ApiActivator activator);
	
	/**
	 * 通过适配器获取数据
	 * @param key
	 * @return
	 */
	default <T> T getAdapter(String key) {
		return getActivator().getAdapter(key);
	}
	
	/**
	 * 通过适配器获取数据
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	default <T> T getAdapter( Class<T> type ) {
		if( type == ApiActivator.class ) {
			return (T)getActivator();
		}
		return getActivator().getAdapter(type);
	}
	
	//------------------------------------执行内容--------------------------------//
	default <U> void exec(Supplier<U> restMethod, Consumer<U> handler, Consumer<Throwable> exceptionHandler)
			throws Exception {
		chainableExec(restMethod, exceptionHandler).thenAccept(handler).exceptionally(t -> {
			exceptionHandler.accept(t);
			return null;
		});
	}

	default <U> CompletableFuture<U> chainableExec(Supplier<U> restMethod, Consumer<Throwable> exceptionHandler)
			throws Exception {
		return CompletableFuture.supplyAsync(restMethod, executorService()).exceptionally(t -> {
			exceptionHandler.accept(t);
			return null;
		});
	}

	default ManagedExecutorService executorService() throws Exception {
		InitialContext ctx = new InitialContext();
		return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
	}
}
