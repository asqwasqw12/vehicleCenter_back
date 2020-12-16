package com.eshop.pojo;

import java.util.Date;

public class VehicleOutStorage  extends BasePojo {
	private Long vehicleId;	//车辆编号
	private Long userId;	//经办人ID
	private Date outTime; //出库时间
	private String reason; //出库原因
	private String destination; //目的地
	private Long clientId; //客户Id
	private Long companyId; //客户公司Id
	//非数据库属性
	private String operator;	//出库经办人
	private Client client;  //客户信息
	private Company company; //客户公司信息
	private Vehicle vehicle;//车辆信息 
	
	public void setVehicleId(Long vehicleId) {
    	this.vehicleId = vehicleId;
    }
   
    public Long getVehicleId() {
    	return vehicleId;
    }
    
	public void setUserId(Long userId) {
    	this.userId = userId;
    }
   
    public Long getUserId() {
    	return userId;
    }
    
    public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	
	public String getReason() {
			return reason;
		}

	public void setReason(String reason) {
			this.reason = reason;
		}
	
	public String getDestination() {
		return destination;
	}

    public void setDestination(String destination) {
		this.destination = destination;
	}
    
    public void setClientId(Long clientId) {
    	this.clientId = clientId;
    }
   
    public Long getClientId() {
    	return clientId;
    }
    
    public void setCompanyId(Long companyId) {
    	this.companyId = companyId;
    }
   
    public Long getCompanyId() {
    	return companyId;
    }
    
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Company getCompany() {
		return company;
	}
	
    public Vehicle getVehicle() {
    	return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
    	this.vehicle = vehicle;
    }
}
