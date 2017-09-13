package com.service.APIGateWay.nginx;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.jboss.netty.util.internal.ConcurrentHashMap;

import com.service.APIGateWay.bean.Configuration;
import com.service.APIGateWay.bean.RegisteredService;
import com.service.APIGateWay.bean.ServiceProperty;

/**
 * This class helps in converting the service parameters to the configuration specific
 * 
 * @author prasanna.rajendran
 *
 */

public class ConfigurationMap {
	
	private ConfigurationMap() {
		
	}
	/**
	 * Method converts the registered services to configuration map(key: serviceAPI, value: {@link Configuration})
	 * @return ConcurrentMap
	 */
	public static ConcurrentMap<String, Configuration> getMap(){
		
		ConcurrentMap<String, ServiceProperty> serviceMap=RegisteredService.getInstance().getServiceMap();
		ConcurrentMap<String, Configuration> configMap =new ConcurrentHashMap<String, Configuration>();
		for(String key:serviceMap.keySet()) {
			Set<String> serverSet;
			ServiceProperty sp=RegisteredService.getInstance().getData(key);
			Configuration configuration=new Configuration();
			configuration.setLocation(sp.getServiceAPI());
			configuration.setProxyPass(sp.getServiceAPI());
			
			if(!configMap.containsKey(sp.getServiceAPI())) {
				serverSet=new HashSet<String>(); 
			}else {
				serverSet= configMap.get(sp.getServiceAPI()).getUpstreamServer();
			}
			serverSet.add(sp.getServiceUrl());
			configuration.setUpstreamServer(serverSet);
			configMap.put(sp.getServiceAPI(), configuration);
		}
		
		return configMap;
	}

}
