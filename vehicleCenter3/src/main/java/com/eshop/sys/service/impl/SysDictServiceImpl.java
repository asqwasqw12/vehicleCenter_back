package com.eshop.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.sys.dao.SysDictMapper;
import com.eshop.sys.pojo.SysDict;
import com.eshop.sys.service.SysDictService;

@Service
public class SysDictServiceImpl implements SysDictService {

	@Autowired
	private SysDictMapper sysDictMapper;

	@Override
	public int save(SysDict record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysDictMapper.insertSelective(record);
		}
		return sysDictMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysDict record) {
		return sysDictMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysDict> records) {
		for(SysDict record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysDict findById(Long id) {
		return sysDictMapper.selectByPrimaryKey(id);
	}

	/*
	 * @Override public PageResult findPage(PageRequest pageRequest) { Object label
	 * = pageRequest.getParamValue("label"); if(label != null) { return
	 * MybatisPageHelper.findPage(pageRequest, sysDictMapper, "findPageByLabel",
	 * label); } return MybatisPageHelper.findPage(pageRequest, sysDictMapper); }
	 */

	/*
	 * @Override public List<SysDict> findByLable(String lable) { return
	 * sysDictMapper.findByLable(lable); }
	 */


}
