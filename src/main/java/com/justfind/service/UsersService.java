package com.justfind.service;

import java.util.List;
import java.util.Map;

import com.justfind.entity.Users;

public interface UsersService {
	public void insert(Users record);

	public Users login(Users record);

	public Users selectByMobileNum(String mobileNum);

	public void updatePass(Users record);

	public void updateByPrimaryKeySelective(Users entity);

	public int queryCount(Map<String, Object> paramMap);

	public Users selectByPrimaryKey(Integer userid);

	int deleteByPrimaryKey(Integer userid);

	public String getOpenId(String appid, String secret, String loginCode);

	public Users selectByOpenId(String openId);

	public List<Users> queryUserList(Users user);

	List<Users> selectByType(Users users);
}
