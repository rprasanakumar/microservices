package com.zookeeper.service;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zookeeper.registration.IZookeeperService;

public class ServiceContextResource  implements ServletContextListener{

	@Inject @ZooKeeperService IZookeeperService zookeeperService;
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			zookeeperService.registerService("printservice", "https://localhost:8080");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			zookeeperService.unregisterService("printservice", "https://localhost:8080");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}