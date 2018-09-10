package com.justfind.dao;

import com.justfind.entity.UnifiedOrderResult;

public interface UnifiedOrderResultMapper {
    int deleteByPrimaryKey(Integer unifiedOrderResultId);

    int insert(UnifiedOrderResult record);

    int insertSelective(UnifiedOrderResult record);

    UnifiedOrderResult selectByPrimaryKey(Integer unifiedOrderResultId);

    int updateByPrimaryKeySelective(UnifiedOrderResult record);

    int updateByPrimaryKey(UnifiedOrderResult record);
}