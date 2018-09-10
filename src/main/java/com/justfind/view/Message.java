package com.justfind.view;

public class Message {
	private String msg;
	private int error;
	private Object data;
	
	public Message() {

	}

	public Message(int error, String msg, Object data) {
		this.error = error;
		this.msg = msg;
		this.data = data;
	}

	
	public Message(int error, String msg, Object data,long total) {
		this.error = error;
		this.msg = msg;
		this.data = data;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
