package com.zookeeper.registration;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.zookeeper.service.ZooKeeperService;

@ApplicationScoped
@ZooKeeperService
public class ZookeeperServiceImpl implements IZookeeperService {
	
	
	@Inject
	public ZookeeperServiceImpl() {
		
	}

	public void registerService(String serviceName, String path) throws Exception {
		System.out.println("------------------------------------Registered to ZooKeeper--------------------------------------------   " + serviceName);
		
	}

	public void unregisterService(String serviceName, String path) throws Exception {
		System.out.println("------------------------------------UNRegistered to ZooKeeper--------------------------------------------   " + serviceName);
		
	}

}
