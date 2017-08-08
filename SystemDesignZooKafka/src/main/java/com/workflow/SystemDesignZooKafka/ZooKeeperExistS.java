package com.workflow.SystemDesignZooKafka;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZooKeeperExistS {
	
	private static ZooKeeper zk;
	private static ZooKeeperConnection zkConn;
	
	public static Stat exists(String path) throws KeeperException, InterruptedException {
				
		return zk.exists(path, true);
		
	}
	
	
	public static void main(String[] args) {
		
		
		String path="/myFirstZnode";
		zkConn=new ZooKeeperConnection();
		try {
			zk=zkConn.connect("localhost");
			Stat stat=exists(path);
			if(stat!=null) {
				System.out.println("Node is valid "+stat.getVersion());
			}else {
				System.out.println("Invalid Node!!");
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
		
	}

}
