package com.service.APIGateWay.watcher;

import java.util.Properties;
import java.io.IOException;
import java.io.FileInputStream;

/**
 * Class for loading the Zookeeper properties 
 * @author ryo.yokoohji, prasanna.rajendran
 *
 */


public class PropertyLoader {
	private static final String propertyFile = "./resource/watcher.property";
	private Properties prop;
	
	public PropertyLoader(){
		prop = new Properties();
		
		try{
			System.out.println("Current dir: " + System.getProperty("user.dir"));
			
			prop.load(new FileInputStream(propertyFile));
			
			System.out.println("[Property] zkserver: " + prop.getProperty("zkserver"));
			System.out.println("[Property] baseznode: " + prop.getProperty("baseznode"));
		}catch(IOException ie){
			ie.printStackTrace();
		}
	}
	
	public String getZooKeeperAddress(){
		if(this.prop.getProperty("zkserver").isEmpty()){
			return null;
		}else{
			return this.prop.getProperty("zkserver");
		}
	}
	
	public String getBaseZnode(){
		if(this.prop.getProperty("baseznode").isEmpty()){
			return null;
		}else{
			return this.prop.getProperty("baseznode");
		}
	}
	
	public String getProperty(String key){
		return this.prop.getProperty(key);
	}
	
	/**
	 * Intentionally unreference the Properties instance. 
	 */
	public void destroy(){
		this.prop = null;
	}
}
