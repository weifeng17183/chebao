package com.justfind.dao;

import java.util.List;

import com.justfind.entity.Product;
import com.justfind.entity.SecondType;
import com.justfind.entity.StuffType;

public interface StuffTypeMapper {
	int deleteByPrimaryKey(Integer stuffTypeId);

	int insertSelective(StuffType record);

	StuffType selectByPrimaryKey(Integer stuffTypeId);

	int updateByPrimaryKeySelective(StuffType record);

	List<StuffType> queryList(StuffType record);

	// 查出所有
	List<StuffType> selectAll();

	StuffType selectByName(String stuffTypeName);

	List<StuffType> getProductList();
}