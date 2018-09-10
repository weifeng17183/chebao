package com.justfind.dao;

import java.util.List;

import com.justfind.entity.PermissionTitle;

public interface PermissionTitleMapper {
    int deleteByPrimaryKey(Integer titleId);

    int insert(PermissionTitle record);

    int insertSelective(PermissionTitle record);

    PermissionTitle selectByPrimaryKey(Integer titleId);

    int updateByPrimaryKeySelective(PermissionTitle record);

    int updateByPrimaryKey(PermissionTitle record);
    
    List<PermissionTitle> listAll();
}