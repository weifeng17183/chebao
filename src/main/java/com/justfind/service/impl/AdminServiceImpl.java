package com.justfind.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justfind.dao.AdminMapper;
import com.justfind.dao.AdminRoleMapper;
import com.justfind.entity.Admin;
import com.justfind.entity.AdminRole;
import com.justfind.service.AdminService;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private AdminRoleMapper adminRoleMapper;

	public void insert(Admin record) {
		adminMapper.insert(record);
	}

	public void updatePass(Admin record) {
		adminMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Admin> selectAdminList(Admin admin) {
		return adminMapper.queryAdminList(admin);
	}

	@Override
	public int queryCount(Map<String, Object> paramMap) {
		return adminMapper.queryCount(paramMap);
	}

	@Override
	public Admin selectByPrimaryKey(Integer adminId) {
		Admin admin = adminMapper.selectByPrimaryKey(adminId);
		return admin;
	}

	@Override
	public int deleteByPrimaryKey(Integer adminId) {
		adminRoleMapper.deleteByAdminId(adminId);
		return adminMapper.deleteByPrimaryKey(adminId);
	}

	@Override
	public void insertSelective(Admin record) {
		adminMapper.insertSelective(record);
		Integer adminId = adminMapper.getMaxId();
		for (AdminRole role : record.getArList()) {
			AdminRole adminRole = new AdminRole(adminId, role.getRoleId());
			adminRoleMapper.insertSelective(adminRole);
		}
	}

	@Override
	public void updateByPrimaryKeySelective(Admin record) {
		adminMapper.updateByPrimaryKeySelective(record);
		adminRoleMapper.deleteByAdminId(record.getAdminId());
		for (AdminRole role : record.getArList()) {
			AdminRole adminRole = new AdminRole(record.getAdminId(), role.getRoleId());
			adminRoleMapper.insertSelective(adminRole);
		}
	}

	@Override
	public void updateByPrimaryKey(Admin record) {
		adminMapper.updateByPrimaryKey(record);
	}

	@Override
	public Admin selectByUserName(String userName) {
		return adminMapper.selectByUserName(userName);
	}
}
