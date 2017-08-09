package com.workflow.SystemDesignZooKafka.kafka;

public interface KafkaProperties {
	
	final static String zkConnect="localhost:2181";
	final static String consumerGroupId="theconsumergroup";
	final static String messageTopic="topic1";

	final static String kafkaServerURL="localhost";
	final static String kafkaServerPort="9092";
	
	final static String stringEncoder="kafka.serializer.StringEncoder";
	
	final static String serializer="org.apache.kafka.common.serialization.StringSerializer";
	final static String deserializer="org.apache.kafka.common.serialization.StringDeserializer";

}
