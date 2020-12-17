package com.eshop.gateway.gb32960.pojo;

import java.util.Date;
import java.util.List;

public class FuelCellData {
	
	Long id; //
	
	private Long vehicleId;			//车辆Id
	
	private String terminalPhone; // 终端手机号
	
	private String vin; //车辆唯一识别码
	
    //燃料电池电压
    private Integer voltage;

    //燃料电池电流
    private Integer current;

    //燃料消耗率
    private Integer fuelConsumption;

    //燃料电池温度探针总数
    private Integer temperatureProbeCount;

    //探针温度值集合
    private List<Short> probeTemperature;

    //氢系统中最高温度
    private Integer hydrogenSystemMaxTemperature;

    //氢系统中最高温度探针代号
    private Short hydrogenSystemTemperatureProbeNum;

    //氢气最高浓度
    private Integer hydrogenSystemMaxConcentration;

    //氢气最高浓度传感器代号
    private Short hydrogenSystemConcentrationProbeNum;

    //氢气最高压力
    private Integer hydrogenSystemMaxPressure;

    //氢气最高压力传感器代号
    private Short hydrogenSystemPressureProbeNum;

    //高压DC/DC状态
    private Short dcStatus;
    
    private Date createTime;	//创建时间
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public void setVehicleId(Long vehicleId) {
    	this.vehicleId = vehicleId;
    }
    
    public Long getVehicleId() {
    	return this.vehicleId;
    }
    
    public void setTerminalPhone(String terminalPhone) {
    	this.terminalPhone = terminalPhone;
    }
    
   public String getTerminalPhone() {
	   return this.terminalPhone;
   }
   
   public void setVin(String vin) {
   	this.vin = vin;
   }
   
  public String getVin() {
	   return this.vin;
  }
  
  
  
  
  public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
