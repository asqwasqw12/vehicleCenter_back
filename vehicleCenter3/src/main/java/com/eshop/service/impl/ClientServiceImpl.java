package com.eshop.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.eshop.dao.ClientMapper;
import com.eshop.pojo.Client;
import com.eshop.service.ClientService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	ClientMapper clientMapper;
	
	@Override
	public int save(Client record) {
		if(record.getId() == null || record.getId() == 0) {
			return clientMapper.insertSelective(record);
		}
		return clientMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Client record) {
		return clientMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<Client> records) {
		for(Client record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public Client findById(Long id) {
		return clientMapper.selectByPrimaryKey(id);
	}
	
	 @Override
	 public List<Client> findByCompanyId(Long companyId){
		 return clientMapper.findByCompanyId(companyId);
	 }

	
	  @Override 
	  public PageResult findPage(PageRequest pageRequest) { 
		  	PageResult pageResult = null;
			Map<String, Object> params = pageRequest.getObjectParam();
			int pageNum = pageRequest.getPageNum();
			int pageSize = pageRequest.getPageSize();
			PageHelper.startPage(pageNum, pageSize);
			List<Client> result = clientMapper.findPageByParams(params);
			pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<Client>((List<Client>) result));
			return pageResult;
	  }
	 
	  @Override
	  public void downloadExcel(List<?> records, HttpServletResponse response) throws IOException{
		  List<Map<String, Object>> list = new ArrayList<>();
	        for (int i = 0; i <records.size(); i++) {
	        	Client client = (Client) records.get(i);
	        	Map<String,Object> map = new LinkedHashMap<>(); 
	        	map.put("ID", client.getId());
	        			 map.put("名称",client.getName());  
	        			 map.put("性别",client.getSex()); 
	        			 map.put("移动电话", client.getMobilephone()); 
	        			 map.put("座机",client.getTelephone()); 
	        			 map.put("邮箱", client.getEmail()); 
	        			 map.put("职务", client.getPost()); 
	        			 map.put("QQ", client.getQq());
	        			 map.put("微信", client.getWechat());
	        			 map.put("教育程度", client.getEducation());
	        			 map.put("爱好", client.getHobby());
	        			 map.put("地址", client.getAddress());
	        			 map.put("生日", new SimpleDateFormat("yyyy-MM-dd").format(client.getBirthday()));
	        			 map.put("星座", client.getConstellation());
	        			 map.put("上级",client.getUpperContactName());
	        			 map.put("公司", client.getCompanyName());
	        			 map.put("创建人",client.getCreateBy()); 
	        			 map.put("创建时间日期",DateTimeUtils.getDateTime(client.getCreateTime())); 
	        			 map.put("最后更新人",client.getLastUpdateBy()); 
	        			 map.put("最后更新时间",DateTimeUtils.getDateTime(client.getLastUpdateTime())); 
	        			 list.add(map);
	        	
	        }
	        FileUtil.downloadExcel(list, response);
	  }

}
