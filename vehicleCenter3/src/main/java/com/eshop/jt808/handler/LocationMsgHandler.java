package com.eshop.jt808.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eshop.jt808.pojo.Location;
import com.eshop.jt808.pojo.req.LocationMsg;
import com.eshop.jt808.pojo.res.CommonRespMsg;
import com.eshop.jt808.service.LocationService;
import com.eshop.pojo.VehicleDevice;
import com.eshop.service.VehicleDeviceService;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

//@Description: 位置消息->CommonResp
@Slf4j
@Component
@ChannelHandler.Sharable
public class LocationMsgHandler extends BaseHandler<LocationMsg>{
	@Autowired
    @Qualifier("workerGroup")
    private NioEventLoopGroup workerGroup;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	VehicleDeviceService vehicleDeviceService;
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, LocationMsg msg) throws Exception {
        //log.debug(msg.toString());
		System.out.println("LocationMsgHandler.msg:"+msg.toString());
		Location location = Location.parseFromLocationMsg(msg);
		Long vehicleId = vehicleDeviceService.findByTerminalPhone(location.getTerminalPhone())
		//保存位置信息到mysql
		locationService.saveLocation(location);
		
        CommonRespMsg resp = CommonRespMsg.success(msg, getSerialNumber(ctx.channel()));
        workerGroup.execute(() -> write(ctx, resp));//直接write是由businessGroup执行，换成workerGroup写可以少一些判断逻辑，略微提升性能
    }

}
