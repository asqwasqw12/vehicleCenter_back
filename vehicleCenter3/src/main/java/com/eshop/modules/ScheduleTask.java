package com.eshop.modules;



import java.time.Duration;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eshop.jt808.service.LocationInRedisService;
import com.eshop.modules.kafka.Consumer;
import com.eshop.modules.kafka.Producer;
import com.eshop.modules.websocket.WebsocketServer;
import com.eshop.pojo.Vehicle;
import com.eshop.pojo.VehicleStatus;
import com.eshop.service.VehicleStatusInRedisService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduleTask {
	
	//Logger logger = LoggerFactory.getLogger(ScheduleTask.class);
	@Autowired
	LocationInRedisService locationInRedisService;
	@Autowired
	VehicleStatusInRedisService vehicleStatusInRedisService;
	@Autowired
    private WebsocketServer webSocket;
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Scheduled(fixedDelay = 1000)  //定时任务1
    public void printXXXXXXX(){
        try{
            //Thread.sleep(5000);  //睡眠5秒
            //logger.info(Thread.currentThread().getName()); //打印当前线程名字
            String Strfilter = null;
            //List<Location> list1 = locationInRedisService.getAll(Strfilter);
            List<VehicleStatus> list = vehicleStatusInRedisService.getAll(Strfilter);
    		String message = JSONObject.toJSONString(list);
    		webSocket.sendAllMessage(message);
    		
    		/*
    		 KafkaConsumer<String,String> consumer=Consumer.getConsumer();
    		 ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
             System.out.println("准备开始消费消息。。。");
             for (ConsumerRecord<String, String> record : records) {
             	System.out.println("消费者消息="+record);
                 //log.info(record);
             }*/
        }catch (Exception e){
            log.error("定时任务1错误{}"+e);
        }
    }
 
    @Scheduled(fixedDelay = 1000)  //定时任务2
    public void printYYYYYYY(){
        try{
            Thread.sleep(5000);
           // logger.info(Thread.currentThread().getName());
            
            /*
            //kafka测试1
            Vehicle vehicle = new Vehicle();
            vehicle.setBrand("东风");
            vehicle.setChargeDeviceCount(10);
            vehicle.setId((long) 2344);
            vehicle.setName("洗扫车");
            vehicle.setVin("djlkdf4779798");
            kafkaTemplate.send("test20210303","VehicleData",JSONObject.toJSONString(vehicle));
            */
            
            
            
            /*
            //kafka测试2
            KafkaProducer<String,String> producer = Producer.getProducer();
          //消息实体
            ProducerRecord<String , String> record = null;
            for (int i = 0; i < 100; i++) {
                record = new ProducerRecord<String, String>("test20210303", "key"+(int)(1000*(Math.random())),"valuexxx"+(int)(1000*(Math.random())));
                //发送消息
                producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (null != e){
                        	logger.info("send error" + e.getMessage());
                            System.out.println("send error" + e.getMessage());
                        }else {
                        	 System.out.println("生产者生产消息");
                            System.out.println(String.format("offset:%s,partition:%s",recordMetadata.offset(),recordMetadata.partition()));
                        }
                    }
                });
            }*/
 
        }catch (Exception e){
        	 log.error("定时任务2错误{}"+e);
        }
    }


}
