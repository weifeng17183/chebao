package com.justfind.vo;

import org.springframework.util.StringUtils;

/**
 * 数字字典查询对象
 * @author pc
 *
 */

public class SystemDictionaryQueryObject extends QueryObject{
	private String keyword;
	private Long parentId;
	
	public String getKeyword(){
		return StringUtils.hasLength(keyword)? keyword: null;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
