package com.justfind.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justfind.dao.SequenceMapper;
import com.justfind.service.SequenceService;

@Service
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private SequenceMapper sequenceMapper;
    static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");

    public String getNextStuffSeq(String key) {
        String dateStr = format.format(new Date());
        String seq = dateStr + sequenceMapper.getSeq(key);
        return seq;
    }
}