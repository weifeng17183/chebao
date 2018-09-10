package com.justfind.entity;

public class Permission {

	private Integer permissionId;

	private String permissionName;

	private String permissionSign;

	private Integer titleId;

	/**
	 * 权限路径控制路径（支持通配符（*），多路径可以都好分割，如/user/info/**,/user/update）
	 */
	private String path;

	private PermissionTitle permissionTitle;

	private Boolean isChecked = false;

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionSign() {
		return permissionSign;
	}

	public void setPermissionSign(String permissionSign) {
		this.permissionSign = permissionSign;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	public PermissionTitle getPermissionTitle() {
		return permissionTitle;
	}

	public void setPermissionTitle(PermissionTitle permissionTitle) {
		this.permissionTitle = permissionTitle;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}