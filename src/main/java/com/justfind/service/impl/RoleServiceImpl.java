package com.justfind.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justfind.dao.AdminRoleMapper;
import com.justfind.dao.RoleMapper;
import com.justfind.dao.RolePermissionMapper;
import com.justfind.entity.AdminRole;
import com.justfind.entity.Role;
import com.justfind.entity.RolePermission;
import com.justfind.service.RoleService;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private AdminRoleMapper adminRoleMapper;

	@Override
	public List<Role> listAll() {
		return roleMapper.queryListAll();
	}

	@Override
	public List<Role> selectAll() {
		return roleMapper.selectAll();
	}

	@Override
	public void insert(Role role, List<RolePermission> rpList) {
		roleMapper.insert(role);
		Integer roleId = roleMapper.getMaxId();
		for (RolePermission rolePermission : rpList) {
			rolePermission.setRoleId(roleId);
			rolePermissionMapper.insertSelective(rolePermission);
		}
	}

	@Override
	public void update(Role role, List<RolePermission> rpList) {
		roleMapper.updateByPrimaryKey(role);
		rolePermissionMapper.deleteByRoleId(role.getRoleId());
		for (RolePermission rolePermission : rpList) {
			rolePermissionMapper.insertSelective(rolePermission);
		}
	}

	@Override
	public Role selectById(Integer id) {
		Role role = roleMapper.selectByPrimaryKey(id);
		return role;
	}

	@Override
	public int delete(Integer id) {
		List<AdminRole> list = adminRoleMapper.selectByRoleId(id);
		if (list.size() > 0) {
			return list.size();
		}
		roleMapper.deleteByPrimaryKey(id);
		rolePermissionMapper.deleteByRoleId(id);
		return -1;
	}

}
