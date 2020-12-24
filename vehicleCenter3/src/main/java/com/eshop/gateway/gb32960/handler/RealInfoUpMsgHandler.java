package com.eshop.gateway.gb32960.handler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.eshop.gateway.gb32960.pojo.req.RealInfoUpMsg;
import com.eshop.gateway.gb32960.res.CommonRespMsg;
import com.eshop.pojo.Vehicle;
import com.eshop.pojo.VehicleDevice;
import com.eshop.service.VehicleService;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealInfoUpMsgHandler extends BaseHandler<RealInfoUpMsg>{
	
	@Autowired
    @Qualifier("workerGroup")
    private NioEventLoopGroup workerGroup;
	
	@Autowired
	VehicleService vehicleService;
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, RealInfoUpMsg msg) throws Exception {
		
		
		
		//log.debug(msg.toString());
		System.out.println("LocationMsgHandler.msg:"+msg.toString());
		
		Vehicle vehicle = vehicleService.findByVin(msg.getHeader().getVin());
		
		if(vehicle != null) {
			Long vehicleId = vehicle.getId();
			if (msg.getLocationData()!= null) {
				msg.getLocationData().setVehicleId(vehicleId);
				//保存位置信息到mysql
				locationService.save(location);
				Date now =new Date();
				//location.setCreateTime(now);
				
				//保存位置信息到Redis
				locationInRedisService.save(location);
			}
			if(msg.getAlarmData()!=null) {
				
			}
			if(msg.getDriveMotorDatas()!=null) {
				
			}
		}
        CommonRespMsg resp = CommonRespMsg.success(msg, getSerialNumber(ctx.channel()));
        workerGroup.execute(() -> write(ctx, resp));//直接write是由businessGroup执行，换成workerGroup写可以少一些判断逻辑，略微提升性能
	}
        

}
