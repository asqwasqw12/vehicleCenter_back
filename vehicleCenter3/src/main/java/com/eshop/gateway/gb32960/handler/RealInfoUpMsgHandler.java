package com.eshop.gateway.gb32960.handler;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eshop.gateway.gb32960.pojo.AlarmData;
import com.eshop.gateway.gb32960.pojo.DriveMotorData;
import com.eshop.gateway.gb32960.pojo.EngineData;
import com.eshop.gateway.gb32960.pojo.ExtremeData;
import com.eshop.gateway.gb32960.pojo.FuelCellData;
import com.eshop.gateway.gb32960.pojo.LocationData;
import com.eshop.gateway.gb32960.pojo.RunData;
import com.eshop.gateway.gb32960.pojo.SubSystemTemperatureData;
import com.eshop.gateway.gb32960.pojo.SubSystemVoltageData;
import com.eshop.gateway.gb32960.pojo.req.RealInfoUpMsg;
import com.eshop.gateway.gb32960.res.CommonRespMsg;
import com.eshop.gateway.gb32960.service.AlarmDataService;
import com.eshop.gateway.gb32960.service.DriveMotorDataService;
import com.eshop.gateway.gb32960.service.EngineDataService;
import com.eshop.gateway.gb32960.service.ExtremeDataService;
import com.eshop.gateway.gb32960.service.FuelCellDataService;
import com.eshop.gateway.gb32960.service.LocationDataInRedisService;
import com.eshop.gateway.gb32960.service.LocationDataService;
import com.eshop.gateway.gb32960.service.RunDataService;
import com.eshop.gateway.gb32960.service.SubSystemTemperatureDataService;
import com.eshop.gateway.gb32960.service.SubSystemVoltageDataService;
import com.eshop.pojo.Vehicle;
import com.eshop.service.VehicleService;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ChannelHandler.Sharable
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
	
	@Autowired
	AlarmDataService alarmDataService;
	
	@Autowired
	DriveMotorDataService driveMotorDataService;
	
	@Autowired
	EngineDataService engineDataService;
	
	@Autowired
	ExtremeDataService extremeDataService;
	
	@Autowired
	FuelCellDataService fuelCellDataService;
	
	@Autowired
	RunDataService runDataService;
	
	@Autowired
	SubSystemTemperatureDataService subSystemTemperatureDataService;
	
	@Autowired
	SubSystemVoltageDataService subSystemVoltageDataService;
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, RealInfoUpMsg msg) throws Exception {
		
		
		
		//log.debug(msg.toString());
		System.out.println("LocationMsgHandler.msg:"+msg.toString());
		
		Vehicle vehicle = vehicleService.findByVin(msg.getHeader().getVin());
		LocalDateTime  sampleTime = msg.getSampleTime();
		
		if(vehicle != null) {
			System.out.println("Location:001  ");
			Long vehicleId = vehicle.getId();
			if (msg.getLocationData()!= null) {
				LocationData locationData =msg.getLocationData();
				locationData.setVehicleId(vehicleId);			
				locationDataService.save(locationData);//保存位置信息到mysql			
				locationDataInRedisService.save(locationData);//保存位置信息到Redis
			}
			System.out.println("Location:002  ");
			if(msg.getAlarmData()!=null) {
				AlarmData alarmData = msg.getAlarmData();
				alarmData.setVehicleId(vehicleId);
				alarmDataService.save(alarmData);//保存报警信息到mysql
			}
			System.out.println("Location:003  ");
			if(msg.getDriveMotorDatas() != null && msg.getDriveMotorDatas().size()>0) {
				List<DriveMotorData> list = new ArrayList<DriveMotorData>();
				list = msg.getDriveMotorDatas();
				for(DriveMotorData item : list) {
					item.setVehicleId(vehicleId);
					driveMotorDataService.save(item);//保存驱动电机信息到mysql
				}
			}
			System.out.println("Location:004  ");
			if(msg.getRunData() != null) {
				RunData runData = msg.getRunData();
				runData.setVehicleId(vehicleId);
				runDataService.save(runData); //保存运行数据到mysql
			}
			System.out.println("Location:005  ");
			if(msg.getEngineData() != null) {
				EngineData engineData = msg.getEngineData();
				engineData.setVehicleId(vehicleId);
				engineDataService.save(engineData);//保存引擎数据到mysql
			}
			if(msg.getExtremeData() != null) {
				ExtremeData extremeData = msg.getExtremeData();
				extremeData.setVehicleId(vehicleId);
				extremeDataService.save(extremeData);//保存极值数据到mysql
			}
			System.out.println("Location:006  ");
			if(msg.getFuelCellData() != null) {
				FuelCellData fuelCellData = msg.getFuelCellData();
				fuelCellData.setVehicleId(vehicleId);
				fuelCellDataService.save(fuelCellData);//保存燃料电池数据到mysql
			}
			System.out.println("Location:007  ");
			if(msg.getSubSystemTemperatureList() !=null && msg.getSubSystemTemperatureList().size()>0) {
				List<SubSystemTemperatureData> list = new ArrayList<SubSystemTemperatureData>();
				list = msg.getSubSystemTemperatureList();
				for(SubSystemTemperatureData item : list) {
					item.setVehicleId(vehicleId);
					subSystemTemperatureDataService.save(item);//保存充电储能装置温度数据到mysql
				}
				
			}
			System.out.println("Location:008  ");
			if(msg.getSubSystemVoltageDataList() !=null && msg.getSubSystemVoltageDataList().size()>0) {
				List<SubSystemVoltageData> list = new ArrayList<SubSystemVoltageData>();
				list = msg.getSubSystemVoltageDataList();
				for(SubSystemVoltageData item : list) {
					item.setVehicleId(vehicleId);
					subSystemVoltageDataService.save(item);//保存充电储能装置电压数据到mysql
				}
				
			}
		}
		System.out.println("Location:009  ");
        CommonRespMsg resp = CommonRespMsg.success(msg, getSerialNumber(ctx.channel()));
        workerGroup.execute(() -> write(ctx, resp));//直接write是由businessGroup执行，换成workerGroup写可以少一些判断逻辑，略微提升性能
	}
        

}
