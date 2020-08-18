package com.eshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.eshop.aop.Log;
import com.eshop.common.HttpResult;
import com.eshop.common.SecurityUtils;
import com.eshop.jt808.pojo.Location;
import com.eshop.jt808.service.LocationInRedisService;
import com.eshop.modules.websocket.WebsocketServer;

@RestController
@RequestMapping("/vehicleLocation")
public class VehicleLocationController {
	
	@Autowired
    private WebsocketServer webSocket;
	
	@Autowired
	LocationInRedisService locationInRedisService;
	
	
	@Log("查看车辆位置信息")
	@GetMapping(value="/findAll")
	public HttpResult findAll() throws InterruptedException {
		
		String Strfilter = null;
		List<Location> list = locationInRedisService.getAll(Strfilter);
		/*
		 * String userName = SecurityUtils.getUsername();
		 * String message = JSONObject.toJSONString(list); for (int i = 0; i < 20; i++){
		 * Thread.sleep(1000); System.out.println("userName:"+userName);
		 * webSocket.sendTextMessage(userName, message); }
		 */
		return HttpResult.ok(list);
	}

}
