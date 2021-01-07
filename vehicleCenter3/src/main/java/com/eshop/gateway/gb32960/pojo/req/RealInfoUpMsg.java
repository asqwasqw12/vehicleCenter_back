package com.eshop.gateway.gb32960.pojo.req;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.tomcat.util.buf.StringUtils;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.AlarmData;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;
import com.eshop.gateway.gb32960.pojo.DriveMotorData;
import com.eshop.gateway.gb32960.pojo.EngineData;
import com.eshop.gateway.gb32960.pojo.ExtremeData;
import com.eshop.gateway.gb32960.pojo.FuelCellData;
import com.eshop.gateway.gb32960.pojo.LocationData;
import com.eshop.gateway.gb32960.pojo.RunData;
import com.eshop.gateway.gb32960.pojo.SubSystemTemperatureData;
import com.eshop.gateway.gb32960.pojo.SubSystemVoltageData;

import cn.hutool.core.date.DateTime;
import io.netty.buffer.ByteBuf;

public class RealInfoUpMsg extends GB32960DataPacket{
	
	private LocalDateTime sampleTime;//采样时间
	
	private ZonedDateTime sampleZonedTime;//采样时间

	//整车运行数据
    private RunData runData;

    //驱动电机个数
    private Short driveMotorCount;

    //驱动电机数据列表
    private List<DriveMotorData> driveMotorDatas;

    //燃料电池数据
    private FuelCellData fuelCellData;

    //发动机数据
    private EngineData engineData;

    //位置数据
    private LocationData locationData;

    //极值数据
    private ExtremeData extremeData;

    //报警数据
    private AlarmData alarmData;

    //可充电储能装置电压数据个数
    private Short subsystemVoltageCount;

    //可充电储能装置电压数据列表
    private List<SubSystemVoltageData> subSystemVoltageDatas;

    //可充电储能装置温度数据个数
    private Short subsystemTemperatureCount;

    //可充电储能装置温度数据列表
    private List<SubSystemTemperatureData> subSystemTemperatures;

    public void setSampleTime(LocalDateTime sampleTime) {
  	  this.sampleTime = sampleTime;
    }

    public LocalDateTime getSampleTime() {
  	  return this.sampleTime;
    }

    public void setSampleZonedTime(ZonedDateTime sampleZonedTime) {
    	  this.sampleZonedTime = sampleZonedTime;
      }

      public ZonedDateTime getSampleZonedTime() {
    	  return this.sampleZonedTime;
      }
    
    public RunData getRunData() {
        return runData;
    }

    public void setRunData(RunData runData) {
        this.runData = runData;
    }

    public Short getDriveMotorCount() {
        return driveMotorCount;
    }

    public void setDriveMotorCount(Short driveMotorCount) {
        this.driveMotorCount = driveMotorCount;
    }

    public List<DriveMotorData> getDriveMotorDatas() {
        return driveMotorDatas;
    }

    public void setDriveMotorDatas(List<DriveMotorData> driveMotorDatas) {
        this.driveMotorDatas = driveMotorDatas;
    }

    public FuelCellData getFuelCellData() {
        return fuelCellData;
    }

    public void setFuelCellData(FuelCellData fuelCellData) {
        this.fuelCellData = fuelCellData;
    }

    public EngineData getEngineData() {
        return engineData;
    }

    public void setEngineData(EngineData engineData) {
        this.engineData = engineData;
    }

    public LocationData getLocationData() {
        return locationData;
    }

    public void setLocationData(LocationData locationData) {
        this.locationData = locationData;
    }

    public ExtremeData getExtremeData() {
        return extremeData;
    }

    public void setExtremeData(ExtremeData extremeData) {
        this.extremeData = extremeData;
    }

    public AlarmData getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(AlarmData alarmData) {
        this.alarmData = alarmData;
    }

    public Short getSubsystemVoltageCount() {
        return subsystemVoltageCount;
    }

    public void setSubsystemVoltageCount(Short subsystemVoltageCount) {
        this.subsystemVoltageCount = subsystemVoltageCount;
    }

    public List<SubSystemVoltageData> getSubSystemVoltageDatas() {
        return subSystemVoltageDatas;
    }

    public void setSubSystemVoltageDatas(List<SubSystemVoltageData> subSystemVoltageDatas) {
        this.subSystemVoltageDatas = subSystemVoltageDatas;
    }

    public Short getSubsystemTemperatureCount() {
        return subsystemTemperatureCount;
    }

