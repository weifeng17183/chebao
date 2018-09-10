package com.justfind.dao;

import java.util.List;

import com.justfind.entity.Permission;

public interface PermissionMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Permission record);

	int insertSelective(Permission record);

	Permission selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);

	List<Permission> queryListAll();

	List<Permission> selectByAdminId(Integer adminId);
}