package com.eshop.modules.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Logger;

public class MessageProducer {
	static Logger log = Logger.getLogger(Producer.class); //静态是否合适
	
	private static final String BOOTSTRAP_SERVERS = "localhost:9092";
	private static final String ARG1 = "1";
	private static  KafkaProducer<String,String> producer=null;
	private  String topic = null;
	  /*
    初始化配置
     */
    private static  void init(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
        properties.put(ProducerConfig.ACKS_CONFIG, ARG1);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        producer = new KafkaProducer<String, String>(properties);
    }

}
