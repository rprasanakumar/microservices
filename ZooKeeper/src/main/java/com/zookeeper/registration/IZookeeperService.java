package com.zookeeper.registration;

public interface IZookeeperService {
	
	
	/**
	 * Register a service by path and service name. Creates a znode, type ephemeral 
	 * 
	 * For the given path and name, a znode is created. The paths are hierarchical. If the parent node does not exist, it is created.
	 * 
	 * 
	 * @param serviceName- the name of the individual service for identification
	 * @param path- The endpoint to which the service can handle the request.
	 * 
	 */
	
	public void registerService(String serviceName, String path)  throws Exception; 
	
	
	
	
	/**
	 * Unregister a service by path. Deletes a znode recursively.
	 * 
	 * 
	 * @param serviceName- the name of the individual service to be deleted
	 * @param path- The endpoint to which the service was registered.
	 * 
	 */
	public void unregisterService(String serviceName, String path)  throws Exception;
}
