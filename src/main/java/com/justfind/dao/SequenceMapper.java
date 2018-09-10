package com.justfind.dao;

import com.justfind.entity.Sequence;

public interface SequenceMapper {
    int deleteByPrimaryKey(String name);

    int insert(Sequence record);

    int insertSelective(Sequence record);

    Sequence selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(Sequence record);

    int updateByPrimaryKey(Sequence record);
    
    int getSeq(String key);
    
    String getStringSeq(String key);
}