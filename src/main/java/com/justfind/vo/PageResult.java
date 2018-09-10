package com.justfind.vo;

import java.util.Collections;
import java.util.List;


//分页结果对象
@SuppressWarnings("all")
public class PageResult {
	private List listData;//当前页的结果集数据:SQL查询
	private Integer totalCount;//结果总数据:SQL查询

	private Integer currentPage = 1;//当前页
	private Integer pageSize = 5;//每页最多多少条数据

	private Integer prevPage;//上一页
	private Integer nextPage;//下一页
	private Integer totalPage;//总页数/末页

	public static PageResult empty(Integer pageSize){
		return new PageResult(1, pageSize, Collections.EMPTY_LIST, 0);
	}
	//解决分页问题
	public int getTotalPage(){
		return totalPage == 0 ? 1 : totalPage; 
	}
	
	public PageResult(Integer currentPage, Integer pageSize, List listData,
			Integer totalCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.listData = listData;
		this.totalCount = totalCount;
		//------------------------------------------
		totalPage = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;
		prevPage = currentPage - 1 >= 1 ? currentPage - 1 : 1;
		nextPage = currentPage + 1 <= totalPage ? currentPage + 1 : totalPage;
	}
	public List getListData() {
		return listData;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public Integer getPrevPage() {
		return prevPage;
	}
	public Integer getNextPage() {
		return nextPage;
	}

	
	
}
