package com.justfind.service;

import com.justfind.entity.OrderReturnApply;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>maxmin<br>
 * <b>日期：</b> 2016年8月4日 <br>
 * <b>版权所有：<b>版权所有(C) 2015，快找网络<br>
 */
public interface OrderReturnApplyService {
	public void deleteByPrimaryKey(String requestNumber);

    public void insert(OrderReturnApply record);

    public void insertSelective(OrderReturnApply record);

    public OrderReturnApply selectByPrimaryKey(String requestNumber);

    public void updateByPrimaryKeySelective(OrderReturnApply record);

    public void updateByPrimaryKey(OrderReturnApply record);
}
