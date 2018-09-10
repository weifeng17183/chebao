package com.justfind.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.justfind.utils.DateJsonSerializer;

/**
 * 用户对象
 * 
 * @author chebao
 *
 */
public class Users {
	private Integer userId;// 主键id

	private String name;// 姓名

	private String mobileNum;// 电话号码：用于登录时做账号用

	@JsonIgnore
	private String password;// 密码

	private Integer userStates;// 用户状态，1：可用和2：禁用 3 休假

	private Integer userType;// 用户类型

	@JsonSerialize(using = DateJsonSerializer.class)
	private Date registerDate;// 注册时间

	private String openId;// 小程序唯一标识

	private String inviter;// 邀请人

	private String memo;// 备注

	private List<CarInfo> carInfoList;

	private List<UserAddress> addressList;

	private Integer factoryId;

	private Factory factory;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public Integer getUserStates() {
		return userStates;
	}

	public void setUserStates(Integer userStates) {
		this.userStates = userStates;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public List<CarInfo> getCarInfoList() {
		return carInfoList;
	}

	public void setCarInfoList(List<CarInfo> carInfoList) {
		this.carInfoList = carInfoList;
	}

	public String getInviter() {
		return inviter;
	}

	public void setInviter(String inviter) {
		this.inviter = inviter;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<UserAddress> addressList) {
		this.addressList = addressList;
	}

	public Integer getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	public Factory getFactory() {
		return factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}

}