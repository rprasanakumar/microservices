package com.workflow.SystemDesignZooKafka;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZKnodeCreate {
	
	private static ZooKeeper zooKpr;
	private static ZooKeeperConnection zooKprConn;
	
	
	public static void create(String path, byte[] data) throws KeeperException, InterruptedException {
		
		zooKpr.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	
	public static void main(String[] args) {
		
		String path="/myFirstZnode/children";
		byte[] data="Data stream for my First child node in ZK app".getBytes();
		
		zooKprConn= new ZooKeeperConnection();
		try {
			zooKpr=zooKprConn.connect("localhost");
			create(path,data);
			zooKprConn.close();
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
