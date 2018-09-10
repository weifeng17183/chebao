package com.justfind.constant;

public class BaseConstant {

	public static final String WX_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	public static final String WX_URL_RETURN = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	public static final String PAY_METHOD = "POST";

	public static final String LOGIN_SESSION = "LOGINSESSION";

	public static final String HEAD_PORTRAIT = "headPortrait";

	public static final String SHOP_PORTRAIT = "shopPortrait";

	public static final String CAR_PIC = "carPic";

	public static final String ORDER_PIC = "orderPic";

	public static class RefundStatus {
		public static final int WAIT = 0; // 退款申请中
		public static final int SUCCESS = 1; // 退款成功
		public static final int FAIL = 2;// :退款失败
	}
	
	  public static class OrderStatus{
	    	public static final int CANCEL=-1;	//取消订单 
	    	public static final int WAIT=0;	//待支付 
	    	public static final int PAYING=1;	// 支付中
	    	public static final int SUCCESS=2;//支付成功
	    	public static final int FAIL=3;//支付失败
	    	public static final int REFUNDING=4;//退款中
	    	public static final int REFUND=5;//已退款
	    	public static final int REFUND_FAIL=6;//退款失败
	    }

	public static class PayType {
		public static final int ALIPAY = 0; // 阿里
		public static final int WEIXIN = 1; // 微信
	}

	/**
	 * 手机验证码的有效时间
	 */
	public static final int VERIFYCODE_VAILDATE_SECOND = 300;

	public static class UserStates {
		public static final int NORMAL = 1;
		public static final int DISABLED = 2;
	}

	public static class AdminStatus {
		public static final int NORMAL = 1; // 正常
		public static final int DISABLED = 2; // 禁用
	}
 
	 public static class SequenceKey{
        public static final String ORDER_KEY="ORDER_KEY";
        public static final String RETURN_KEY="RETURN_KEY";
    }

	public static class FrontLoginStatus {
		public static final int SUCCESS = 0; // 登录成功
		public static final int FAIL_USER_NAME = -1; // 用户名不存在
		public static final int FAIL_PASSWORD = -2; // 密码错误
		public static final int FAIL_PROVIDER = -3; // 供应商暂不支持登录
	}

}
