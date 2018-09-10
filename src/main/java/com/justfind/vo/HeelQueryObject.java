package com.justfind.vo;

import org.springframework.util.StringUtils;

public class HeelQueryObject extends QueryObject {
	private String keyword;//关键字
	private Integer stuffTypeId;//一级材料类型id
	private Long stuffTypeItemId;//二级材料类型id
	private Integer submitType;//发布类型id
	
	
	public String getKeyword() {
		return StringUtils.hasLength(keyword)? keyword: null;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getStuffTypeId() {
		return stuffTypeId;
	}
	public void setStuffTypeId(Integer stuffTypeId) {
		this.stuffTypeId = stuffTypeId;
	}
	public Long getStuffTypeItemId() {
		return stuffTypeItemId;
	}
	public void setStuffTypeItemId(Long stuffTypeItemId) {
		this.stuffTypeItemId = stuffTypeItemId;
	}
	public Integer getSubmitType() {
		return submitType;
	}
	public void setSubmitType(Integer submitType) {
		this.submitType = submitType;
	}

}
