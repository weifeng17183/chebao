package com.justfind.service;
/**
 * 用作于手机验证相关服务
 * @author pc
 *
 */
public interface IVerifyCodeService {
	/**
	 * 给指定的手机  发送验证码
	 * @param phoneNumber
	 */
	void sendVerifyCode(String phoneNumber);
	
	/**
	 * 验证手机验证码,
	 */
	boolean verify(String phoneNumber ,String verifyCode);
}
