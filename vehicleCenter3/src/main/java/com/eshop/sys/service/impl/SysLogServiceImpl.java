package com.eshop.sys.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.eshop.sys.dao.SysLogMapper;
import com.eshop.sys.pojo.SysLog;
import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class SysLogServiceImpl  implements SysLogService {

	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public int save(SysLog record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysLogMapper.insertSelective(record);
		}
		return sysLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysLog record) {
		return sysLogMapper.deleteByPrimaryKey(record.getId());
	}
	
	@Override
	public int delete(Map<String,Object> params) {
		handlePageRequest(params);
		return sysLogMapper.deleteByParams(params);
	}

	@Override
	public int delete(List<SysLog> records) {
		for(SysLog record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysLog findById(Long id) {
		return sysLogMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		PageResult pageResult = null;
		Map<String, Object> params = new HashMap<>();
		params = pageRequest.getObjectParam();
		handlePageRequest(params);
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<SysLog> result = sysLogMapper.findPageByParams(params);
		pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<SysLog>((List<SysLog>) result));
		return pageResult;
	}
	
	// PageRequest参数处理函数
	private void handlePageRequest(Map<String,Object> params) {

			// 处理注册时间参数
			if (params.get("createTime") != null && params.get("createTime") != "") {
				List<String> strList = new ArrayList<>();
				strList = (ArrayList<String>) params.get("createTime");
				if (strList.size() > 0) {
					String startTime = strList.get(0);
					String endTime = strList.get(1);
					params.put("startTime", startTime);
					params.put("endTime", endTime);
				}
			}
	}
	
	@Override
    public void downloadExcel(List<?> records, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i <records.size(); i++) {
        	SysLog log = (SysLog) records.get(i);
        	Map<String,Object> map = new LinkedHashMap<>(); 
        	map.put("ID",log.getId());
        	map.put("用户名", log.getUserName());
        			 map.put("方法",log.getMethod()); 
        			 map.put("方法参数", log.getParams()); 
        			 map.put("操作描述", log.getOperation()); 
        			 map.put("IP地址",log.getIp()); 
        			 map.put("IP来源", log.getAddress()); 
        			 map.put("浏览器",log.getBrowser()); 
        			 map.put("耗时", log.getTime()); 
        			 map.put("创建人",log.getCreateBy()); 
        			 map.put("创建时间日期",DateTimeUtils.getDateTime(log.getCreateTime())); 
        			 map.put("最后更新人",log.getLastUpdateBy()); 
        			 map.put("最后更新时间",DateTimeUtils.getDateTime(log.getLastUpdateTime())); 
        			 list.add(map);
        	
        }
        FileUtil.downloadExcel(list, response);
    }

	
}
