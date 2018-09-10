package com.justfind.exception;

import java.util.Locale;

public class CheckServiceException extends BaseLogicException{
	private static final long serialVersionUID = 1L;

	public CheckServiceException(Locale locale, Messageable errorCode, Exception e, Object[] messageParams) {
		super(locale, errorCode, e, messageParams);
	}
	public CheckServiceException(Locale lang, Messageable errorCode,Object... messageParams) {
		super(lang, errorCode, messageParams);
	}
	public CheckServiceException(Messageable errorCode, String message) {
		super(Locale.CHINA, errorCode, message);
	}
	

}
