package com.justfind.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justfind.dao.ProductMapper;
import com.justfind.entity.Product;
import com.justfind.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return productMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Product record) {
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setStatus(1);
		return productMapper.insertSelective(record);
	}

	@Override
	public Product selectByPrimaryKey(Integer id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Product record) {
		record.setUpdateTime(new Date());
		return productMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Product> queryList(Product record) {
		return productMapper.queryList(record);
	}

	@Override
	public Product selectByName(String productName) {
		return productMapper.selectByName(productName);
	}

}
