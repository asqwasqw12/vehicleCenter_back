package com.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eshop.pojo.FileBean;

public interface FileMapper {
	
	int deleteByPrimaryKey(Long id);		//通过id删除

    int insert(FileBean record);		//插入文件记录

    int insertSelective(FileBean record);	//插入文件记录

    FileBean selectByPrimaryKey(Long id);	//通过id查找记录

    int updateByPrimaryKeySelective(FileBean record);	//更新文件记录

    int updateByPrimaryKey(FileBean record);		//更新文件记录
    
    List<FileBean> findByPid(Long id);   //通过父id查找所有文件记录
    
    List<FileBean> findByName(@Param(value="name") String name);  //通过文件名查找文件记录
    
    List<FileBean> findByType(@Param(value="type") String type);  //通过文件类型查找文件记录
    	    
    List<FileBean> findAll();		//查找所有文件
}
