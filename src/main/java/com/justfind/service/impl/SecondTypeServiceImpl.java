package com.justfind.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justfind.dao.SecondTypeMapper;
import com.justfind.entity.Product;
import com.justfind.entity.SecondType;
import com.justfind.service.ProductService;
import com.justfind.service.SecondTypeService;

@Service("secondTypeService")
public class SecondTypeServiceImpl implements SecondTypeService {

	@Autowired
	private SecondTypeMapper secondTypeMapper;

	@Autowired
	private ProductService productService;

	@Override
	public int deleteByPrimaryKey(Integer secondTypeId) {
		return secondTypeMapper.deleteByPrimaryKey(secondTypeId);
	}

	@Override
	public int insertSelective(SecondType record) {
		return secondTypeMapper.insertSelective(record);
	}

	@Override
	public SecondType selectByPrimaryKey(Integer secondTypeId) {
		return secondTypeMapper.selectByPrimaryKey(secondTypeId);
	}

	@Override
	public int updateByPrimaryKeySelective(SecondType record) {
		return secondTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Integer secondTypeId) {
		Product product = new Product();
		product.setSecondTypeId(secondTypeId);
		List<Product> list = productService.queryList(product);
		if (list.size() > 0) {
			return list.size();
		}
		deleteByPrimaryKey(secondTypeId);
		return 0;
	}

	@Override
	public List<SecondType> queryList(SecondType secondType) {
		return secondTypeMapper.queryList(secondType);
	}

	@Override
	public SecondType selectByName(String secondTypeName) {
		return secondTypeMapper.selectByName(secondTypeName);
	}

	@Override
	public List<SecondType> selectByStuffTypeId(Integer stuffTypeId) {
		return secondTypeMapper.selectByStuffTypeId(stuffTypeId);
	}

}
