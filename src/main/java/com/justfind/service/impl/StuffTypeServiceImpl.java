package com.justfind.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justfind.dao.StuffTypeMapper;
import com.justfind.entity.Product;
import com.justfind.entity.SecondType;
import com.justfind.entity.StuffType;
import com.justfind.service.ProductService;
import com.justfind.service.SecondTypeService;
import com.justfind.service.StuffTypeService;

@Service("stuffTypeService")
public class StuffTypeServiceImpl implements StuffTypeService {

	@Autowired
	private StuffTypeMapper stuffTypeMapper;

	@Autowired
	private ProductService productService;

	@Autowired
	private SecondTypeService secondTypeService;

	@Override
	public int deleteByPrimaryKey(Integer stuffTypeId) {
		return stuffTypeMapper.deleteByPrimaryKey(stuffTypeId);
	}

	@Override
	public int insertSelective(StuffType record) {
		return stuffTypeMapper.insertSelective(record);
	}

	@Override
	public StuffType selectByPrimaryKey(Integer stuffTypeId) {
		return stuffTypeMapper.selectByPrimaryKey(stuffTypeId);
	}

	@Override
	public int updateByPrimaryKeySelective(StuffType record) {
		return stuffTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<StuffType> queryList(StuffType record) {
		return stuffTypeMapper.queryList(record);
	}

	@Override
	public List<StuffType> selectAll() {
		return stuffTypeMapper.selectAll();
	}

	@Override
	public int delete(Integer id) {
		Product product = new Product();
		product.setStuffTypeId(id);
		List<Product> list = productService.queryList(product);
		if (list.size() > 0) {
			return list.size();
		}
		SecondType secondType = new SecondType();
		secondType.setStuffTypeId(id);
		List<SecondType> queryList = secondTypeService.queryList(secondType);
		if (queryList.size() > 0) {
			return queryList.size();
		}
		deleteByPrimaryKey(id);
		return 0;
	}

	@Override
	public StuffType selectByName(String stuffTypeName) {
		return stuffTypeMapper.selectByName(stuffTypeName);
	}

}
