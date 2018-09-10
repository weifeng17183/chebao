package com.justfind.dao;

import java.util.List;

import com.justfind.entity.UserAddress;

public interface UserAddressMapper {
	int deleteByPrimaryKey(Integer addressId);

	int insertSelective(UserAddress record);

	UserAddress selectByPrimaryKey(Integer addressId);

	int updateByPrimaryKeySelective(UserAddress record);

	List<UserAddress> selectByUserId(Integer userId);

	Integer getMaxId();
}