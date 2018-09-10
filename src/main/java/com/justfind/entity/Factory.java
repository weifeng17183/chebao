package com.justfind.entity;

public class Factory {
    private Integer id;

    private String factoryName;

    private String factoryAdress;

    private String factoryDesc;

    private String factoryMobile;

    private String factoryContract;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    public String getFactoryAdress() {
        return factoryAdress;
    }

    public void setFactoryAdress(String factoryAdress) {
        this.factoryAdress = factoryAdress == null ? null : factoryAdress.trim();
    }

    public String getFactoryDesc() {
        return factoryDesc;
    }

    public void setFactoryDesc(String factoryDesc) {
        this.factoryDesc = factoryDesc == null ? null : factoryDesc.trim();
    }

    public String getFactoryMobile() {
        return factoryMobile;
    }

    public void setFactoryMobile(String factoryMobile) {
        this.factoryMobile = factoryMobile == null ? null : factoryMobile.trim();
    }

    public String getFactoryContract() {
        return factoryContract;
    }

    public void setFactoryContract(String factoryContract) {
        this.factoryContract = factoryContract == null ? null : factoryContract.trim();
    }
}