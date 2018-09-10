package com.justfind.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderReturnApply {
    private String requestNumber;

    private String orderId;

    private BigDecimal returnAmount;

    private Integer payType;

    private Integer refundStatus;

    private Date refundTime;

    private Date gmtCreate;

    private Date gmtUpdate;

    private String refundFailMemo;

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber == null ? null : requestNumber.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public String getRefundFailMemo() {
        return refundFailMemo;
    }

    public void setRefundFailMemo(String refundFailMemo) {
        this.refundFailMemo = refundFailMemo == null ? null : refundFailMemo.trim();
    }
}