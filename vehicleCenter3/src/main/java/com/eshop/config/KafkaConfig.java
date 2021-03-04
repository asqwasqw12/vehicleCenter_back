package com.eshop.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.*;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import com.eshop.modules.kafka.KafkaConstants;
@Configuration
@EnableKafka
public class KafkaConfig {
	    //监听容器
	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory());
	        return factory;
	    }
	    
	    //消费者配置
	    @Bean
	    public Map<String, Object> consumerConfig() {
	        Map<String, Object> props = new HashMap<>();
	        props.put("bootstrap.servers", KafkaConstants.BOOTSTRAP_SERVERS);
	        props.put("group.id", KafkaConstants.GROUP_ID_VEHICLE_INFO);
	        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

	        return props;
	    }
	    //消费者工厂
	    @Bean
	    public ConsumerFactory<String, String> consumerFactory() {
	        return new DefaultKafkaConsumerFactory<>(consumerConfig());
	    }
	    //生产者配置
	    @Bean
	    public Map<String, Object> producerConfig() {
	        Map<String, Object> props = new HashMap<>();
	        //连接地址
	        props.put("bootstrap.servers", KafkaConstants.BOOTSTRAP_SERVERS);
	        //指定了必须要有多少个分区副本收到消息，生产者才会认为写入消息是成功的，这个参数对消息丢失的可能性有重大影响。
	        props.put("acks",KafkaConstants.ACKS_CONFIG);
	        //批量发送，延迟为1毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量
	        props.put("linger.ms", 1);
	        //键的序列化方式
	        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        //值的序列化方式
	        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        //键的反序列化方式
	        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        //值的反序列化方式
	        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

	        return props;
	    }
	    //生产者工厂
	    @Bean
	    public ProducerFactory<String, String> producerFactory() {
	        return new DefaultKafkaProducerFactory<>(producerConfig());
	    }
	    //kafkaTemplate实现了Kafka发送接收等功能
	    @Bean
	    public KafkaTemplate<String, String> kafkaTemplate() {
	        return new KafkaTemplate<>(producerFactory(), true);
	    }
}
