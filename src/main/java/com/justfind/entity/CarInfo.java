package com.justfind.entity;

public class CarInfo {
	private Integer carId;

	private Integer userId;

	private String carNumber; // 车牌号

	private String engineNumber; // 发动机号

	private String frameNumber; // 车架号

	private Integer mileage; // 行驶里程

	private String memo; // 备注

	private String carName; // 车型

	private String colour; // 颜色

	private String insuranceCompany; // 保险公司

	private String insuranceNum; // 保险单号

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCarNumber() {
		return carNumber.toUpperCase();
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber == null ? null : carNumber.trim().toUpperCase();
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber == null ? null : engineNumber.trim();
	}

	public String getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber == null ? null : frameNumber.trim();
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName == null ? null : carName.trim();
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour == null ? null : colour.trim();
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String getInsuranceNum() {
		return insuranceNum;
	}

	public void setInsuranceNum(String insuranceNum) {
		this.insuranceNum = insuranceNum;
	}
}