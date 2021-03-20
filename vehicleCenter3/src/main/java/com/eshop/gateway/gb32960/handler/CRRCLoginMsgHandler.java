package com.eshop.gateway.gb32960.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshop.gateway.gb32960.config.ChannelManager;
import com.eshop.gateway.gb32960.pojo.req.CRRCLoginMsg;
import com.eshop.gateway.gb32960.res.CRRCLoginRespMsg;
import com.eshop.gateway.gb32960.res.CommonRespMsg;
import com.eshop.pojo.Vehicle;
import com.eshop.service.VehicleService;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ChannelHandler.Sharable
public class CRRCLoginMsgHandler extends BaseHandler<CRRCLoginMsg>{
	
	@Autowired
    private ChannelManager channelManager;
	@Autowired
	private VehicleService vehicleService;
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, CRRCLoginMsg msg) throws Exception {
		
		String vin = msg.getHeader().getVin();
		String iccid = msg.getIccid();
		String terminalNum = msg.getTerminalNum();
		Vehicle vehicle = vehicleService.findByTerminalNum(terminalNum);
		if(vehicle !=null) {
			if(iccid.equals(vehicle.getIccid()) && vin.equals(vehicle.getVin())){
				Boolean channelAddFlag = channelManager.add(vin, ctx.channel());
				if(!channelAddFlag) {
					log.info("CRRC终端登入时，channel添加失败");
				}else {
					CommonRespMsg resp = CommonRespMsg.success(msg);
					write(ctx,resp);
				}
			}else {
				CRRCLoginRespMsg resp = CRRCLoginRespMsg.setVinAndIccid(msg, vehicle.getVin(), vehicle.getIccid());
				write(ctx,resp);
			}
		}else {
			CommonRespMsg resp = CommonRespMsg.failure(msg);
			write(ctx,resp);
			ctx.close();
		}
	}
}
