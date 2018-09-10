package com.justfind.dao;

import java.util.List;

import com.justfind.entity.AdminRole;

public interface AdminRoleMapper {
	int insert(AdminRole record);

	int insertSelective(AdminRole record);

	List<AdminRole> selectByAdminId(Integer adminId);

	int deleteByAdminId(Integer adminId);

	List<AdminRole> selectByRoleId(Integer id);
}