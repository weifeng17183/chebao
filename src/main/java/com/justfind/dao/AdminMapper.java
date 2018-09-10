package com.justfind.dao;

import java.util.List;
import java.util.Map;

import com.justfind.entity.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    void insert(Admin record);

    void insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    void updateByPrimaryKeySelective(Admin record);

    void updateByPrimaryKey(Admin record);

	int queryCount(Map<String, Object> paramMap);

	List<Admin> queryAdminList(Admin admin);

	Admin selectByUserName(String userName);
	/**
	 * 获取最大主键
	 * @return
	 */
	Integer getMaxId();

	List<Admin> queryFactoryAdminList(Admin searchBean);
}