package com.justfind.vo;
import java.util.Date;
/**
 * 用于存放验证码相关的内容,在把这个对象放入session中共享
 * @author pc
 *
 */

public class VerifyCodeVO {

	private String verifyCode;   //验证码
	private String mobileNum;  //手机号码
	private Date lastSenTime;   //最后一次发送的时间
	
	
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public Date getLastSenTime() {
		return lastSenTime;
	}
	public void setLastSenTime(Date lastSenTime) {
		this.lastSenTime = lastSenTime;
	}
	
	
}
