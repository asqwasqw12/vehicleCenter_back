package com.eshop.modules.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class VehicleDataListener {
	
	private static Logger logger = LoggerFactory.getLogger(VehicleDataListener.class);

    @KafkaListener(topics = {"test20210303"})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("监听到消息记录 ===============");
        logger.info("topic = " + record.topic());
        logger.info("partition = " + record.partition());
        logger.info("offset = " + record.offset());
        logger.info("key = " + record.key());
        logger.info("value = " + record.value());
        logger.info("----------------------------");

       /* System.out.println("监听到消息记录 ===============");
        System.out.println("topic = " + record.topic());
        System.out.println("partition = " + record.partition());
        System.out.println("offset = " + record.offset());
        System.out.println("key = " + record.key());
        System.out.println("value = " + record.value());
        System.out.println("----------------------------");*/
    }
}
