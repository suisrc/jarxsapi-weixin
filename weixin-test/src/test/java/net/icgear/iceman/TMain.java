package net.icgear.iceman;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import com.suisrc.weixin.runtime.ServiceClientProcessor;

/**
 * 程序入口
 * 
 * @author Y13
 *
 */
public class TMain {

    public static void main(String[] args) throws Exception {
        Swarm swarm = new Swarm();
        swarm.start();

        JAXRSArchive archive = ShrinkWrap.create(JAXRSArchive.class, "test.war");
        archive.addPackages(true, "com.qq.weixin", "com.suisrc");
        // archive.addAsLibrary(Swarm.artifact("org.apache.httpcomponents:httpclient"));
        archive.addModule("org.apache.httpcomponents", "main");
        archive.addAsResource(new ClassLoaderAsset("META-INF/beans.xml", TMain.class.getClassLoader()), "META-INF/beans.xml");
        archive.addAllDependencies();
        ServiceClientProcessor.processArchive2(archive);
        swarm.deploy(archive);
    }
}
