package com.justfind.service;

import java.util.List;

import com.justfind.entity.SecondType;

public interface SecondTypeService {
	int deleteByPrimaryKey(Integer secondTypeId);

	int insertSelective(SecondType record);

	SecondType selectByPrimaryKey(Integer secondTypeId);

	int updateByPrimaryKeySelective(SecondType record);

	int delete(Integer secondTypeId);

	List<SecondType> queryList(SecondType secondType);

	List<SecondType> selectByStuffTypeId(Integer stuffTypeId);

	SecondType selectByName(String secondTypeName);
}
