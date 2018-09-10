package com.justfind.entity;
/**
 * 微信支付返回信息对象
 * @author kuaizhao
 *
 */
public class UnifiedOrderResult {
	private Integer unifiedOrderResultId;
	private String appId;//appid
	private String mchId;//商家id
	private String deviceInfo;//设备号
	private String nonceStr;//随机字符串
	private String sign;//签名
	private String resultCode;//错误码
	private String errCode;//错误代码
	private String errCodeDes;//错误返回的信息描述
	private String tradeType;//调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP
	private String prepayId;//微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
	private String codeUrl;//trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付
	
	private String returnCode;//返回状态码SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	private String returnMsg;//返回信息
	private Long timeStamp;
	private String packages="Sign=WXPay";
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrCodeDes() {
		return errCodeDes;
	}
	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getPrepayId() {
		return prepayId;
	}
	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	public String getCodeUrl() {
		return codeUrl;
	}
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public Integer getUnifiedOrderResultId() {
		return unifiedOrderResultId;
	}
	public void setUnifiedOrderResultId(Integer unifiedOrderResultId) {
		this.unifiedOrderResultId = unifiedOrderResultId;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getPackages() {
		return packages;
	}
	public void setPackages(String packages) {
		this.packages = packages;
	}
	

}
