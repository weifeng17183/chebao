package com.justfind.dao;

import com.justfind.entity.UnifiedOrder;

public interface UnifiedOrderMapper {
    int deleteByPrimaryKey(Integer unifiedOrderId);

    int insert(UnifiedOrder record);

    int insertSelective(UnifiedOrder record);

    UnifiedOrder selectByPrimaryKey(Integer unifiedOrderId);

    int updateByPrimaryKeySelective(UnifiedOrder record);

    int updateByPrimaryKey(UnifiedOrder record);
}