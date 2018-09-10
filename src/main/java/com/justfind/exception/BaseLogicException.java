package com.justfind.exception;

import java.util.Locale;

import org.springframework.core.NestedRuntimeException;


public class BaseLogicException extends NestedRuntimeException {
	private Messageable errorCode;
	private Object[] messageParams;
	private String message;
	private static final long serialVersionUID = -8215216116902160982L;

	public BaseLogicException(Locale locale, Messageable errorCode,
			Object... messageParams) {
		super(errorCode.toString());
		this.errorCode = errorCode;
		this.messageParams = messageParams;
		this.message=messageParams[0].toString();
	}

	public BaseLogicException(Locale locale, Messageable errorCode,
			Exception e, Object... messageParams) {
		super(errorCode.toString(), e);
		this.errorCode = errorCode;
		this.messageParams = messageParams;
		this.message=messageParams[0].toString();
	}

	public Messageable getErrorCode() {
		return errorCode;
	}

	public Object[] getMessageParams() {
		return messageParams;
	}

	public String getMessage() {
		return message;
	}
}
