package com.workflow.SystemDesignZooKafka.kafka;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KafkaDriverClass {
	
	final static int numConsumers=3;
	public static void main(String[] args) {
		String topic="PrintingDevices_Communication";
		KafkaProducerComplete prodcr=new KafkaProducerComplete(topic);
		prodcr.setMessage2Topics();
		System.out.println("Producer Completed");
		
		// Consumer Sevice Starts
		
		ArrayList<String> topics=new ArrayList<String>();
		topics.add(topic);
		topics.add("Offset_Communication_group");
		
		final ExecutorService executor = Executors.newFixedThreadPool(numConsumers);
		final List<KafkaConsumerComplete> listOfKafkaConsumers=new ArrayList<KafkaConsumerComplete>();
		
		for(int i=0;i<numConsumers;i++) {
			KafkaConsumerComplete conSumr=new KafkaConsumerComplete(topics);
			listOfKafkaConsumers.add(conSumr);
			executor.submit(conSumr);
		}
		
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
			for (KafkaConsumerComplete consumer : listOfKafkaConsumers) {
				consumer.shutdown();
			}
			executor.shutdown();
			try {
			executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			});
		
		
		
	}

}
