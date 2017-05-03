package org.jboss.resteasy.client.jaxrs;

import java.io.IOException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;
import org.jboss.resteasy.client.jaxrs.engines.PassthroughTrustManager;

/**
 * http client builder 初始化工具类，功能和HttpClientBuilder43类似
 * @see org.jboss.resteasy.client.jaxrs.HttpClientBuilder43
 * @see org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
 * @author Y13
 *
 */
public class HttpClientBuilder43_s {

	public static ClientHttpEngineProxy initDefaultEngine43(ResteasyClientBuilder that) {
		HostnameVerifier verifier = null;
		if (that.verifier != null) {
			verifier = new ResteasyClientBuilder.VerifierWrapper(that.verifier);
		} else {
			switch (that.policy) {
			case ANY:
				verifier = new NoopHostnameVerifier();
				break;
			case WILDCARD:
				verifier = new DefaultHostnameVerifier();
				break;
			case STRICT:
				verifier = new DefaultHostnameVerifier();
				break;
			}
		}
		try {
			SSLConnectionSocketFactory sslsf = null;
			SSLContext theContext = that.sslContext;
			if (that.disableTrustManager) {
				theContext = SSLContext.getInstance("SSL");
				theContext.init(null, new TrustManager[] { new PassthroughTrustManager() }, new SecureRandom());
				verifier = new NoopHostnameVerifier();
				sslsf = new SSLConnectionSocketFactory(theContext, verifier);
			} else if (theContext != null) {
				sslsf = new SSLConnectionSocketFactory(theContext, verifier) {
					@Override
					protected void prepareSocket(SSLSocket socket) throws IOException {
						that.prepareSocketForSni(socket);
					}
				};
			} else if (that.clientKeyStore != null || that.truststore != null) {
				SSLContext ctx = SSLContexts.custom().useProtocol(SSLConnectionSocketFactory.TLS).setSecureRandom(null)
						.loadKeyMaterial(that.clientKeyStore, that.clientPrivateKeyPassword != null ? that.clientPrivateKeyPassword.toCharArray() : null)
						.loadTrustMaterial(that.truststore, TrustSelfSignedStrategy.INSTANCE).build();
				sslsf = new SSLConnectionSocketFactory(ctx, verifier) {
					@Override
					protected void prepareSocket(SSLSocket socket) throws IOException {
						that.prepareSocketForSni(socket);
					}
				};
			} else {
				final SSLContext tlsContext = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
				tlsContext.init(null, null, null);
				sslsf = new SSLConnectionSocketFactory(tlsContext, verifier);
			}

			final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();

//			HttpClientConnectionManager cm = null;
//			if (that.connectionPoolSize > 0) {
//				@SuppressWarnings("resource")
//				PoolingHttpClientConnectionManager tcm = new PoolingHttpClientConnectionManager(registry, null, null, null, that.connectionTTL, that.connectionTTLUnit);
//				tcm.setMaxTotal(that.connectionPoolSize);
//				if (that.maxPooledPerRoute == 0) {
//					that.maxPooledPerRoute = that.connectionPoolSize;
//				}
//				tcm.setDefaultMaxPerRoute(that.maxPooledPerRoute);
//				cm = tcm;
//
//			}
//			else {
//				cm = new BasicHttpClientConnectionManager(registry);
//			}
			if (that.connectionPoolSize > 0 && that.maxPooledPerRoute == 0) {
				that.maxPooledPerRoute = that.connectionPoolSize;
			}

			RequestConfig.Builder rcBuilder = RequestConfig.custom();
			if (that.socketTimeout > -1) {
				rcBuilder.setSocketTimeout((int) that.socketTimeoutUnits.toMillis(that.socketTimeout));
			}
			if (that.establishConnectionTimeout > -1) {
				rcBuilder.setConnectTimeout((int) that.establishConnectionTimeoutUnits.toMillis(that.establishConnectionTimeout));
			}
			if (that.connectionCheckoutTimeoutMs > -1) {
				rcBuilder.setConnectionRequestTimeout(that.connectionCheckoutTimeoutMs);
			}

			ClientHttpEngineProxy proxy = new ClientHttpEngineProxy();
			proxy.setRegistry_s(registry);
			proxy.setConnectionPoolSize_s(that.connectionPoolSize);
			proxy.setConnectionTTL_s(that.connectionTTL);
			proxy.setConnectionTTLUnit_s(that.connectionTTLUnit);
			proxy.setMaxPooledPerRoute_s(that.maxPooledPerRoute);
			proxy.setRequestConfigBuilder_s(rcBuilder);
//			proxy.setConnManager_s(cm);
			proxy.setProxy_s(that.defaultProxy);
			proxy.setResponseBufferSize_s(that.responseBufferSize);
			proxy.setHostnameVerifier_s(verifier);
			// theContext may be null. We can't really support this with Apache Client.
			proxy.setSslContext_s(theContext);
			return proxy;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
