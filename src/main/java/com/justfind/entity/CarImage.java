package com.justfind.entity;

public class CarImage {
    private Integer carImageId;

    private Integer carId;

    private String carImageUrl;

    public Integer getCarImageId() {
        return carImageId;
    }

    public void setCarImageId(Integer carImageId) {
        this.carImageId = carImageId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl == null ? null : carImageUrl.trim();
    }
}