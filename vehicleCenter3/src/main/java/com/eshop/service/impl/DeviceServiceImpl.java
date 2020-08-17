package com.eshop.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.common.DateTimeUtils;
import com.eshop.common.FileUtil;
import com.eshop.common.page.MybatisPageHelper;
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.dao.DeviceMapper;
import com.eshop.pojo.Device;
import com.eshop.service.DeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class DeviceServiceImpl implements DeviceService {
	
	@Autowired
	DeviceMapper deviceMapper;
	
	@Override
	public int save(Device record) {
		if(record.getId() == null || record.getId() == 0) {
			return deviceMapper.insertSelective(record);
		}
		return deviceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Device record) {
		return deviceMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<Device> records) {
		for(Device record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public Device findById(Long id) {
		return deviceMapper.selectByPrimaryKey(id);
	}

	
	  @Override 
	  public PageResult findPage(PageRequest pageRequest) { 
		  	PageResult pageResult = null;
			Map<String, Object> params = pageRequest.getObjectParam();
			int pageNum = pageRequest.getPageNum();
			int pageSize = pageRequest.getPageSize();
			PageHelper.startPage(pageNum, pageSize);
			List<Device> result = deviceMapper.findPageByParams(params);
			pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<Device>((List<Device>) result));
			return pageResult;
	  }
	 
	  @Override
	  public void downloadExcel(List<?> records, HttpServletResponse response) throws IOException{
		  List<Map<String, Object>> list = new ArrayList<>();
	        for (int i = 0; i <records.size(); i++) {
	        	Device device = (Device) records.get(i);
	        	Map<String,Object> map = new LinkedHashMap<>(); 
	        	map.put("ID", device.getId());
	        			 map.put("名称",device.getName());  	        			 
	        			 map.put("型号", device.getType()); 
	        			 map.put("品牌",device.getBrand()); 
	        			 map.put("序列号",device.getNumber()); 
	        			 map.put("软件版本",device.getVersion());
	        			 map.put("创建人",device.getCreateBy()); 
	        			 map.put("创建时间日期",DateTimeUtils.getDateTime(device.getCreateTime())); 
	        			 map.put("最后更新人",device.getLastUpdateBy()); 
	        			 map.put("最后更新时间",DateTimeUtils.getDateTime(device.getLastUpdateTime())); 
	        			 list.add(map);
	        	
	        }
	        FileUtil.downloadExcel(list, response);
	  }

}
