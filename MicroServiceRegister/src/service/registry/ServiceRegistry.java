package service.registry;

import java.io.IOException;
import java.util.Properties;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.jboss.netty.util.internal.ConcurrentHashMap;

public class ServiceRegistry implements IServiceRegistry{
	
	private CuratorFramework curatorFramework;
	private ConcurrentHashMap<String, String> service2APImapping;
	public ServiceRegistry() {
		Properties prop=new Properties();
		try {
			prop.load(this.getClass().getResourceAsStream("zookeeper.properties"));
			curatorFramework=CuratorFrameworkFactory.newClient(prop.getProperty("host")+":"+prop.getProperty("port"), new RetryNTimes(5, 1000));
			curatorFramework.start();
			service2APImapping=new ConcurrentHashMap<>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void registerService(String serviceName, String uri) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unRegisterService(String serviceName, String uri) {
		// TODO Auto-generated method stub
		
	}

}
