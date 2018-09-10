package com.justfind.dao;

import com.justfind.entity.CarImage;

public interface CarImageMapper {
    int deleteByPrimaryKey(Integer carImageId);

    int insert(CarImage record);

    int insertSelective(CarImage record);

    CarImage selectByPrimaryKey(Integer carImageId);

    int updateByPrimaryKeySelective(CarImage record);

    int updateByPrimaryKey(CarImage record);
}