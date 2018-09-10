package com.justfind.dao;

import java.util.List;

import com.justfind.entity.Product;
import com.justfind.entity.SecondType;

public interface ProductMapper {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(Product record);

	Product selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Product record);

	List<Product> queryList(Product record);

	Product selectByName(String productName);

	List<Product> selectProductList(SecondType secondType);
}