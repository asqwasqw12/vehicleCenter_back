package com.eshop.sys.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.common.DateTimeUtils;
import com.eshop.common.FileUtil;
import com.eshop.common.PoiUtils;
import com.eshop.common.SecurityUtils;
import com.eshop.common.StringUtils;
import com.eshop.common.page.MybatisPageHelper;
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.sys.dao.SysRoleMapper;
import com.eshop.sys.dao.SysUserMapper;
import com.eshop.sys.dao.SysUserRoleMapper;
import com.eshop.sys.pojo.SysDept;
import com.eshop.sys.pojo.SysMenu;
import com.eshop.sys.pojo.SysRole;
import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.pojo.SysUserRole;
import com.eshop.sys.service.SysDeptService;
import com.eshop.sys.service.SysMenuService;
import com.eshop.sys.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Value("${file.avatar}")
	private String avatar;
	@Autowired
	private SysDeptService sysDeptService;

	@Transactional
	@Override
	public int save(SysUser record) {
		Long id = null;
		if (record.getId() == null || record.getId() == 0) {
			// 新增用户
			sysUserMapper.insertSelective(record);
			id = record.getId();
		} else {
			// 更新用户信息
			sysUserMapper.updateByPrimaryKeySelective(record);
		}
		// 更新用户角色
		if (id != null && id == 0) {
			return 1;
		}
		if (id != null) {
			for (SysUserRole sysUserRole : record.getUserRoles()) {
				sysUserRole.setUserId(id);
			}
		} else {
			if (!record.getUserRoles().isEmpty()) {
				sysUserRoleMapper.deleteByUserId(record.getId());
			}
		}
		for (SysUserRole sysUserRole : record.getUserRoles()) {
			sysUserRoleMapper.insertSelective(sysUserRole);
		}
		return 1;
	}

	@Override
	public int delete(SysUser record) {
		return sysUserMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysUser> records) {
		for (SysUser record : records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysUser findById(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysUser findByName(String name) {
		SysUser sysUser = sysUserMapper.findByName(name);
		if (sysUser != null) {
			List<SysUserRole> userRoles = findUserRoles(sysUser.getId());
			sysUser.setUserRoles(userRoles);
			sysUser.setRoleNames(getRoleNames(userRoles));
		}
		return sysUser;
	}

	@Override
	public List<SysUser> findByStatus(Byte status) {
		List<SysUser> list = sysUserMapper.selectByStatus(status);
		if (list != null) {
			findUserRoles(list);
		}
		return list;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		PageResult pageResult = null;
		Map<String, Object> params = handlePageRequest(pageRequest);
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<SysUser> result = sysUserMapper.findPageByParams(params);
		pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo((List) result));
		findUserRoles((List<SysUser>) pageResult.getContent());
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

		// 处理部门id参数,转换成包含所有子id的deptIdList
		if (params.get("deptId") != null && !params.get("deptId").equals(0)) {
			Long id = Long.parseLong(params.get("deptId").toString());
			if (sysDeptService.findById(id) != null) {
				List<Long> deptIdList = new ArrayList<>();
				List<SysDept> deptList = sysDeptService.findByPid(id);
				if (deptList != null && deptList.size() > 0) {
					deptIdList = sysDeptService.getDeptChildren(deptList);
				}
				deptIdList.add(id);
				params.put("deptIdList", deptIdList);
			}
		}
		return params;
	}

	/**
	 * 加载用户角色
	 * 
	 * @param pageResult
	 */

	private void findUserRoles(List<SysUser> list) {
		for (SysUser sysUser : list) {
			List<SysUserRole> userRoles = findUserRoles(sysUser.getId());
			sysUser.setUserRoles(userRoles);
			sysUser.setRoleNames(getRoleNames(userRoles));
		}
	}

	private String getRoleNames(List<SysUserRole> userRoles) {
		StringBuilder sb = new StringBuilder();
		for (Iterator<SysUserRole> iter = userRoles.iterator(); iter.hasNext();) {
			SysUserRole userRole = iter.next();
			SysRole sysRole = sysRoleMapper.selectByPrimaryKey(userRole.getRoleId());
			if (sysRole == null) {
				continue;
			}
			sb.append(sysRole.getRemark());
			if (iter.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	@Override
	public Set<String> findPermissions(String userName) {
		Set<String> perms = new HashSet<>();
		List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
		for (SysMenu sysMenu : sysMenus) {
			if (sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
				perms.add(sysMenu.getPerms());
			}
		}
		return perms;
	}

	@Override
	public List<SysUserRole> findUserRoles(Long userId) {
		return sysUserRoleMapper.findUserRoles(userId);
	}

	@Override
	@CacheEvict(allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public void updateAvatar(MultipartFile multipartFile) {
		SysUser user = sysUserMapper.findByName(SecurityUtils.getUsername());
		String userAvatar = user.getAvatar();
		String oldPath = "";
		if (userAvatar != null) {
			oldPath = avatar + userAvatar;
			System.out.println("oldPath=" + oldPath);
		}
		File file = FileUtil.upload(multipartFile, avatar);
		assert file != null;
		user.setAvatar(file.getName());
		sysUserMapper.updateByPrimaryKeySelective(user);
		if (StringUtils.isNotBlank(oldPath)) {
			FileUtil.del(oldPath);
		}
	}

	
	  @Override
	  public File createUserExcelFile(PageRequest pageRequest) {
		  PageResult pageResult = findPage(pageRequest); 
		  return createUserExcelFile(pageResult.getContent()); 
	  }
	  
	  public static File createUserExcelFile(List<?> records) { 
		  if (records ==null) {
			  records = new ArrayList<>();
			  } 
		  Workbook workbook = new XSSFWorkbook(); 
		  Sheet sheet = workbook.createSheet();
		  Row row0 =sheet.createRow(0); 
		  int columnIndex = 0;
	  row0.createCell(columnIndex).setCellValue("No");
	  row0.createCell(++columnIndex).setCellValue("ID");
	  row0.createCell(++columnIndex).setCellValue("用户名");
	  row0.createCell(++columnIndex).setCellValue("姓名");
	  row0.createCell(++columnIndex).setCellValue("机构");
	  row0.createCell(++columnIndex).setCellValue("职务");
	  row0.createCell(++columnIndex).setCellValue("角色");
	  row0.createCell(++columnIndex).setCellValue("邮箱");
	  row0.createCell(++columnIndex).setCellValue("手机号");
	  row0.createCell(++columnIndex).setCellValue("状态");
	  row0.createCell(++columnIndex).setCellValue("头像");
	  row0.createCell(++columnIndex).setCellValue("创建人");
	  row0.createCell(++columnIndex).setCellValue("创建时间");
	  row0.createCell(++columnIndex).setCellValue("最后更新人");
	  row0.createCell(++columnIndex).setCellValue("最后更新时间");
	  for (int i = 0; i <records.size(); i++) { 
		  SysUser user = (SysUser) records.get(i); 
		  Row row = sheet.createRow(i + 1);
		  for (int j = 0; j < columnIndex + 1; j++) {
			  row.createCell(j); 
			  } 
		  columnIndex = 0;
		  row.getCell(columnIndex).setCellValue(i + 1); 
		  row.getCell(++columnIndex).setCellValue(user.getId());
		  row.getCell(++columnIndex).setCellValue(user.getName());
		  row.getCell(++columnIndex).setCellValue(user.getRealName());
		  row.getCell(++columnIndex).setCellValue(user.getDeptName());
		  row.getCell(++columnIndex).setCellValue(user.getJob());
		  row.getCell(++columnIndex).setCellValue(user.getRoleNames());
		  row.getCell(++columnIndex).setCellValue(user.getEmail());
		  row.getCell(++columnIndex).setCellValue(user.getStatus());
		  row.getCell(++columnIndex).setCellValue(user.getAvatar());
		  row.getCell(++columnIndex).setCellValue(user.getCreateBy());
		  row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getCreateTime()));
		  row.getCell(++columnIndex).setCellValue(user.getLastUpdateBy());
		  row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getLastUpdateTime())); 
		  }
	  return PoiUtils.createExcelFile(workbook,"download_user"); 
	  }
	 
}
