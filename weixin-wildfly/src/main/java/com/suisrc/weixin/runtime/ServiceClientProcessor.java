package com.suisrc.weixin.runtime;

import java.io.IOException;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.jandex.Index;
import org.jboss.jandex.Indexer;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.spi.api.ArchiveMetadataProcessor;

import com.suisrc.jaxrsapi.core.factory.ClientServiceFactory;


/**
 * 初始化构建需要加载的扩展对象
 * 
 * @author Y13
 *
 */
@ApplicationScoped
public class ServiceClientProcessor implements ArchiveMetadataProcessor {

    @Override
    public void processArchive(Archive<?> archive, Index index) throws Exception {
        JAXRSArchive jaxrsArchive = archive.as(JAXRSArchive.class);
        ClientServiceFactory.processIndex(index, (api, impl) -> {
            try {
                String path = "WEB-INF/classes/" + impl.getName().replace('.', '/') + ".class";
                jaxrsArchive.add(new ByteArrayAsset(impl.toBytecode()), path);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 当系统无法加载ServiceClientProcessor的时候, 手动调用
     * 
     * @param archive
     * @throws Exception
     */
    public static void processArchive2(Archive<?> archive) throws Exception {
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
