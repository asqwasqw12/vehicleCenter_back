package com.eshop.jt808.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.eshop.jt808.pojo.Location;

public interface LocationDao {
	
	@Insert("insert into location_info(terminalPhone,alarm,statusField,latitude,longitude,elevation,speed,direction,time)"
			+"values(#{terminalPhone},#{alarm},#{statusField},#{latitude},#{longitude},#{elevation},#{speed},#{direction},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int  saveLocation(Location location);

}