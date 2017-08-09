package com.workflow.SystemDesignZooKafka.kafka;


import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerConnector;
import kafka.consumer.ConsumerIterator;

import java.util.*;
import kafka.consumer.KafkaStream;


public class KafkaConsumerAPI {
	
	private final kafka.javaapi.consumer.ConsumerConnector consumer;
	private final String topic;
	Properties properties=new Properties();
	
	public KafkaConsumerAPI(String zooKeeper, String groupId, String topic) {
		properties.put("zookeeper.connect", zooKeeper);
		properties.put("group.id", groupId);
		properties.put("zookeeper.session.timeout.ms", "500");
		properties.put("zookeeper.sync.time.ms", "250");
		properties.put("auto.commit.interval.ms", "1000");
		consumer=Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
		this.topic=topic;
	}
	
	public void getConsumerMessage() {
		
	    Map<String, Integer> topicCount = new HashMap<String, Integer>();
        topicCount.put(topic, 1);
        Map<String, List<KafkaStream<byte[], byte[]>>>  consumerStreams = consumer.createMessageStreams(topicCount);
        
        List<KafkaStream<byte[], byte[]>> streams=consumerStreams.get(topic);
        for(KafkaStream strm:streams) {
        	  ConsumerIterator<byte[], byte[]> it = strm.iterator();
              while (it.hasNext()) {
                  System.out.println("Message from "+topic+ " Topic:" + new String(it.next().message()));
              }
        }
        
        if (consumer != null) {
            consumer.shutdown();
        }
	}
	
	
	public static void main(String[] args) {
		String topic="topic1";
		
		KafkaConsumerAPI conSumer=new KafkaConsumerAPI(KafkaProperties.zkConnect, KafkaProperties.producerGroupId, topic);
		conSumer.getConsumerMessage();
	}

}
