package com.eshop.modules.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Logger;

public class Producer {
	static Logger log = Logger.getLogger(Producer.class);

    private static final String TOPIC = "test20210303";
    private static final String BROKER_LIST = "localhost:9092";
    private static final String ARG1 = "1";
    private static KafkaProducer<String,String> producer = null;

    /*
    初始化生产者
     */
    static {
        Properties configs = initConfig();
        producer = new KafkaProducer<String, String>(configs);
    }

    /*
    初始化配置
     */
    private static Properties initConfig(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);
        properties.put(ProducerConfig.ACKS_CONFIG, ARG1);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return properties;
    }

    public static void main(String[] args) throws InterruptedException {
        //消息实体
        ProducerRecord<String , String> record = null;
        for (int i = 0; i < 10; i++) {
            record = new ProducerRecord<String, String>(TOPIC, "key"+(int)(1000*(Math.random())),"valuexxx"+(int)(1000*(Math.random())));
            //发送消息
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (null != e){
                        log.info("send error" + e.getMessage());
                        System.out.println("send error" + e.getMessage());
                    }else {
                    	 System.out.println("生产者生产消息");
                        System.out.println(String.format("offset:%s,partition:%s",recordMetadata.offset(),recordMetadata.partition()));
                    }
                }
            });
        }
        producer.close();
  }
    public static  KafkaProducer<String,String> getProducer(){
    	return producer;
    }
}
