package com.justfind.entity;

public class OrderImage {
    private Integer orderImageId;

    private Integer orderId;

    private String orderImageUrl;

    public Integer getOrderImageId() {
        return orderImageId;
    }

    public void setOrderImageId(Integer orderImageId) {
        this.orderImageId = orderImageId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderImageUrl() {
        return orderImageUrl;
    }

    public void setOrderImageUrl(String orderImageUrl) {
        this.orderImageUrl = orderImageUrl == null ? null : orderImageUrl.trim();
    }
}