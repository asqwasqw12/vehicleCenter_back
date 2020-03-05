package com.eshop.jt808.pojo.req;

import com.eshop.jt808.pojo.DataPacket;
import com.eshop.jt808.util.BCD;

import io.netty.buffer.ByteBuf;

//@Description:位置上报包
public class LocationMsg extends DataPacket {
	
	private int alarm; //告警信息 4字节
    private int statusField;//状态 4字节
    private float latitude;//纬度 4字节
    private float longitude;//经度 4字节
    private short elevation;//海拔高度 2字节
    private short speed; //速度 2字节
    private short direction; //方向 2字节
    private String time; //时间 6字节BCD
    
    public void setAlarm(int alarm) {
 	   this.alarm = alarm;
    }
    
    public int getAlarm() {
 	   return this.alarm;
    }

    public void setStatusField(int statusField) {
 	   this.statusField = statusField;
    }
    
    public int getStatusField() {
 	   return this.statusField;
    }
    
    public void setLatitude(float latitude) {
 	   this.latitude = latitude;
    }
    
    public float getLatitude() {
 	   return this.latitude;
    }
    
    public void setLongitude(float longitude) {
 	   this.longitude = longitude;
    }
    
    public float getLongitude() {
 	   return this.longitude;
    }
    
    public void setElevation(short elevation) {
 	   this.elevation = elevation;
    }
    
    public short getElevation() {
 	   return this.elevation;
    }
    
    public void setSpeed(short speed) {
 	   this.speed = speed;
    }
    
    public short getSpeed() {
 	   return this.speed;
    }
    
    public void setDirection(short direction) {
 	   this.direction = direction;
    }
    
    public short getDirection() {
 	   return this.direction;
    }
    
    public void setTime(String time) {
 	   this.time = time;
    }
    
    public String getTime() {
 	   return this.time;
    }

    public LocationMsg(ByteBuf byteBuf) {
        super(byteBuf);
    }
    
    @Override
    public void parseBody() {
        ByteBuf bb = this.payload;
        this.setAlarm(bb.readInt());
        this.setStatusField(bb.readInt());
        this.setLatitude(bb.readUnsignedInt() * 1.0F / 1000000);
        this.setLongitude(bb.readUnsignedInt() * 1.0F / 1000000);
        this.setElevation(bb.readShort());
        this.setSpeed(bb.readShort());
        this.setDirection(bb.readShort());
        this.setTime(BCD.toBcdTimeString(readBytes(6)));
    }
}
