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
import com.eshop.common.SysConstants;
import com.eshop.sys.dao.SysMenuMapper;
import com.eshop.sys.pojo.SysDept;
import com.eshop.sys.pojo.SysMenu;
import com.eshop.sys.service.SysDeptService;
import com.eshop.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public int save(SysMenu record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysMenuMapper.insertSelective(record);
		}
		if(record.getParentId() == null) {
			record.setParentId(0L);
		}
		return sysMenuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysMenu record) {
		return sysMenuMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysMenu> records) {
		for(SysMenu record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysMenu findById(Long id) {
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	/*
	 * @Override public PageResult findPage(PageRequest pageRequest) { return
	 * MybatisPageHelper.findPage(pageRequest, sysMenuMapper); }
	 */
	
	@Override
	public List<SysMenu> findTree(String userName, int menuType) {
		List<SysMenu> sysMenus = new ArrayList<>();
		List<SysMenu> menus = findByUser(userName);
		for (SysMenu menu : menus) {
			if (menu.getParentId() == null || menu.getParentId() == 0) {
				menu.setLevel(0);
				if(!exists(sysMenus, menu)) {
					sysMenus.add(menu);
				}
			}
		}
		sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
		findChildren(sysMenus, menus, menuType);
		return sysMenus;
	}

	@Override
	public List<SysMenu> findByUser(String userName) {
		if(userName == null || "".equals(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
			return sysMenuMapper.findAll();
		}
		return sysMenuMapper.findByUserName(userName);
	}

	private void findChildren(List<SysMenu> SysMenus, List<SysMenu> menus, int menuType) {
		for (SysMenu SysMenu : SysMenus) {
			List<SysMenu> children = new ArrayList<>();
			for (SysMenu menu : menus) {
				if(menuType == 1 && menu.getType() == 2) {
					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
					continue ;
				}
				if (SysMenu.getId() != null && SysMenu.getId().equals(menu.getParentId())) {
					menu.setParentName(SysMenu.getName());
					menu.setLevel(SysMenu.getLevel() + 1);
					if(!exists(children, menu)) {
						children.add(menu);
					}
				}
			}
			SysMenu.setChildren(children);
			children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
			findChildren(children, menus, menuType);
		}
	}

	private boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
		boolean exist = false;
		for(SysMenu menu:sysMenus) {
			if(menu.getId().equals(sysMenu.getId())) {
				exist = true;
			}
		}
		return exist;
	}
	
	@Override
	public List<SysMenu> findTreebyName(String name){
		List<SysMenu> sysMenus = new ArrayList<>();
		List<SysMenu> menus = sysMenuMapper.findByName(name);
		sysMenus =findMenuChildren(menus);
		return sysMenus;
	}
	
	
	@Override
	public List<SysMenu> findMenuChildren(List<SysMenu> menuList) {
		List<SysMenu> list = new ArrayList<>();
		menuList.forEach(menu -> {
            if (menu!=null ){
                List<SysMenu> menus = sysMenuMapper.findByPid(menu.getId());
                if(menuList.size() != 0){
                	
                	menus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
                	menu.setChildren(findMenuChildren(menus));
                    //findMenuChildren(menus);
                }
                if(menu.getParentId()!=null && menu.getParentId()!=0){
                		SysMenu temp=sysMenuMapper.selectByPrimaryKey(menu.getParentId());
                			menu.setParentName(temp.getName());              		
                }
                list.add(menu);               
            }
        }
	);
        return list;
	}
	
	//通过父id查询用户信息
		@Override
		public List<SysMenu> findByPid(Long id){
			return sysMenuMapper.findByPid(id);
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
				 SysMenu menu = (SysMenu) records.get(i);
		        	Map<String,Object> map = new LinkedHashMap<>(); 
		        	         map.put("ID", menu.getId());
		        			 map.put("名称",menu.getName()); 
		        			 map.put("类型", menu.getType()); 
		        			 map.put("上级菜单Id",menu.getParentId());
		        			 map.put("上级菜单名",menu.getParentName());
		        			 map.put("URL地址",menu.getUrl());
		        			 map.put("权限标识",menu.getPerms());
		        			 map.put("顺序编号",menu.getOrderNum());
		        			 map.put("创建人",menu.getCreateBy()); 
		        			 map.put("创建时间日期",DateTimeUtils.getDateTime(menu.getCreateTime())); 
		        			 map.put("最后更新人",menu.getLastUpdateBy()); 
		        			 map.put("最后更新时间",DateTimeUtils.getDateTime(menu.getLastUpdateTime()));
		        			 list.add(map);
		        			 if(menu.getChildren().size()>0) {
		        				 list.addAll(getChildren(menu.getChildren()));
		        			 }
		        			 
			 }
			 return list;
		 }

}
