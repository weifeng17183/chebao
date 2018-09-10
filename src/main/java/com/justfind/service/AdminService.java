package com.justfind.service;

import java.util.List;
import java.util.Map;

import com.justfind.entity.Admin;

public interface AdminService {
	public int deleteByPrimaryKey(Integer adminId);

	public void insert(Admin record);

	public void insertSelective(Admin record);

	public Admin selectByPrimaryKey(Integer adminId);

	public void updateByPrimaryKeySelective(Admin record);

	public void updateByPrimaryKey(Admin record);

	List<Admin> selectAdminList(Admin admin);

	int queryCount(Map<String, Object> paramMap);

	public Admin selectByUserName(String userName);
}
