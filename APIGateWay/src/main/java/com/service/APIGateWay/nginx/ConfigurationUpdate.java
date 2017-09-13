package com.service.APIGateWay.nginx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Iterator;
import java.util.Map;

import java.util.concurrent.ConcurrentMap;

import com.service.APIGateWay.bean.Configuration;
import com.service.APIGateWay.bean.RegisteredService;

/**
 * This class uses the Configuration map for constructing the configuration file.
 * @author prasanna.rajendran
 *
 */

public class ConfigurationUpdate {
	
	private static ConfigurationUpdate configurationUpdate;
	
	private ConfigurationUpdate() {
		
	}
	
	/**
	 * Method adds the registered service's UUID and Data to the {@link RegisteredService}
	 * @param uuid
	 * @param data
	 * @return {@link ConfigurationUpdate configurationUpdate}
	 */
	
	public static ConfigurationUpdate add(String uuid,String data) {
		
		if(uuid==null||uuid.isEmpty()) {
			return configurationUpdate;
		}
		 RegisteredService.getInstance().addService(uuid, data);
		if(configurationUpdate==null) {
			configurationUpdate = new ConfigurationUpdate();
		}
		
		return configurationUpdate;
	}
	
	
	/**
	 * Method removes the uuid from the RegisteredService
	 * @param uuid
	 * @return {@link ConfigurationUpdate configurationUpdate} 
	 */
	
	public static ConfigurationUpdate remove(String uuid) {
		
		if(uuid==null||uuid.isEmpty()) {
			return configurationUpdate;
		}
		 RegisteredService.getInstance().removeService(uuid);
	
		if(configurationUpdate==null) {
			configurationUpdate = new ConfigurationUpdate();
		}
		return configurationUpdate;
	}

	/**
	 * this method performs Nginx conf update operation
	 * 1. {@link #writeFile()}
	 * 2. {@link #runBackUpNreLoad()}
	 */
	public void update() {
		if(configurationUpdate==null) {
			return;
		}
		writeFile();
		runBackUpNreLoad();
	}
	
	
	/**
	 * This method creates new files with the present {@link ConfigurationMap} values
	 * {@link #getUpstreamServers(Configuration, StringBuilder)} 
	 */
	public void writeFile() {
		
		StringBuilder sbConfig=new StringBuilder();	
		StringBuilder sbUpstream=new StringBuilder();
		BufferedWriter bw1 = null;
		BufferedWriter bw2 = null;
		ConcurrentMap<String, Configuration> configMap=ConfigurationMap.getMap();
		
		for(Map.Entry<String, Configuration> value:configMap.entrySet()) {
			sbConfig.append(String.format("location %s  {", "/"+value.getValue().getLocation()));
			sbConfig.append('\n');
			sbConfig.append(String.format("proxy_pass %s  ;", "http://"+value.getValue().getProxyPass()));
			sbConfig.append('\n');
			sbConfig.append("}");
			sbConfig.append('\n');
			getUpstreamServers(value.getValue(), sbUpstream);
		}
		
		try {
			bw1=new BufferedWriter(new FileWriter("C:\\Nginx\\nginx-1.12.1\\conf\\nginx_backend_back.conf"));
			bw2=new BufferedWriter(new FileWriter("C:\\Nginx\\nginx-1.12.1\\conf\\upstream.conf"));
			bw1.write(sbConfig.toString());
			bw2.write(sbUpstream.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(bw1!=null) {
					bw1.flush();
					bw1.close();	
				}
				if(bw2!=null) {
					bw2.flush();
					bw2.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * Helper method for {@link #writeFile()}
	 * 
	 * Used for creating the upstream server list from the Registered service {@link Configuration} value
	 * @param value
	 * @param sbUpstream
	 */
	public void getUpstreamServers(Configuration value, StringBuilder sbUpstream) {
		Iterator<String> it = value.getUpstreamServer().iterator();
		sbUpstream.append(String.format("upstream %s  {", value.getLocation()));
		sbUpstream.append('\n');
		while(it.hasNext()) {
			sbUpstream.append(String.format("server %s ;", it.next()));
			sbUpstream.append('\n');
		}
		sbUpstream.append("}");
		sbUpstream.append('\n');
		
	}
	
	
	/**
	 * Method used to back up the existing configuration file and reload the Nginx
	 * uses {@link ProcessBuilder}
	 */
	
	public void runBackUpNreLoad() {
		
		 String workDir="C:\\backreloadscript";
	     String[] command = {"CMD", "/C", workDir+ File.separator+"reload.bat"};
	     File out=new File("log");
	     ProcessBuilder builder = new ProcessBuilder(command);
	     builder.redirectErrorStream(true);
	     builder.redirectOutput(out);
	     builder.directory(new File(workDir));
	     try {
			Process proc=builder.start();
			final int status=proc.waitFor();
			System.out.println("Status----> "+ status);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
