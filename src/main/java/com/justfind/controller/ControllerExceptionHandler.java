package com.justfind.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.justfind.exception.BaseLogicException;
import com.justfind.exception.ErrorCode;
import com.justfind.exception.Messageable;
import com.justfind.view.Message;



@Controller
@ControllerAdvice
public class ControllerExceptionHandler{
	
	private static final Log LOGGER = LogFactory.getLog(ControllerExceptionHandler.class);
	
	
	@ExceptionHandler(Throwable.class)  
    @ResponseBody  
    public ResponseEntity<Message> handleThrowable(Throwable ex) { 
		
		LOGGER.error("系统异常!", ex);

		Message message = new Message();
		message.setError(-1);
		message.setData("");
		message.setMsg(ex.getLocalizedMessage());
		 
		return new ResponseEntity<Message>(message, null, HttpStatus.OK);
    }  
		
	
	@ExceptionHandler(IllegalArgumentException.class)  
    @ResponseBody  
    public ResponseEntity<Message> handleIllegalArgumentException(IllegalArgumentException ex) { 
		
		LOGGER.error("参数异常!", ex);

		Message message = new Message();
		message.setError(ErrorCode.PARAMETERS_ILLEGAL.getCode());
		message.setData("");
		message.setMsg(ex.getLocalizedMessage());

		return new ResponseEntity<Message>(message, null, HttpStatus.OK);
    }
	
	@ExceptionHandler(BaseLogicException.class)  
    @ResponseBody  
    public ResponseEntity<Message> handleBaseLogicException(BaseLogicException ex) { 
		
		LOGGER.error("逻辑异常", ex);
		
		Message message = new Message();
		
		String msg = ex.getMessage();
		if( StringUtils.isEmpty(msg) ){
	    	msg = ex.getLocalizedMessage();
	    }
		
		Messageable errorCode = ex.getErrorCode();
		int error = errorCode.getCode();
		message.setError(error);
		message.setData("");
		message.setMsg(msg);

		return new ResponseEntity<Message>(message, null, HttpStatus.OK);
    }
}


