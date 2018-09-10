package com.justfind.service;

import java.util.List;

import com.justfind.entity.Role;
import com.justfind.entity.RolePermission;

public interface RoleService {
	/**
	 * 查询角色列表
	 * 
	 * @return
	 */
	List<Role> listAll();

	/**
	 * 添加角色
	 * 
	 * @param role
	 */
	void insert(Role role, List<RolePermission> rpList);

	void update(Role role, List<RolePermission> rpList);

	Role selectById(Integer id);

	int delete(Integer id);

	List<Role> selectAll();
}
