package com.justfind.dao;

import com.justfind.entity.Rate;

public interface RateMapper {
	int insert(Rate record);

	int insertSelective(Rate record);

	int updateSelective(Rate record);

	Rate selectOne();
}