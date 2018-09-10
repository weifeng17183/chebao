package com.justfind.entity;

public class SecondType {
    private Integer secondTypeId;

    private Integer stuffTypeId;
    
    private StuffType stuffType;

    private String secondTypeName;

    public Integer getSecondTypeId() {
        return secondTypeId;
    }

    public void setSecondTypeId(Integer secondTypeId) {
        this.secondTypeId = secondTypeId;
    }

    public Integer getStuffTypeId() {
        return stuffTypeId;
    }

    public void setStuffTypeId(Integer stuffTypeId) {
        this.stuffTypeId = stuffTypeId;
    }

    public String getSecondTypeName() {
        return secondTypeName;
    }

    public void setSecondTypeName(String secondTypeName) {
        this.secondTypeName = secondTypeName == null ? null : secondTypeName.trim();
    }

	public StuffType getStuffType() {
		return stuffType;
	}

	public void setStuffType(StuffType stuffType) {
		this.stuffType = stuffType;
	}
}