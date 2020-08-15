package com.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.aop.Log;
import com.eshop.common.HttpResult;
import com.eshop.common.SecurityUtils;
import com.eshop.modules.websocket.WebsocketServer;

@RestController
@RequestMapping("/vehicleLocation")
public class VehicleLocationController {
	
	@Autowired
    private WebsocketServer webSocket;
	
	
	@Log("查看车辆位置信息")
	@GetMapping(value="/findAll")
	public HttpResult findAll() throws InterruptedException {
		String userName = SecurityUtils.getUsername();
		 for (int i = 0; i < 20; i++){
	            Thread.sleep(1000);
	            System.out.println("userName:"+userName);
	            webSocket.sendTextMessage(userName, ""+i);
	        }
		return HttpResult.ok("传输完毕");
	}

}
