package com.justfind.service;

import java.util.List;

import com.justfind.entity.Permission;

public interface PermissionService {
	/**
	 * 查询所有权限
	 * @return
	 */
	public List<Permission> listAll();

	public int delete(Permission permission);
	
	int insertSelective(Permission record);

	int updateByPrimaryKeySelective(Permission record);

}
