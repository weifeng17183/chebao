package com.justfind.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justfind.dao.OrderReturnApplyMapper;
import com.justfind.entity.OrderReturnApply;
import com.justfind.service.OrderReturnApplyService;


@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl implements OrderReturnApplyService {
    @Autowired
    private OrderReturnApplyMapper orderReturnApplyMapper;
    
   
    public void deleteByPrimaryKey(String requestNumber){
    	orderReturnApplyMapper.deleteByPrimaryKey(requestNumber);
    }

    public void insert(OrderReturnApply record){
    	record.setGmtCreate(new Date());
    	record.setGmtUpdate(new Date());
    	orderReturnApplyMapper.insert(record);
    }

    public void insertSelective(OrderReturnApply record){
    	record.setGmtCreate(new Date());
    	record.setGmtUpdate(new Date());
    	orderReturnApplyMapper.insertSelective(record);
    }

    public OrderReturnApply selectByPrimaryKey(String requestNumber){
    	return orderReturnApplyMapper.selectByPrimaryKey(requestNumber);
    }

    public void updateByPrimaryKeySelective(OrderReturnApply record){
    	record.setGmtUpdate(new Date());
    	orderReturnApplyMapper.updateByPrimaryKeySelective(record);
    }

    public void updateByPrimaryKey(OrderReturnApply record){
    	record.setGmtUpdate(new Date());
    	orderReturnApplyMapper.updateByPrimaryKey(record);
    }
    

    
}
