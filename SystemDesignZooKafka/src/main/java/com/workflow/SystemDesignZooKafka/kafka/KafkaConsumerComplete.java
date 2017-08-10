package com.workflow.SystemDesignZooKafka.kafka;

import java.util.*;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
public class KafkaConsumerComplete implements Runnable{
	
	private static final Properties prop;
	private Consumer<String,String> consumer;
	private List<String> topics;
	static {
		prop=new Properties();
		prop.put("bootstrap.servers", KafkaProperties.kafkaServerURL+":"+KafkaProperties.kafkaServerPort);
		prop.put("group.id", KafkaProperties.consumerGroupId);
		prop.put("key.deserializer", KafkaProperties.deserializer);
		prop.put("value.deserializer", KafkaProperties.deserializer);
	}
	
	public KafkaConsumerComplete( ArrayList<String> topics) {
		this.consumer= new KafkaConsumer<String, String>(prop);
		this.topics=topics;
	}
	
/*	public void subscribeNPrintMessage() {
		consumer.subscribe(topics);
		
		while(true) {
			ConsumerRecords<String, String> records = consumer.poll(1000);
			
			for(ConsumerRecord<String, String> rec: records) {
				System.out.println("message Partition: "+rec.partition() +" message offset: "+rec.offset()+" message : "+rec.value());
			}
			
		}
		
	}*/
	public void run() {

		// TODO Auto-generated method stub
		consumer.subscribe(topics);
		
		while(true) {
			ConsumerRecords<String, String> records = consumer.poll(10);
			for(ConsumerRecord<String, String> rec: records) {
				System.out.println("message Topic: "+rec.topic() + " message Partition: "+rec.partition() +" message offset: "+rec.offset()+" message : "+rec.value());
			}			
		}
	}

	public void shutdown() {
		// TODO Auto-generated method stub
		consumer.wakeup();
		
	}

}
