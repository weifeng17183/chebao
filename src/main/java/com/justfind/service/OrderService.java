package com.justfind.service;

import java.util.List;

import com.justfind.bean.OrderCollect;
import com.justfind.entity.Order;

public interface OrderService {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(Order record);

	Order selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Order record);

	Order selectByOrderId(String orderId);

	List<Order> queryList(Order order);

	List<Order> queryOrderList(Order order);

	void buildPayParam(Order order);

	List<Order> querySettlementList(Order order);

	List<OrderCollect> queryCollectList(Order order);
}
