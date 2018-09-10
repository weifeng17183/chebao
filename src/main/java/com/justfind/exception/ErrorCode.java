package com.justfind.exception;

/**
 * Mapping message enum.
 */
public enum ErrorCode implements Messageable {
    FAIL(0), 
    CHECK_AUTH_FAILED(-1), 
    SQL_ERROR(-2),
    PARAMETERS_ILLEGAL(-3),
    REGISTERED(10001),
    NOT_REGISTERED(10002),
    PASSWORD_ERROR(10003),
    ORDER_ERROR(10004),
    ;

	private int code;

	ErrorCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
