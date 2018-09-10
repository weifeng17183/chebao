package com.justfind.dao;

import java.util.List;

import com.justfind.entity.Factory;

public interface FactoryMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Factory record);

	int insertSelective(Factory record);

	Factory selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Factory record);

	int updateByPrimaryKey(Factory record);

	List<Factory> queryFactoryList(Factory factory);

	List<Factory> selectAll();
}