package com.justfind.dao;

import org.apache.ibatis.annotations.Param;

import com.justfind.entity.OrderItem;

public interface OrderItemMapper {
	int deleteByPrimaryKey(Integer itemId);

	int insert(OrderItem record);

	int insertSelective(OrderItem record);

	OrderItem selectByPrimaryKey(Integer itemId);

	int updateByPrimaryKeySelective(OrderItem record);

	int updateByPrimaryKey(OrderItem record);

	OrderItem selectByOrderIdAndProductId(@Param(value = "orderId") Integer orderId,
			@Param(value = "productId") Integer productId);
}