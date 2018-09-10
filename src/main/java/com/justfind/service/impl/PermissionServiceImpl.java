package com.justfind.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justfind.dao.PermissionMapper;
import com.justfind.dao.RolePermissionMapper;
import com.justfind.entity.AdminRole;
import com.justfind.entity.Permission;
import com.justfind.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private FilterChainDefinitionsService filterChainDefinitionsService;

	@Override
	public List<Permission> listAll() {
		return permissionMapper.queryListAll();
	}

	@Override
	public int delete(Permission permission) {
		List<AdminRole> list = rolePermissionMapper.selectByPermissionId(permission.getPermissionId());
		if (list.size() > 0) {
			return list.size();
		}
		permissionMapper.deleteByPrimaryKey(permission.getPermissionId());
		filterChainDefinitionsService.reloadFilterChains();
		return -1;
	}

	@Override
	public int insertSelective(Permission record) {
		int i = permissionMapper.insertSelective(record);
		filterChainDefinitionsService.reloadFilterChains();
		return i;
	}

	@Override
	public int updateByPrimaryKeySelective(Permission record) {
		int i = permissionMapper.updateByPrimaryKeySelective(record);
		filterChainDefinitionsService.reloadFilterChains();
		return i;
	}

}
