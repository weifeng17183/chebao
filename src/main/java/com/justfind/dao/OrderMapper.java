package com.justfind.dao;

import java.util.List;

import com.justfind.entity.Order;

public interface OrderMapper {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(Order record);

	Order selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Order record);

	Order selectByOrderId(String orderId);

	List<Order> queryList(Order order);

	List<Order> queryReList(Order order);

	List<Order> queryOrderList(Order order);

	List<Order> querySettlementList(Order order);
}