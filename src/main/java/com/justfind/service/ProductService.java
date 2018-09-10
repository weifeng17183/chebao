package com.justfind.service;

import java.util.List;

import com.justfind.entity.Product;

public interface ProductService {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(Product record);

	Product selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Product record);

	List<Product> queryList(Product record);

	Product selectByName(String productName);
}
