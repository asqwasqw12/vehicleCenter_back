package com.eshop.gateway.gb32960.config;

import java.nio.charset.Charset;
import java.time.ZoneId;
import sun.nio.cs.ext.GB18030;

import com.google.common.base.Charsets;

@SuppressWarnings("restriction")
public class gb32960Const {
	

    //默认时区为东八区
    public static final ZoneId ZONE_UTC8 = ZoneId.of("UTC+8");

    //消息起始符
    public static final int START_SYMBOL = 0x2323;

    // 命令标识
    public static final short VEHICLE_LOGIN = 0x01; //车辆登入
    public static final short REAL_INFO_UP = 0x02; //实时信息上报
    public static final short REISSUE_INFO_UP = 0x03; //补发实时信息上报
    public static final short VEHICLE_LOGOUT = 0x04; //车辆登出
    public static final short PLATFORM_LOGIN = 0x05; //平台登入
    public static final short PLATFORM_LOGOUT = 0x06; //平台登出
    
    // 应答标志
    public static final short RESPONSE_SUCCESS = 0x01; //接收到的信息正确
    public static final short RESPONSE_ERROR = 0x02; //设置未成功
    public static final short RESPONSE_VIN_DUPLICATE = 0x03;//VIN重复错误
    public static final short RESPONSE_ORDER = 0x04;//表示数据包为命令包，而非应答包
    
	 //默认英文字符集为ASCII
    public static final Charset ASCII_CHARSET = Charsets.US_ASCII;
    //默认中文字符集为GBK
    public static final Charset CHINESE_CHARSET = new GB18030();



}
