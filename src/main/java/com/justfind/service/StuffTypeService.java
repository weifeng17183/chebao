package com.justfind.service;

import java.util.List;

import com.justfind.entity.StuffType;

public interface StuffTypeService {
	int deleteByPrimaryKey(Integer stuffTypeId);

	int insertSelective(StuffType record);

	StuffType selectByPrimaryKey(Integer stuffTypeId);

	int updateByPrimaryKeySelective(StuffType record);

	List<StuffType> queryList(StuffType record);

	List<StuffType> selectAll();

	int delete(Integer id);

	StuffType selectByName(String stuffTypeName);
}
