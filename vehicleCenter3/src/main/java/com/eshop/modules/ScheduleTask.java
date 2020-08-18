package com.eshop.modules;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.eshop.jt808.pojo.Location;
import com.eshop.jt808.service.LocationInRedisService;
import com.eshop.modules.websocket.WebsocketServer;

@Component
public class ScheduleTask {
	
	Logger logger = LoggerFactory.getLogger(ScheduleTask.class);
	@Autowired
	LocationInRedisService locationInRedisService;
	@Autowired
    private WebsocketServer webSocket;
	
	@Scheduled(fixedDelay = 1000)  //定时任务1
    public void printXXXXXXX(){
        try{
            //Thread.sleep(5000);  //睡眠5秒
            logger.info(Thread.currentThread().getName()); //打印当前线程名字
            String Strfilter = null;
            List<Location> list = locationInRedisService.getAll(Strfilter);
    		String message = JSONObject.toJSONString(list);
    		webSocket.sendAllMessage(message);
 
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
 
    @Scheduled(fixedDelay = 1000)  //定时任务2
    public void printYYYYYYY(){
        try{
            Thread.sleep(5000);
            logger.info(Thread.currentThread().getName());
 
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }


}
