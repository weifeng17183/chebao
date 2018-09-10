package com.justfind.dao;

import com.justfind.entity.OrderReturnApply;

public interface OrderReturnApplyMapper {
    int deleteByPrimaryKey(String requestNumber);

    int insert(OrderReturnApply record);

    int insertSelective(OrderReturnApply record);

    OrderReturnApply selectByPrimaryKey(String requestNumber);

    int updateByPrimaryKeySelective(OrderReturnApply record);

    int updateByPrimaryKey(OrderReturnApply record);
}