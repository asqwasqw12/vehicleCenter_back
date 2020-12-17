package com.eshop.gateway.gb32960.pojo.req;

import java.time.ZonedDateTime;

import com.eshop.gateway.gb32960.pojo.DataPacket;

public class RealInfoUpMsg extends DataPacket{
	
	private ZonedDateTime sampleTime;//采样时间

	//整车数据
    private VehicleData vehicleData;

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
    private List<SubSystemTemperature> subSystemTemperatures;

    public static BeanTime getProducer() {
        return producer;
    }

    public BeanTime getBeanTime() {
        return beanTime;
    }

    public void setBeanTime(BeanTime beanTime) {
        this.beanTime = beanTime;
    }

    public VehicleData getVehicleData() {
        return vehicleData;
    }

    public void setVehicleData(VehicleData vehicleData) {
        this.vehicleData = vehicleData;
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

    public List<SubSystemTemperature> getSubSystemTemperatures() {
        return subSystemTemperatures;
    }

    public void setSubSystemTemperatures(List<SubSystemTemperature> subSystemTemperatures) {
        this.subSystemTemperatures = subSystemTemperatures;
    }

    @Override
    public void parseBody() {

        RealTimeData realTimeData = new RealTimeData();
        BeanTime beanTime = producer.decode(byteBuf);
        realTimeData.setBeanTime(beanTime);
        while (byteBuf.isReadable()) {
            decodeByType(byteBuf, realTimeData);
        }
        return realTimeData;
    }
	
}
