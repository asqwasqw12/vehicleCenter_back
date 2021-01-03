package com.eshop.gateway.gb32960.handler;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eshop.gateway.gb32960.pojo.LocationData;
import com.eshop.gateway.gb32960.pojo.req.RealInfoUpMsg;
import com.eshop.gateway.gb32960.res.CommonRespMsg;
import com.eshop.gateway.gb32960.service.LocationDataInRedisService;
import com.eshop.gateway.gb32960.service.LocationDataService;
import com.eshop.pojo.Vehicle;
import com.eshop.service.VehicleService;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RealInfoUpMsgHandler extends BaseHandler<RealInfoUpMsg>{
	
	@Autowired
    @Qualifier("workerGroup")
    private NioEventLoopGroup workerGroup;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	LocationDataService locationDataService;
	
	@Autowired
	LocationDataInRedisService locationDataInRedisService;
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, RealInfoUpMsg msg) throws Exception {
		
		
		
		//log.debug(msg.toString());
		System.out.println("LocationMsgHandler.msg:"+msg.toString());
		
		Vehicle vehicle = vehicleService.findByVin(msg.getHeader().getVin());
		ZonedDateTime  sampleTime = msg.getSampleTime();
		
		if(vehicle != null) {
			Long vehicleId = vehicle.getId();
			if (msg.getLocationData()!= null) {
				LocationData locationData =msg.getLocationData();
				locationData.setVehicleId(vehicleId);
				//保存位置信息到mysql
				locationDataService.save(locationData);
				Date now =new Date();
				//location.setCreateTime(now);
				
				//保存位置信息到Redis
				locationDataInRedisService.save(locationData);
			}
			if(msg.getAlarmData()!=null) {
				
			}
			if(msg.getDriveMotorDatas()!=null) {
				
			}
		}
        CommonRespMsg resp = CommonRespMsg.success(msg, getSerialNumber(ctx.channel()),sampleTime);
        workerGroup.execute(() -> write(ctx, resp));//直接write是由businessGroup执行，换成workerGroup写可以少一些判断逻辑，略微提升性能
	}
        

}
