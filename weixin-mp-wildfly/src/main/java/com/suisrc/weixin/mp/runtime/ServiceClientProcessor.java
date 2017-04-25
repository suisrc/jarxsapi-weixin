package com.suisrc.weixin.mp.runtime;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
import org.jboss.jandex.Index;
import org.jboss.jandex.Indexer;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.spi.api.ArchiveMetadataProcessor;

import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.jaxrsapi.core.runtime.ClientServiceFactory;

import javassist.ClassPool;
import javassist.CtClass;

/**
 * 初始化构建需要加载的扩展对象
 * @author Y13
 *
 */
@ApplicationScoped
public class ServiceClientProcessor implements ArchiveMetadataProcessor {

	@Override
	public void processArchive(Archive<?> archive, Index index) throws Exception {
		ClassPool ctPool = ClassPool.getDefault();
		List<ClassInfo> activatorClasses = index.getKnownDirectImplementors((DotName.createSimple(ApiActivator.class.getName())));
		JAXRSArchive jaxrsArchive = archive.as(JAXRSArchive.class);
		for( ClassInfo activatorClass : activatorClasses ) {
			ApiActivator activator = (ApiActivator) Class.forName(activatorClass.name().toString()).newInstance();
			for( Class<?> apiClass : activator.getClasses() ) {
				ClassInfo info = index.getClassByName(DotName.createSimple(apiClass.getName()));
				// 生成api代理实体
				String name = apiClass.getCanonicalName() + "_$$jaxrsapi";
				CtClass ctClass = ClientServiceFactory.createImpl(activator, index, ctPool, name, info);
				String path = "WEB-INF/classes/" + name.replace('.', '/') + ".class";
				jaxrsArchive.add(new ByteArrayAsset(ctClass.toBytecode()), path);
				ctClass.freeze(); // 释放
			}
		}
	}
	
	/**
	 * 当系统无法加载ServiceClientProcessor的时候, 手动调用
	 * @param archive
	 * @throws Exception
	 */
	public static void processArchive(Archive<?> archive) throws Exception {
		Indexer indexer = new Indexer();
		Map<ArchivePath, Node> c = archive.getContent();
		try {
			for (Map.Entry<ArchivePath, Node> each : c.entrySet()) {
				if (each.getKey().get().endsWith(".class")) {
					indexer.index(each.getValue().getAsset().openStream());
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Index index = indexer.complete();
		new ServiceClientProcessor().processArchive(archive, index);
	}

}