    public void setSubsystemTemperatureCount(Short subsystemTemperatureCount) {
        this.subsystemTemperatureCount = subsystemTemperatureCount;
    }

    public List<SubSystemTemperatureData> getSubSystemTemperatures() {
        return subSystemTemperatures;
    }

    public void setSubSystemTemperatures(List<SubSystemTemperatureData> subSystemTemperatures) {
        this.subSystemTemperatures = subSystemTemperatures;
    }

    public RealInfoUpMsg(ByteBuf byteBuf) {
    	super(byteBuf);
    }
    
    @Override
    public void parseBody() {
    	
    	this.sampleZonedTime = ZonedDateTime.of((this.payload.readByte()+ 2000),this.payload.readByte(),this.payload.readByte(),
        		this.payload.readByte(),this.payload.readByte(),this.payload.readByte(),0,gb32960Const.ZONE_UTC8);
    	this.sampleTime = sampleZonedTime.toLocalDateTime();

    	//
    	try {
            //中断标识
        	Boolean interrupt = false;
    		while(payload.readableBytes()>0) {
    			int infoType = payload.readUnsignedByte(); //获取信息类型
    			switch(infoType) {
    			case 0x01:
    				interrupt = parseRunData(payload); //解析整车数据
    				break;
    			case 0x02:
    				interrupt = parseDriveMotorData(payload); //解析驱动电机数据
    				break;
    			case 0x03:
    				interrupt = parseFuelCellData(payload);  //解析燃料电池数据
    			case 0x04:
    				interrupt = parseEngineData(payload); //解析发动机数据
    				break;
    			case 0x05:
    				interrupt = parseLoactionData(payload); //解析车辆位置数据
    				break;
    			case 0x06:
    				interrupt = parseExtremeData(payload); //解析极值数据
    				break;
    			case 0x07:
    				interrupt = parseAlarmData(payload); //解析报警数据
    				break;
    			case 0x08:
    				interrupt = parseStorageVoltage(payload); //解析可充电储能装置电压数据
    				break;
    			case 0x09:
    				interrupt = parseStorageTemp(payload); //解析可充电储能装置温度数据
    			default:									
    					if(payload.readableBytes() > 2 ) {
    						int length = payload.readUnsignedShort();
    						if(payload.readableBytes() < length ) {
    							interrupt = true;
    							break;
    						}
    						payload.readBytes(new byte[length]);	//解析自定义数据
    					}
    				break;
    			}
    		}    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
  //解析整车数据
    private Boolean parseRunData(ByteBuf buf) {
    	//整车数据20字节，小于20字节直接返回
    	if(buf.readableBytes() <20) {
    		return true;
    	}
    	RunData runData =new RunData();
    	runData.setVin(header.getVin());
    	runData.setRunStatus(buf.readUnsignedByte());
    	runData.setChargeStatus(buf.readUnsignedByte());
    	runData.setOperationMode(buf.readUnsignedByte());
    	runData.setSpeed(buf.readUnsignedShort());
    	runData.setMileAge(buf.readUnsignedInt());
    	runData.setTotalVoltage(buf.readUnsignedShort());
    	runData.setTotalCurrent(buf.readUnsignedShort());
    	runData.setSoc(buf.readUnsignedByte());
    	runData.setDcStatus(buf.readUnsignedByte());
    	runData.setGears(buf.readUnsignedByte());
    	runData.setInsulationResistance(buf.readUnsignedShort());
    	runData.setThrottle(buf.readUnsignedByte());
    	runData.setBrake(buf.readUnsignedByte());
    	this.runData = runData;
    	return false;
    }
    
    //解析驱动电机数据
    private Boolean parseDriveMotorData(ByteBuf buf){
    	
    	Short count = buf.readUnsignedByte();
    	if(count >253 || (buf.readableBytes() < count *12 ) || count == 0 ) {
    		return true;
    	}
    	List<DriveMotorData> list = new ArrayList<DriveMotorData>();
    	for(int i=0;i<count;i++) {
    		DriveMotorData data = new DriveMotorData();
    		data.setVin(header.getVin());
    		data.setNum(buf.readUnsignedByte());
    		data.setStatus(buf.readUnsignedByte());
    		data.setControllerTemperature(buf.readUnsignedByte());
    		data.setSpeed(buf.readUnsignedShort());
    		data.setTorque(buf.readUnsignedShort());
    		data.setTemperature(buf.readUnsignedByte());
    		data.setControllerInputVoltage(buf.readUnsignedShort());
    		data.setControllerBusCurrent(buf.readUnsignedShort());
    		list.add(data);
    	}
    	driveMotorDatas = list;
    	return false;
    }
    
   //解析燃料电池数据
    private Boolean parseFuelCellData(ByteBuf buf){
    	if(buf.readableBytes() <18) {
    		return true;
    	}
    	FuelCellData fuelCellData = new FuelCellData();
    	fuelCellData.setVoltage(buf.readUnsignedShort());
    	fuelCellData.setCurrent(buf.readUnsignedShort());
    	fuelCellData.setFuelConsumption(buf.readUnsignedShort());
    	int count =  buf.readUnsignedShort();
    	fuelCellData.setTemperatureProbeCount(count);
        List<Short> list = new ArrayList<Short>();
    	for(int i= 0 ;i<count;i++) {
    		list.add(buf.readUnsignedByte());
    	}
    	fuelCellData.setProbeTemperature(list);
    	fuelCellData.setHydrogenSystemMaxTemperature(buf.readUnsignedShort());
    	fuelCellData.setHydrogenSystemTemperatureProbeNum(buf.readUnsignedByte());
    	fuelCellData.setHydrogenSystemMaxConcentration(buf.readUnsignedShort());
    	fuelCellData.setHydrogenSystemConcentrationProbeNum(buf.readUnsignedByte());
    	fuelCellData.setHydrogenSystemMaxPressure(buf.readUnsignedShort());
    	fuelCellData.setHydrogenSystemPressureProbeNum(buf.readUnsignedByte());
    	fuelCellData.setDcStatus(buf.readUnsignedByte());
    	this.fuelCellData = fuelCellData;
    	return false;
    }
    
   //解析发动机数据
   private Boolean parseEngineData(ByteBuf buf){
	   if(buf.readableBytes() <5) {
   		return true;
   	}
	   EngineData engineData = new EngineData();
	   engineData.setStatus(buf.readUnsignedByte());
	   engineData.setCrankshaftSpeed(buf.readUnsignedShort());
	   engineData.setFuelConsumption(buf.readUnsignedShort());
	   this.engineData = engineData;
	   return false;
    }
   
 //解析车辆位置数据
   private Boolean parseLoactionData(ByteBuf buf){
	   if(buf.readableBytes() <9) {
	   		return true;
	   	}
	   LocationData locationData = new LocationData();
	   locationData.setVin(header.getVin());
	   locationData.setSampleTime(sampleTime);
	   locationData.setStatus(buf.readUnsignedByte());
	   locationData.setLongitude(buf.readUnsignedInt());
	   locationData.setLatitude(buf.readUnsignedInt());
	   
	   
	 //定位状态，bit0,0:有效定位，1：无效定位，bit1,0:北纬，1南纬，bit2,0:东经，1：西经
	   if((locationData.getStatus() & 0x01)>0) {
		   locationData.setValid(true);
	   }else {
		   locationData.setValid(false);
	   }
	   locationData.setLongitudeDataType(locationData.getStatus() & 0x04);
	   locationData.setLatitudeDataType(locationData.getStatus() & 0x02);
	   this.locationData = locationData;
	   return false;
   }
   
 //解析极值数据
   private Boolean parseExtremeData(ByteBuf buf){
	   if(buf.readableBytes() <14) {
	   		return true;
	   	}
	   ExtremeData extremeData = new ExtremeData();
	   extremeData.setMaxVoltageSystemNum(buf.readUnsignedByte());
	   extremeData.setMaxVoltageBatteryNum(buf.readUnsignedByte());
	   extremeData.setBatteryMaxVoltage(buf.readUnsignedShort());
	   extremeData.setMinVoltageSystemNum(buf.readUnsignedByte());
	   extremeData.setMinVoltageBatteryNum(buf.readUnsignedByte());
	   extremeData.setBatteryMinVoltage(buf.readUnsignedShort());
	   extremeData.setMaxTemperatureSystemNum(buf.readUnsignedByte());
	   extremeData.setMaxTemperatureNum(buf.readUnsignedByte());
	   extremeData.setMaxTemperature(buf.readUnsignedByte());
	   extremeData.setMinTemperatureSystemNum(buf.readUnsignedByte());
	   extremeData.setMinTemperatureNum(buf.readUnsignedByte());
	   extremeData.setMinTemperature(buf.readUnsignedByte());
	   this.extremeData = extremeData;
	   return false;
	   
   }
   
 //解析报警数据
   private Boolean parseAlarmData(ByteBuf buf){
	   AlarmData alarmData = new AlarmData();
	   alarmData.setLevel(buf.readUnsignedByte());
	   Long generalAlarm = buf.readUnsignedInt();
	   alarmData.setAlarmInfo(generalAlarm);
	   parseGeneralAlarm(generalAlarm,alarmData);//解析通过报警信息
	   
	   //可充电储能装置故障列表
	   alarmData.setDeviceFailureCount(buf.readUnsignedByte());
	   List<Long> deviceFailureCodeList = new ArrayList<Long>();
	   StringBuffer deviceFailureCodes = new StringBuffer();
	   for(int i= 0 ; i< alarmData.getDeviceFailureCount();i++) {
		   Long alarmCode = buf.readUnsignedInt();
		   deviceFailureCodeList.add(alarmCode);
		   deviceFailureCodes.append(alarmCode);
		   deviceFailureCodes.append(",");
	   }
	   if(deviceFailureCodeList.size()>0) {
		   alarmData.setDeviceFailureCodeList( deviceFailureCodeList);
		   alarmData.setDeviceFailureCodes(deviceFailureCodes.toString());
		   //String deviceFailuerCodes = new String();
		   //deviceFailuerCodes = ArrayUtils.toString(deviceFailureCodeList,",");//数组转为字符串
		   //alarmData.setDeviceFailureCodes(deviceFailuerCodes);
		   
	   }
	   //驱动电机故障列表
	   alarmData.setDriveMotorFailureCount(buf.readUnsignedByte());
	   List<Long> driveMotorFailureCodeList = new ArrayList<Long>();
	   StringBuffer driveMotorFailureCodes = new StringBuffer();
	   for(int i= 0 ; i< alarmData.getDriveMotorFailureCount();i++) {
		    Long alarmCode = buf.readUnsignedInt();
	    	driveMotorFailureCodeList.add(alarmCode);
	    	driveMotorFailureCodes.append(alarmCode);
	    	driveMotorFailureCodes.append(",");
		   }
	   if(driveMotorFailureCodeList.size()>0) {		   
		   alarmData.setDriveMotorFailureCodeList(driveMotorFailureCodeList);
		   alarmData.setDriveMotorFailureCodes(driveMotorFailureCodes.toString());
		   //String driveMotorFailureCodes = new String();
		   //driveMotorFailureCodes = ArrayUtils.toString(driveMotorFailureCodeList,",");//数组转为字符串
		   //alarmData.setDriveMotorFailureCodes(driveMotorFailureCodes);
	   }
	   
	   
	   //发动机故障列表
	   alarmData.setEngineFailureCount(buf.readUnsignedByte());
	   List<Long> engineFailureCodeList = new ArrayList<Long>();
	   StringBuffer engineFailureCodes = new StringBuffer();
	   for(int i=0; i< alarmData.getEngineFailureCount();i++) {
		   Long alarmCode = buf.readUnsignedInt();
		   engineFailureCodeList.add(alarmCode);
		   engineFailureCodes.append(alarmCode);
		   engineFailureCodes.append(",");
	   }
	   if(engineFailureCodeList.size()>0) {
		   alarmData.setEngineFailureCodeList(engineFailureCodeList);
		   alarmData.setEngineFailureCodes(engineFailureCodes.toString());
		  // String engineFailureCodes = new String();
		  //engineFailureCodes = ArrayUtils.toString(engineFailureCodeList,","); //数组转为字符串
		  // alarmData.setEngineFailureCodes(engineFailureCodes);
	   }
	   
	   
	   //其他故障列表
	   alarmData.setOtherFailureCount(buf.readUnsignedByte());
	   List<Long> otherFailureCodeList = new ArrayList<>();
	   StringBuffer otherFailureCodes = new StringBuffer();
	   for (int i=0; i< alarmData.getOtherFailureCount();i++) {
		   Long alarmCode = buf.readUnsignedInt();
		   otherFailureCodeList.add(alarmCode);
		   otherFailureCodes.append(alarmCode);
		   otherFailureCodes.append(",");
	   }
	   if(otherFailureCodeList.size()>0) {
		   alarmData.setOtherFailureCodeList(otherFailureCodeList);
		   alarmData.setOtherFailureCodes(otherFailureCodes.toString());
		   //String otherFailureCodes = new String();
		   //otherFailureCodes = ArrayUtils.toString(otherFailureCodeList,","); //数组转为字符串
		   //alarmData.setOtherFailureCodes(otherFailureCodes);
	   }
	  
	   this.alarmData = alarmData;
	   return false;
   }
   
   private void parseGeneralAlarm(Long generalAlarm,AlarmData alarmData) {
	   alarmData.setTemperatureDifferential((generalAlarm & 0x01)>0);
	   alarmData.setBatteryHighTemperature(((generalAlarm>>1) & 0x01)>0);
	   alarmData.setDeviceTypeOverVoltage(((generalAlarm>>2) & 0x01)>0);
	   alarmData.setDeviceTypeUnderVoltage(((generalAlarm>>3) & 0x01)>0);
	   alarmData.setSocLow(((generalAlarm>>4) & 0x01)>0);
	   alarmData.setMonomerBatteryOverVoltage(((generalAlarm>>5) & 0x01)>0);
	   alarmData.setMonomerBatteryUnderVoltage(((generalAlarm>>6) & 0x01)>0);
	   alarmData.setSocHigh(((generalAlarm>>7) & 0x01)>0);
	   alarmData.setSocJump(((generalAlarm>>8) & 0x01)>0);
	   alarmData.setDeviceTypeDontMatch(((generalAlarm>>9) & 0x01)>0);
	   alarmData.setInsulation(((generalAlarm>>10) & 0x01)>0);
	   alarmData.setDcTemperature(((generalAlarm>>11) & 0x01)>0);
	   alarmData.setBrakingSystem(((generalAlarm>>12) & 0x01)>0);
	   alarmData.setDcStatus(((generalAlarm>>13) & 0x01)>0);
	   alarmData.setDriveMotorControllerTemperature(((generalAlarm>>14) & 0x01)>0);
	   alarmData.setHighPressureInterlock(((generalAlarm>>15) & 0x01)>0);
	   alarmData.setDriveMotorTemperature(((generalAlarm>>16) & 0x01)>0);
	   alarmData.setDeviceTypeOverFilling(((generalAlarm>>17) & 0x01)>0);
	   
   }
 //解析可充电储能装置电压数据
   private Boolean parseStorageVoltage(ByteBuf buf){

	   Short count = buf.readUnsignedByte();
   	if(count >250 || count == 0 ) {
   		return true;
   	}
   	
   	List<SubSystemVoltageData> list = new ArrayList<SubSystemVoltageData>();
   	for(int i=0;i<count;i++) {
   		SubSystemVoltageData data= new SubSystemVoltageData();
   		data.setNum(buf.readUnsignedByte());
   		data.setVoltage(buf.readUnsignedShort());
   		data.setCurrent(buf.readUnsignedShort());
   		data.setCellCount(buf.readUnsignedShort());
   		data.setBatteryNumber(buf.readUnsignedShort());
   		data.setBatteryCount(buf.readUnsignedByte());
   		List<Integer> cellVoltagesList = new ArrayList<Integer>();
   		for(int j = 0; j<data.getBatteryCount();j++) {
   			cellVoltagesList.add(buf.readUnsignedShort());
   		}
   		data.setCellVoltages(cellVoltagesList);  	
   		list.add(data);
   	}
   	subsystemVoltageCount=count;
   	subSystemVoltageDatas = list;
   	return false;
   }
   
 //解析可充电储能装置温度数据
   private Boolean parseStorageTemp(ByteBuf buf) {
	   
	   Short count = buf.readUnsignedByte();
	   	if(count >250 || count == 0 ) {
	   		return true;
	   	}
	   	List<SubSystemTemperatureData> list = new ArrayList<SubSystemTemperatureData>();
	   	for(int i=0;i<count; i++) {
	   		SubSystemTemperatureData data = new SubSystemTemperatureData();
	   		data.setNum(buf.readUnsignedByte());
	   		data.setTemperatureProbeCount(buf.readUnsignedShort());
	   		List<Short> probeList = new ArrayList<Short>();
	   		for(int j=0; j<data.getTemperatureProbeCount();j++) {
	   			probeList.add(buf.readUnsignedByte());
	   		}
	   		data.setProbeTemperatures(probeList);
	   		list.add(data);
	   	}
	    subsystemTemperatureCount = count;
	    subSystemTemperatures = list;
	    return false;
   }
}
