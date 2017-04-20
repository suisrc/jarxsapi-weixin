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
public interface ServiceClient<T> {

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
