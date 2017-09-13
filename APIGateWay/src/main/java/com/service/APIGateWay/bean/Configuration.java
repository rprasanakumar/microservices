package com.service.APIGateWay.bean;

import java.util.Set;
/**
 * This class holds the Nginx configuration parameters
 * 
 * @author prasanna.rajendran
 *
 */
public class Configuration implements Comparable<Configuration>{
	
	
	private String location;
	private String proxyPass;
	private Set<String> upstreamServer;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProxyPass() {
		return proxyPass;
	}
	public void setProxyPass(String proxyPass) {
		this.proxyPass = proxyPass;
	}
	public Set<String> getUpstreamServer() {
		return upstreamServer;
	}
	public void setUpstreamServer(Set<String> upstreamServer) {
		this.upstreamServer = upstreamServer;
	}

	public int compareTo(Configuration o) {
		return this.getLocation().compareTo(o.getLocation());
	}
	
	

}
