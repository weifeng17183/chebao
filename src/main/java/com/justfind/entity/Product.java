package com.justfind.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.justfind.utils.DateJsonSerializer;

public class Product {
	private Integer id;

	private String productName; // 项目名称

	private BigDecimal price;// 价格

	private Integer status;// 状态

	private BigDecimal discount; // 折扣

	@JsonSerialize(using = DateJsonSerializer.class)
	private Date createTime; // 添加时间

	@JsonSerialize(using = DateJsonSerializer.class)
	private Date updateTime;// 修改时间

	private Integer stuffTypeId; // 一级分类id

	private StuffType stuffType; // 一级分类

	private Integer secondTypeId; // 二级分类id

	private SecondType secondType; // 二级分类

	@JsonIgnore
	private Boolean isChecked = false;

	@JsonIgnore
	private Boolean isDisabled = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getSecondTypeId() {
		return secondTypeId;
	}

	public void setSecondTypeId(Integer secondTypeId) {
		this.secondTypeId = secondTypeId;
	}

	public SecondType getSecondType() {
		return secondType;
	}

	public void setSecondType(SecondType secondType) {
		this.secondType = secondType;
	}

	public Integer getStuffTypeId() {
		return stuffTypeId;
	}

	public void setStuffTypeId(Integer stuffTypeId) {
		this.stuffTypeId = stuffTypeId;
	}

	public StuffType getStuffType() {
		return stuffType;
	}

	public void setStuffType(StuffType stuffType) {
		this.stuffType = stuffType;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
}