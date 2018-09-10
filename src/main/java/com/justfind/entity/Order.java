package com.justfind.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.justfind.utils.DateJsonSerializer;

public class Order {
	private Integer id;

	private String orderId;

	private Integer userId;

	@JsonSerialize(using = DateJsonSerializer.class)
	private Date createTime;

	@JsonSerialize(using = DateJsonSerializer.class)
	private Date updateTime;

	private Integer payStatus;

	private Integer carStatus;

	private Integer assessStatus;

	private Integer orderUserId;

	private String memo;

	private Integer carId;

	private Integer addressId;

	private Integer backAddressId;

	private BigDecimal amount;

	private Integer factoryId;

	private Factory factory;

	private CarInfo carInfo;

	private UserAddress userAddress;

	private UserAddress backAddress;

	private Users user;

	private Users orderUser;

	private String ip;

	private UnifiedOrderResult unifiedOrderResult;// 微信支付返回信息对象

	private Integer unifiedOrderResultId;// 微信支付返回信息对象id

	private String transactionId;

	private List<OrderItem> itemList;

	private List<OrderImage> imageList;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate;// 开始时间：查询时用

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;// 结束时间：查询时用户

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getOrderUserId() {
		return orderUserId;
	}

	public void setOrderUserId(Integer orderUserId) {
		this.orderUserId = orderUserId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Users getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(Users orderUser) {
		this.orderUser = orderUser;
	}

	public List<OrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(Integer carStatus) {
		this.carStatus = carStatus;
	}

	public List<OrderImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<OrderImage> imageList) {
		this.imageList = imageList;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public UnifiedOrderResult getUnifiedOrderResult() {
		return unifiedOrderResult;
	}

	public void setUnifiedOrderResult(UnifiedOrderResult unifiedOrderResult) {
		this.unifiedOrderResult = unifiedOrderResult;
	}

	public Integer getUnifiedOrderResultId() {
		return unifiedOrderResultId;
	}

	public void setUnifiedOrderResultId(Integer unifiedOrderResultId) {
		this.unifiedOrderResultId = unifiedOrderResultId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getAssessStatus() {
		return assessStatus;
	}

	public void setAssessStatus(Integer assessStatus) {
		this.assessStatus = assessStatus;
	}

	public Integer getBackAddressId() {
		return backAddressId;
	}

	public void setBackAddressId(Integer backAddressId) {
		this.backAddressId = backAddressId;
	}

	public UserAddress getBackAddress() {
		return backAddress;
	}

	public void setBackAddress(UserAddress backAddress) {
		this.backAddress = backAddress;
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