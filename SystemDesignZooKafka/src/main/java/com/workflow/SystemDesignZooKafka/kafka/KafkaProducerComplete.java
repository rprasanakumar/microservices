package com.workflow.SystemDesignZooKafka.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringDeserializer;


public class KafkaProducerComplete{
	private Producer<String, String>  producer;
	private static final Properties kafkaProp=new Properties();
	private String topics;
/*	public KafkaProducer() {
		kafkaProp.put("metadata.broker.list", "localhost:9092");
		kafkaProp.put("serializer.class", "kafka.serializer.StringEncoder");
		kafkaProp.put("request.required.acks", "1");
		producer=new Producer<Integer, String>(new ProducerConfig(kafkaProp));
	}*/
	
	static {
		kafkaProp.put("bootstrap.servers", KafkaProperties.kafkaServerURL+":"+KafkaProperties.kafkaServerPort);
		kafkaProp.put("serializer.class", KafkaProperties.stringEncoder);
		kafkaProp.put("request.required.acks", "1");
		kafkaProp.put("key.serializer", KafkaProperties.serializer);
		kafkaProp.put("value.serializer", KafkaProperties.serializer);
		
	}
	
	public KafkaProducerComplete(String topic) {
		this.topics=topic;
		this.producer=new KafkaProducer<String, String>(kafkaProp);
	}
	
	public void setMessage2Topics() {
		
		for(int i = 0; i < 10; i++) {
			producer.send(new ProducerRecord<String, String>(topics,
					Integer.toString(i), "Message number "+Integer.toString(i)));
		}
		
		System.out.println("Sent 10 messages successfully");
		producer.close();
		
	}
	
}
