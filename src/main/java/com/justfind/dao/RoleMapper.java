package com.justfind.dao;

import java.util.List;

import com.justfind.entity.Role;

public interface RoleMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	List<Role> queryListAll();
	
	List<Role> selectAll();

	List<Role> selectByAdminId(Integer adminId);

	Integer getMaxId();
}