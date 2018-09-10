package com.justfind.dao;

import java.util.List;

import com.justfind.entity.SecondType;

public interface SecondTypeMapper {
	int deleteByPrimaryKey(Integer secondTypeId);

	int insertSelective(SecondType record);

	SecondType selectByPrimaryKey(Integer secondTypeId);

	int updateByPrimaryKeySelective(SecondType record);

	List<SecondType> queryList(SecondType secondType);

	SecondType selectByName(String secondTypeName);

	List<SecondType> selectByStuffTypeId(Integer stuffTypeId);
}