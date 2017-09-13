package com.service.APIGateWay.bean;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.gson.Gson;

/**
 * Stores all registered services to zookeeper.
 * @author ryo.yokoohji
 */
public class RegisteredService {
	private String apiName;
	private ConcurrentMap<String, ServiceProperty> serviceMap;
	private static RegisteredService service;
	
	
/*	public RegisteredService(){
		this.serviceMap = new ConcurrentHashMap<String, ServiceProperty>();
	}*/
/*	
	public RegisteredService(String apiName){
		this();  // call default constructor
		// set api name.
		this.apiName = apiName;
	}*/
	
	private RegisteredService() {
		this.serviceMap = new ConcurrentHashMap<String, ServiceProperty>();
	}
	public static RegisteredService getInstance() {
		if(service==null) {
			service=new RegisteredService();
		}
		return service;
	}
	
	// parser the given data to require POJO(ServiceProperty.class) type
	private ServiceProperty parseData(String data){
		Gson gson = new Gson(); 
		return gson.fromJson(data, ServiceProperty.class);
	}
	
	public void addService(String uuid, String data){
		if(data==null||data.isEmpty()) {
			return;
		}
		this.serviceMap.put(uuid, parseData(data));
	}
	
	public void removeService(String uuid) {
		this.serviceMap.remove(uuid);
	}
	
	public ServiceProperty getData(String uuid) {
		return this.serviceMap.get(uuid);
	}
	
	public String getApiName(){
		return this.apiName;
	}
	
	public void setApiName(String apiName){
		this.apiName = apiName;
	}
	
	public ConcurrentMap<String, ServiceProperty> getServiceMap(){
		return this.serviceMap;
	}
}
