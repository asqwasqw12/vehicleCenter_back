package com.eshop.modules.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VehicleDataListener {

    @KafkaListener(topics = {"test20210303"})
    public void listen(ConsumerRecord<?, ?> record) {
        log.debug("监听到消息记录 ===============");
        log.debug("topic = " + record.topic());
        log.debug("partition = " + record.partition());
        log.debug("offset = " + record.offset());
        log.debug("key = " + record.key());
        log.debug("value = " + record.value());
        log.debug("----------------------------");

       /* System.out.println("监听到消息记录 ===============");
        System.out.println("topic = " + record.topic());
        System.out.println("partition = " + record.partition());
        System.out.println("offset = " + record.offset());
        System.out.println("key = " + record.key());
        System.out.println("value = " + record.value());
        System.out.println("----------------------------");*/
    }
}
