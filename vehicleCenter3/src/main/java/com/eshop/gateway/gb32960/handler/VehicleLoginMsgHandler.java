package com.eshop.gateway.gb32960.handler;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshop.gateway.gb32960.config.ChannelManager;
import com.eshop.gateway.gb32960.pojo.req.VehicleLoginMsg;
import com.eshop.gateway.gb32960.res.CommonRespMsg;
import com.eshop.pojo.Vehicle;
import com.eshop.service.VehicleService;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ChannelHandler.Sharable
public class VehicleLoginMsgHandler extends BaseHandler<VehicleLoginMsg>{
	
	@Autowired
    private ChannelManager channelManager;
	@Autowired
	private VehicleService vehicleService;
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, VehicleLoginMsg msg) throws Exception {
		
		String vin = msg.getHeader().getVin();
		String iccid = msg.getIccid();
		Integer flowId = msg.getFlowId();
		LocalDateTime  requestTime = msg.getLoginTime();
		
		channelManager.add(iccid, ctx.channel());
		Vehicle vehicle = null;
		vehicle = vehicleService.findByVin(vin);
		if(vehicle !=null && vin.equals(vehicle.getIccid())){
			CommonRespMsg resp = CommonRespMsg.success(msg, flowId,requestTime);
			write(ctx,resp);
		}else {
			CommonRespMsg resp = CommonRespMsg.failure(msg, flowId,requestTime);
			write(ctx,resp);
		}
		
	}


}
