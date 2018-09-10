package com.justfind.dao;

import java.util.List;

import com.justfind.entity.CarInfo;

public interface CarInfoMapper {
	int deleteByPrimaryKey(Integer carId);

	int insert(CarInfo record);

	int insertSelective(CarInfo record);

	CarInfo selectByPrimaryKey(Integer carId);

	List<CarInfo> selectByUserId(Integer userId);

	int updateByPrimaryKeySelective(CarInfo record);

	int updateByPrimaryKey(CarInfo record);

	Integer getMaxId();
}