package com.workflow.SystemDesignZooKafka.kafka;

public interface KafkaProperties {
	
	final static String zkConnect="localhost:2181";
	final static String producerGroupId="printGroup1";
	final static String messageTopic="topic1";

	final static String kafkaServerURL="localhost";
	final static String kafkaServerPort="9092";
	  
	final static int kafkaProducerBufferSize = 64*1024;
	final static int connectionTimeOut = 100000;
	final static int reconnectInterval = 10000;

}
