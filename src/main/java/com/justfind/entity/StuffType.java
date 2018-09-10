package com.justfind.entity;

import java.util.List;

public class StuffType {
	private Integer stuffTypeId;

	private String stuffTypeName;

	private List<Product> productList;

	public Integer getStuffTypeId() {
		return stuffTypeId;
	}

	public void setStuffTypeId(Integer stuffTypeId) {
		this.stuffTypeId = stuffTypeId;
	}

	public String getStuffTypeName() {
		return stuffTypeName;
	}

	public void setStuffTypeName(String stuffTypeName) {
		this.stuffTypeName = stuffTypeName == null ? null : stuffTypeName.trim();
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}