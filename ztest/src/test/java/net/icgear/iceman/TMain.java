package net.icgear.iceman;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

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

		JAXRSArchive archive = ShrinkWrap.create(JAXRSArchive.class, "webapp-test.war");
		archive.addPackages(true, "com.suisrc");
		archive.addAsResource(new ClassLoaderAsset("META-INF/beans.xml", TMain.class.getClassLoader()), "META-INF/beans.xml");
		archive.addAllDependencies();
		System.out.println(archive.toString(true));
//		ServiceClientProcessor.processArchive(archive);
		swarm.deploy(archive);
	}
}
