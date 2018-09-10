package com.justfind.vo;


//所有的高级查询对象基本
public class QueryObject {
	private Integer currentPage = 1;
	private Integer pageSize = 15;
	
	public int getStart(){
		return (currentPage - 1)*pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
