package com.service.ZooKeeper.registration;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@RegisterZooKeeper
public class ZookeeperServiceRegistryImpl implements IServiceRegistry{
	
	
	@Inject
	public ZookeeperServiceRegistryImpl() {
		
	}

	public void registerService(String serviceName, String path) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void unregisterService(String serviceName, String path) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
