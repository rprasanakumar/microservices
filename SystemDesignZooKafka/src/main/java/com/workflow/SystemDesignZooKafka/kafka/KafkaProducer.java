package com.workflow.SystemDesignZooKafka.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer{
	private static Producer<Integer, String> producer;
	private static final Properties kafkaProp=new Properties();
	
/*	public KafkaProducer() {
		kafkaProp.put("metadata.broker.list", "localhost:9092");
		kafkaProp.put("serializer.class", "kafka.serializer.StringEncoder");
		kafkaProp.put("request.required.acks", "1");
		producer=new Producer<Integer, String>(new ProducerConfig(kafkaProp));
	}*/
	
	static {
		kafkaProp.put("metadata.broker.list", "localhost:9092");
		kafkaProp.put("serializer.class", "kafka.serializer.StringEncoder");
		kafkaProp.put("request.required.acks", "1");
		producer=new Producer<Integer, String>(new ProducerConfig(kafkaProp));
		
	}
	
	public static void main(String[] args) {
		String topics="topic1";
		String message="Hello54";
		KeyedMessage<Integer, String> message2Send=new KeyedMessage<Integer, String>(topics, message);
		producer.send(message2Send);
		producer.close();
	}
	
}
