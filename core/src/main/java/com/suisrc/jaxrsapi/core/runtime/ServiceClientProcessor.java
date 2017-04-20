package com.suisrc.jaxrsapi.core.runtime;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
import org.jboss.jandex.Index;

import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.jaxrsapi.core.MetadataProcessor;

/**
 * @author Y13
 */
@ApplicationScoped
public class ServiceClientProcessor implements MetadataProcessor {
	
	@Override
	public void processMetadata(Index index) {
		// 查找接口领事类型
		List<ClassInfo> serviceServer = index.getKnownDirectImplementors((DotName.createSimple(ApiActivator.class.getName())));
		serviceServer.forEach(info -> {
			try {
				Class<?> clazz = Class.forName(info.name().toString());
//				ApiActivator activator = (ApiActivator) CDI.current().select(clazz);
				ApiActivator activator = (ApiActivator) clazz.newInstance();
				activator.getClasses().forEach(cz -> {
					ClassInfo ci = index.getClassByName(DotName.createSimple(cz.getName()));
					String name = ci.name().toString() + "_generated";
					String path = "D:/classes/" + name.replace('.', '/') + ".class";
					byte[] bytes = ClientServiceFactory.createImpl(name, ci);
					try {
						File file = new File(path);
						FileOutputStream fos = new FileOutputStream(path);
						fos.write(bytes);
						fos.close();
						System.out.println( file.getAbsolutePath() );
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
