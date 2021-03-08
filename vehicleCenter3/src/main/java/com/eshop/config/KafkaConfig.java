package com.eshop.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.*;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import com.eshop.modules.kafka.listener.VehicleDataListener;

@Configuration
@EnableKafka
public class KafkaConfig {
	

	//消费者参数
	@Value("${kafka.consumer.servers}")
	private String consumerServers;  //消费者服务器
	
    @Value("${kafka.consumer.enable-auto-commit}")
	private String consumerEnableAutoCommit;//
    
    @Value("${kafka.consumer.session-timeout}")
	private String consumerSessionTimeout;
    
    @Value("${kafka.consumer.auto-commit-interval}")
	private String consumerAutoCommitInterval;
    
    @Value("${kafka.consumer.auto-offset-reset}")
	private String consumerAutoOffsetReset;
    
    @Value("${kafka.consumer.topic}")
	private String consumerTopic;
    
    @Value("${kafka.consumer.group-id}")
	private String consumerGroupId;//消费组ID
    

    
    @Value("${kafka.consumer.key-deserializer}")
	private String consumerKeyDeserializer;//key反序列化
    
    @Value("${kafka.consumer.value-deserializer}")
	private String consumerValueDeserializer;//value反序列化
    
    //监听参数
    @Value("${kafka.listener.concurrency}")
	private Integer listenerConcurrency;   //Listener线程数
    
    
    //生产者参数
    @Value("${kafka.producer.servers}")
	private String producerServers;  //生产者服务器
    
    @Value("${kafka.producer.ack-config}")
   	private String producerAckConfig;  ////指定了必须要有多少个分区副本收到消息，生产者才会认为写入消息是成功的，这个参数对消息丢失的可能性有重大影响。
    
    @Value("${kafka.producer.retries}")
	private String producerRetries;//失败是否重试，设置会有可能产生重复数据
    
    @Value("${kafka.producer.batch-size}")
	private String producerBatchSize;//对于每个partition的batch buffer大小
    
    @Value("${kafka.producer.linger}")
    private String producerLinger; //批量发送，延迟为1毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量
    
    @Value("${kafka.producer.buffer-memory}")
    private String producerBufferMemory;//整个producer可以用于buffer的内存大小
	
    @Value("${kafka.producer.key-serializer}")
    private String producerkeySerializer;//key的序列化
    
    @Value("${kafka.producer.value-serializer}")
    private String producerValueSerializer;//value的序列化
    
    
	
	
	    //监听容器工厂
	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory());
	        factory.setConcurrency(listenerConcurrency);
	        return factory;
	    }
	    
	    //消费者配置
	    @Bean
	    public Map<String, Object> consumerConfig() {
	        Map<String, Object> props = new HashMap<>();
	        props.put("bootstrap.servers", consumerServers);
	        props.put("group.id", consumerGroupId);
	        props.put("key.deserializer", consumerKeyDeserializer);
	        props.put("value.deserializer", consumerValueDeserializer);

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
	        props.put("bootstrap.servers", producerServers);
	        //指定了必须要有多少个分区副本收到消息，生产者才会认为写入消息是成功的，这个参数对消息丢失的可能性有重大影响。
	        props.put("acks",producerAckConfig);
	        //批量发送，延迟为1毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量
	        props.put("linger.ms", producerLinger);
	        //键的序列化方式
	        props.put("key.serializer", producerkeySerializer);
	        //值的序列化方式
	        props.put("value.serializer", producerValueSerializer);

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
	    
	    @Bean
	    public VehicleDataListener vehicleDataListener() {
	    	return new VehicleDataListener();
	    }
}
