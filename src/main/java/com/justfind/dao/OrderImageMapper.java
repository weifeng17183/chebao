package com.justfind.dao;

import com.justfind.entity.OrderImage;

public interface OrderImageMapper {
    int deleteByPrimaryKey(Integer orderImageId);

    int insert(OrderImage record);

    int insertSelective(OrderImage record);

    OrderImage selectByPrimaryKey(Integer orderImageId);

    int updateByPrimaryKeySelective(OrderImage record);

    int updateByPrimaryKey(OrderImage record);
}