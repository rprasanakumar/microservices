package com.service.APIGateWay.watcher;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;


import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.service.APIGateWay.nginx.ConfigurationUpdate;

/**
 * The watcher class responsible for looking for node addition, deletion and update
 * in the Znode tree hierarchy
 * 
 * Implements runnable. The main thread waits until a event is notified in the tree hierarchy
 * 
 * @author prasanna.rajendran
 *
 */

public class TreeCacheWatcher implements Runnable{
	 
	 TreeCache cache =null;
	 private static final String PATH = "/services";
	 static CuratorFramework client;
	 private static  boolean running;
	 
	public static void main(String[] args) {
		ExponentialBackoffRetry retryPolicy=new ExponentialBackoffRetry(3,1000);
		client= CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").retryPolicy(retryPolicy)
				.connectionTimeoutMs(1000)
				.sessionTimeoutMs(1000).build();
		 client.start();
		 running=true;
		 new TreeCacheWatcher().run();	
	}
	
	/**
	 *  Tree listener
	 * @param cache
	 */
	 private static void addListener(
			 final TreeCache cache)
	    {
	     
		 TreeCacheListener listener = new TreeCacheListener() 
	        {
	            public void childEvent(CuratorFramework client,
	            		TreeCacheEvent event) throws Exception
	            {
	                switch ( event.getType() )
	                {
	                    case NODE_ADDED:
	                    {
	                        ConfigurationUpdate.add(event.getData().getPath(), new String(event.getData().getData())).update();
	                        break;
	                    }
	                    
	                    case NODE_REMOVED:
	                    {
	                        ConfigurationUpdate.remove(event.getData().getPath()).update();
	                        break;
	                    }
	                    
	                    case NODE_UPDATED:
	                    {
	                        ConfigurationUpdate.add(event.getData().getPath(), new String(event.getData().getData())).update();
	                        break;
	                    }
	              
					case CONNECTION_LOST:
						break;
					case CONNECTION_RECONNECTED:
						break;
					case CONNECTION_SUSPENDED:
						break;
					case INITIALIZED:
						break;
					default:
						break;	
	                }
	            }
	        };
	        cache.getListenable().addListener(listener);
	    }


	public void run() {
		 cache = new TreeCache(client, PATH);
		 try {
			cache.start();
			addListener(cache);
			
			synchronized(this){
				while (TreeCacheWatcher.running){
					wait();
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
