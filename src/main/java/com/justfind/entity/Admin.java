package com.justfind.entity;

import java.util.List;

/**
 * 后台管理人员对象
 * 
 * @author chebao
 *
 */
public class Admin {
	private Integer adminId;// 主键id

	private String userName;// 管理账号

	private String password;// 密码

	private Integer adminStatus;// 状态

	private Integer adminType;

	private Integer factoryId;

	private Factory factory;

	private List<AdminRole> arList;

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}

	public List<AdminRole> getArList() {
		return arList;
	}

	public void setArList(List<AdminRole> arList) {
		this.arList = arList;
	}

	public Integer getAdminType() {
		return adminType;
	}

	public void setAdminType(Integer adminType) {
		this.adminType = adminType;
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