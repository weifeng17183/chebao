package com.justfind.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.justfind.constant.BaseConstant.UserStates;
import com.justfind.dao.UsersMapper;
import com.justfind.entity.Users;
import com.justfind.exception.CheckServiceException;
import com.justfind.exception.ErrorCode;
import com.justfind.service.UsersService;
import com.justfind.utils.CommonUtil;
import com.justfind.utils.Md5Encrypt;

import net.sf.json.JSONObject;

@Service("usersService")
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersMapper usersMapper;

	public void insert(Users record) {
		Users users = usersMapper.selectByMobileNum(record.getMobileNum());
		if (users != null) {
			throw new CheckServiceException(ErrorCode.REGISTERED, record.getMobileNum() + "用户已存在！");
		}
		if (record.getPassword() != null) {
			String password = Md5Encrypt.md5(record.getPassword());
			password = Md5Encrypt.md5(password);
			record.setPassword(password);
		}
		record.setUserStates(UserStates.NORMAL);
		record.setRegisterDate(new Date());
		usersMapper.insertSelective(record);
	}

	public Users login(Users record) {
		if (record.getOpenId() != null) {
			Users dbusers = usersMapper.selectByOpenId(record.getOpenId());
			if (dbusers != null) {
				return dbusers;
			}
		}
		Users users = null;
		if (record.getMobileNum() != null) {
			users = usersMapper.selectByMobileNum(record.getMobileNum());
			if (users == null) {
				throw new CheckServiceException(ErrorCode.NOT_REGISTERED, "账号未注册！");
			}
			String password = Md5Encrypt.md5(record.getPassword());
			password = Md5Encrypt.md5(password);
			record.setPassword(password);
			if (!users.getPassword().equals(record.getPassword())) {
				throw new CheckServiceException(ErrorCode.PASSWORD_ERROR, "密码错误！");
			}
			if (record.getOpenId() != null) {
				users.setOpenId(record.getOpenId());
				usersMapper.updateByPrimaryKeySelective(users);
			}
		}
		return users;
	}

	public Users selectByMobileNum(String mobileNum) {
		if (StringUtils.isEmpty(mobileNum)) {
			return null;
		}
		Users users = usersMapper.selectByMobileNum(mobileNum);
		return users;
	}

	public void updatePass(Users record) {
		String password = Md5Encrypt.md5(record.getPassword());
		password = Md5Encrypt.md5(password);
		record.setPassword(password);
		usersMapper.updateByPrimaryKeySelective(record);
	}

	public void updateByPrimaryKeySelective(Users entity) {
		usersMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public int queryCount(Map<String, Object> paramMap) {
		return usersMapper.queryCount(paramMap);
	}

	@Override
	public Users selectByPrimaryKey(Integer userid) {
		return usersMapper.selectByPrimaryKey(userid);
	}

	@Override
	public int deleteByPrimaryKey(Integer userid) {
		return usersMapper.deleteByPrimaryKey(userid);
	}

	@Override
	public String getOpenId(String appid, String secret, String code) {
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("CODE", code);
		requestUrl = requestUrl.replace("APPID", appid);
		requestUrl = requestUrl.replace("SECRET", secret);
		JSONObject jsonResult = CommonUtil.httpsRequest(requestUrl, "POST", code);
		if (jsonResult != null) {
			String openid = jsonResult.getString("openid");
			if (openid != null) {
				System.out.println("获取用户openId成功");
			} else {
				System.out.println("获取用户openId失败:");
			}
		}
		return jsonResult.getString("openid");
	}

	@Override
	public Users selectByOpenId(String openId) {
		return usersMapper.selectByOpenId(openId);
	}

	@Override
	public List<Users> queryUserList(Users user) {
		return usersMapper.queryUserList(user);
	}

	@Override
	public List<Users> selectByType(Users user) {
		return usersMapper.selectByType(user);
	}

}
