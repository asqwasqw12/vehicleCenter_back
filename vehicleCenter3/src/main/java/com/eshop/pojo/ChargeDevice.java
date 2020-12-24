package com.eshop.pojo;

public class ChargeDevice extends BasePojo{
	private String systemId; //系统编号
	private String type; //装置类型 01：磷酸铁锂电池，02：锰酸锂电池，03：钴酸锂电池，04：三元材料电池，05：聚合物锂离子电池，06：超级电容，07：钛酸锂电池，FC：燃料电池，FF:其他车载储能装置
	private Float totalEnergy; //总能量
	private String coolingMethod;//冷却方式
	
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	
	public String getSystemId() {
		return systemId;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setTotalEnergy(Float totalEnergy) {
		this.totalEnergy = totalEnergy;
	}
	
	public Float getTotalEnergy() {
		return totalEnergy;
	}
	
	public void setCoolingMethod(String coolingMethod) {
		this.coolingMethod = coolingMethod;
	}
	
	public String getCoolingMethod() {
		return coolingMethod;
	}
}
