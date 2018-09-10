package com.justfind.entity;


public class WXPayResult {

	private String appId;//公众账号ID
	private String mchId;//微信支付分配的商户号
	private String deviceInfo;//微信支付分配的终端设备号，
	private String nonceStr;//随机字符串，32位：数字+大写字母组合
	private String sign;//签名：回调的时候需要反校验
	private String resultCode;//SUCCESS/FAIL
	private String errCode;//错误返回的信息描述
	private String errCodeDes;//错误返回的信息描述
	private String openid;//用户在商户appid下的唯一标识
	private String isSubscribe;//用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
	private String tradeType;//交易类型:JSAPI、NATIVE、APP
	private String bankType;//银行类型:ICBC_DEBIT:工商银行（借记卡）;ICBC_CREDIT:工商银行（信用卡）;
	private int totalFee;//订单总金额，单位为分;1元=100分；
	private String feeType;//货币种类，符合ISO4217标准的三位字母代码，默认人民币：CNY
	private int cashFee;//实际支付：现金支付金额订单现金支付金额
	private String cashFeeType;//货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
	private int couponFee;//优惠券总金额：代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额
	private int couponCount;//优惠券的数量：1张
	private String couponId0;//代金券或立减优惠ID,$n为下标，从0开始编号;目前一个订单只能用一张优惠券，(后面需要延伸的话，加参数)
	private int couponFee0;//单个代金券或立减优惠支付金额,$n为下标，从0开始编号
	private String transactionId;//微信支付订单号:1217752501201407033233368018
	private String outTradeNo;//商户系统的订单号，与请求一致。交易单号：1212321211201407033568112322
	private String attach;//商家数据包，原样返回:比如阿迪达斯的篮球鞋，白色，35
	private String timeEnd;//支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	private String returnCode;//SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	private String returnMsg;//返回信息，如非空，为错误原因
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
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getIsSubscribe() {
		return isSubscribe;
	}
	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public int getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public int getCashFee() {
		return cashFee;
	}
	public void setCashFee(int cashFee) {
		this.cashFee = cashFee;
	}
	public String getCashFeeType() {
		return cashFeeType;
	}
	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}
	public int getCouponFee() {
		return couponFee;
	}
	public void setCouponFee(int couponFee) {
		this.couponFee = couponFee;
	}
	public int getCouponCount() {
		return couponCount;
	}
	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}
	public String getCouponId0() {
		return couponId0;
	}
	public void setCouponId0(String couponId0) {
		this.couponId0 = couponId0;
	}
	public int getCouponFee0() {
		return couponFee0;
	}
	public void setCouponFee0(int couponFee0) {
		this.couponFee0 = couponFee0;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
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
	

}
