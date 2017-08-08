package com.workflow.SystemDesignZooKafka;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKGetData {
	
	private static ZooKeeper zkpr;
	private static ZooKeeperConnection zkprConn; 

	
	public static byte[] getMyData(final String path) {
		
	
		zkprConn=new ZooKeeperConnection();
		final CountDownLatch connectedSignal=new CountDownLatch(5);
		byte[] data=null;
		try {
			zkpr=zkprConn.connect("localhost");
			final Stat st=zkpr.exists(path, true);
			
			if(st!=null) {
				
				data= zkpr.getData(path, new Watcher() {
					
					public void process(WatchedEvent event) {
						if(event.getType()==Event.EventType.None) {
							if(event.getState()==KeeperState.Expired) {
								connectedSignal.countDown();
							}
						}else {
							try {
							byte[] data=zkpr.getData(path, false, null);
							System.out.println(new String(data,"UTF-8"));
							connectedSignal.countDown();
							} catch(Exception ex) {
								
							}
						}
					}
				}, null);
				
				connectedSignal.await();
			}
			else {
				System.out.println("Invalid node");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
		
	}
	
	public static void main(String[] args) {
		
		byte[] outData=getMyData("/myFirstZnode/children");
		
		try {
			System.out.println(new String(outData,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
