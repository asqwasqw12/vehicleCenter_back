package com.eshop.sys.service.impl;

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
import com.eshop.sys.dao.SysDeptMapper;
import com.eshop.sys.pojo.SysDept;
import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.service.SysDeptService;

@Service
public class SysDeptServiceImpl implements SysDeptService {

	@Autowired
	private SysDeptMapper sysDeptMapper;

	@Override
	public int save(SysDept record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysDeptMapper.insertSelective(record);
		}
		return sysDeptMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysDept record) {
		return sysDeptMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysDept> records) {
		for(SysDept record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysDept findById(Long id) {
		return sysDeptMapper.selectByPrimaryKey(id);
	}

	/*
	 * @Override public PageResult findPage(PageRequest pageRequest) { return
	 * MybatisPageHelper.findPage(pageRequest, sysDeptMapper); }
	 */

	@Override
	public List<SysDept> findTree() {
		List<SysDept> sysDepts = new ArrayList<>();
		List<SysDept> depts = sysDeptMapper.findAll();
		for (SysDept dept : depts) {
			if (dept.getParentId() == null || dept.getParentId() == 0) {
				dept.setLevel(0);
				sysDepts.add(dept);
			}
		}
		findChildren(sysDepts, depts);
		return sysDepts;
	}
	
	@Override
	public List<SysDept> findTree(String name){
		List<SysDept> sysDepts = new ArrayList<>();
		List<SysDept> depts = sysDeptMapper.findByName(name);
		sysDepts =findDeptChildren(depts);
		return sysDepts;
	}
	
	 @Override
	 public void downloadExcel(List<?> records, HttpServletResponse response) throws IOException {
	        List<Map<String, Object>> list = new ArrayList<>();
	        list = getChildren(records);
	        FileUtil.downloadExcel(list, response);
	    }
	
	 private List<Map<String, Object>> getChildren(List<?> records) {
		 List<Map<String, Object>> list = new ArrayList<>();
		 for(int i=0;i<records.size();i++) {
			 SysDept dept = (SysDept) records.get(i);
	        	Map<String,Object> map = new LinkedHashMap<>(); 
	        	         map.put("ID", dept.getId());
	        			 map.put("名称",dept.getName()); 
	        			 map.put("上级部门", dept.getParentName()); 
	        			 map.put("创建人",dept.getCreateBy()); 
	        			 map.put("创建时间日期",DateTimeUtils.getDateTime(dept.getCreateTime())); 
	        			 map.put("最后更新人",dept.getLastUpdateBy()); 
	        			 map.put("最后更新时间",DateTimeUtils.getDateTime(dept.getLastUpdateTime()));
	        			 list.add(map);
	        			 if(dept.getChildren().size()>0) {
	        				 list.addAll(getChildren(dept.getChildren()));
	        			 }
	        			 
		 }
		 return list;
	 }
	

	private void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {
		for (SysDept sysDept : sysDepts) {
			List<SysDept> children = new ArrayList<>();
			for (SysDept dept : depts) {
				if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
					dept.setParentName(sysDept.getName());
					dept.setLevel(sysDept.getLevel() + 1);
					children.add(dept);
				}
			}
			sysDept.setChildren(children);
			findChildren(children, depts);
		}
	}
	
	@Override
	public List<Long> getDeptChildren(Long id){
		SysDept sysDept = findById(id);
		List<SysDept> depts = sysDeptMapper.findAll();
		List<Long> list =new ArrayList<>();
		List<Long> temp =new ArrayList<>();
		if(sysDept !=null) {
		for(SysDept dept : depts) {
			if(sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
				temp.add(dept.getId());
			}			
		}
		if(temp.size()!=0) {
		 list.addAll(temp);
		  for(Long ChildId :temp) {
			list.addAll(getDeptChildren(ChildId));
		  }
		}
		}
		return list;
	}
	
	
	
	@Override
	public List<Long> getDeptChildren(List<SysDept> deptList) {
        List<Long> list = new ArrayList<>();
        deptList.forEach(dept -> {
                    if (dept!=null ){
                        List<SysDept> depts = sysDeptMapper.findByPid(dept.getId());
                        if(deptList.size() != 0){
                            list.addAll(getDeptChildren(depts));
                        }
                        list.add(dept.getId());
                    }
                }
        );
        return list;
    }
	
	@Override
	public List<SysDept> findDeptChildren(List<SysDept> deptList) {
		List<SysDept> list = new ArrayList<>();
		deptList.forEach(dept -> {
            if (dept!=null ){
                List<SysDept> depts = sysDeptMapper.findByPid(dept.getId());
                if(deptList.size() != 0){
                	dept.setChildren(depts);
                    findDeptChildren(depts);
                }
                if(dept.getParentId()!=null && dept.getParentId()!=0 ){
                		SysDept temp=sysDeptMapper.selectByPrimaryKey(dept.getParentId());
                		dept.setParentName(temp.getName());
                }
                list.add(dept);
                
            }
        }
	);
        return list;
	}
	
	//通过父id查询用户信息
	@Override
	public List<SysDept> findByPid(Long id){
		return sysDeptMapper.findByPid(id);
	}
}
