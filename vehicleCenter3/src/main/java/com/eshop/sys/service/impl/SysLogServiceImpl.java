package com.eshop.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.common.page.MybatisPageHelper;
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.sys.dao.SysLogMapper;
import com.eshop.sys.pojo.SysLog;
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
		Map<String, Object> params = handlePageRequest(pageRequest);
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<SysLog> result = sysLogMapper.findPageByParams(params);
		pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<SysLog>((List<SysLog>) result));
		return pageResult;
	}
	
	// PageRequest参数处理函数
	private Map<String, Object> handlePageRequest(PageRequest pageRequest) {
			Map<String, Object> params = new HashMap<>();
			params = pageRequest.getObjectParam();

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
			return params;
	}

	
}
