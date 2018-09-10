package com.justfind.dao;

import java.util.List;
import java.util.Map;

import com.justfind.entity.Users;

public interface UsersMapper {
	int deleteByPrimaryKey(Integer userid);

	int insertSelective(Users record);

	Users selectByPrimaryKey(Integer userid);

	Users selectByUserId(Integer userid);

	int updateByPrimaryKeySelective(Users record);

	Users selectByMobileNum(String mobileNum);

	int queryCount(Map<String, Object> paramMap);

	Integer selectExsitMobile(Users searchBean);

	Users selectByOpenId(String openId);

	List<Users> queryUserList(Users user);

	List<Users> selectByType(Users user);
}