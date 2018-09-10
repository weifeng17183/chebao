package com.justfind.dao;

import java.util.List;

import com.justfind.entity.AdminRole;
import com.justfind.entity.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
    
    List<RolePermission> selectByRoleId(Integer roleId);

	void deleteByRoleId(Integer roleId);

	List<AdminRole> selectByPermissionId(Integer permissionId);

	void deleteByPermissionId(Integer permissionId);
}