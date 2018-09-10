package com.justfind.vo;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 用于存放当前用户的上下文(获取当前用户)
 * @author pc
 *
 */
public class UserContext {
	public static final String USER_IN_SESSION="logininfo";
	public static final String VERIFYCODE_IN_SESSION="verifycode_in_session";
	
	/**
	 * 从当前线程中获取session
	 * @return
	 */
	private static HttpSession getSession(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}
	
	/**
	 * 从当前线程中获取验证码
	 * @return
	 */
	public static VerifyCodeVO getCurrentVerifyCode(){
		return (VerifyCodeVO) getSession().getAttribute(VERIFYCODE_IN_SESSION);
	}
	/**
	 * 把验证码放入到session中
	 * @param vc
	 */
	public static void putVerifyCode(VerifyCodeVO vc){
		getSession().setAttribute(VERIFYCODE_IN_SESSION, vc);
	}
}
