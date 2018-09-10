package com.justfind.utils;
/**
 * 结果出来类
 * @author chebao
 *
 */
public class JSONResult {
	private Boolean success=true;
	private String msg;
	public JSONResult() {
		super();
	}
	public JSONResult(String msg) {
		super();
		this.msg = msg;
	}
	public JSONResult(Boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
