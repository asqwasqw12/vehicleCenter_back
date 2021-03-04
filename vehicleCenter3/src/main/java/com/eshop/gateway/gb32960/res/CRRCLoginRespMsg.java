package com.eshop.gateway.gb32960.res;


import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;
import com.eshop.gateway.gb32960.pojo.req.CRRCLoginMsg;


import io.netty.buffer.ByteBuf;

public class CRRCLoginRespMsg extends GB32960DataPacket {
    
	private String  vin;   //VIN码    
    private  String iccid;//ICCID    

    public void setVin(String vin) {
    	this.vin = vin;
    }
    
    public String getVin() {
    	return vin;
    }

    
    public void setIccid(String iccid) {
    	this.iccid = iccid;
    }
    
    public String getIccid() {
    	return iccid;
    }
    
    
    @Override
    public ByteBuf toByteBufMsg() {
        ByteBuf bb = super.toByteBufMsg();
        bb.writeBytes(vin.getBytes(gb32960Const.ASCII_CHARSET));//vin
        bb.writeBytes(iccid.getBytes(gb32960Const.ASCII_CHARSET));//iccid
        return bb;
    }
    
    public static CRRCLoginRespMsg setVinAndIccid(CRRCLoginMsg msg,String vin,String iccid) {
    	CRRCLoginRespMsg resp = new CRRCLoginRespMsg();
    	resp.getHeader().setRequestType(gb32960Const.CRRC_VIN_SET);
        resp.getHeader().setResponseTag(gb32960Const.RESPONSE_SUCCESS);//设置应答标志
        resp.getHeader().setVin(vin); //设置唯一识别码
        resp.getHeader().setEncrypTionType(gb32960Const.ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(37); //设置数据长度
        resp.setVin(vin); //设置iccid
        resp.setIccid(iccid); //设置iccid
        return resp;
    }
    

}
