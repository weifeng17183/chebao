package com.justfind.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.justfind.constant.BaseConstant;
import com.justfind.constant.BaseConstant.OrderStatus;
import com.justfind.constant.BaseConstant.PayType;
import com.justfind.constant.BaseConstant.RefundStatus;
import com.justfind.constant.BaseConstant.SequenceKey;
import com.justfind.dao.OrderMapper;
import com.justfind.dao.UnifiedOrderMapper;
import com.justfind.dao.UnifiedOrderResultMapper;
import com.justfind.dao.UsersMapper;
import com.justfind.entity.Order;
import com.justfind.entity.OrderReturnApply;
import com.justfind.entity.PayBaseBean;
import com.justfind.entity.UnifiedOrder;
import com.justfind.entity.UnifiedOrderResult;
import com.justfind.entity.Users;
import com.justfind.service.OrderReturnApplyService;
import com.justfind.service.OrderService;
import com.justfind.service.SequenceService;
import com.justfind.utils.HttpClientSendPost;
import com.justfind.utils.HttpXmlUtils;
import com.justfind.utils.JdomParseXmlUtils;
import com.justfind.utils.MD5Util;
import com.justfind.utils.ParseXMLUtils;
import com.justfind.utils.RandCharsUtils;
import com.justfind.utils.WXSignUtils;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private UnifiedOrderMapper unifiedOrderMapper;

	@Autowired
	private UsersMapper usersMapper;

	@Autowired
	private UnifiedOrderResultMapper unifiedOrderResultMapper;

	@Autowired
	private OrderReturnApplyService orderReturnApplyService;

	private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Value("${app_id}")
	private String appId;

	@Value("${mch_id}")
	private String mchId;

	@Value("${device_info}")
	private String deviceInfo;

	@Value("${notify_url}")
	private String notifyUrl;

	@Value("${pay_key}")
	private String payKey;

	@Value("${refund_fee_type}")
	private String refundFeeType;

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHss");

	public int updateByPrimaryKeySelective(Order entity) {
		return orderMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public Order selectByPrimaryKey(Integer orderId) {
		Order order = orderMapper.selectByPrimaryKey(orderId);
		UnifiedOrderResult uor = unifiedOrderResultMapper.selectByPrimaryKey(order.getUnifiedOrderResultId());
		order.setUnifiedOrderResult(uor);
		return order;
	}

	@Override
	public int deleteByPrimaryKey(Integer orderId) {
		return orderMapper.deleteByPrimaryKey(orderId);
	}

	@Override
	public int insertSelective(Order record) {
		return orderMapper.insertSelective(record);
	}

	@Override
	public Order selectByOrderId(String orderId) {
		return orderMapper.selectByOrderId(orderId);
	}

	@Override
	public List<Order> queryList(Order order) {
		return orderMapper.queryList(order);
	}

	@Override
	public List<Order> queryOrderList(Order order) {
		return orderMapper.queryOrderList(order);
	}

	public void buildPayParam(Order order) {
		Users users = usersMapper.selectByPrimaryKey(order.getUserId());
		// 获取一个32位的随机字符串
		String nonceStr = RandCharsUtils.getRandomString(32);
		String body = "车宝管家-汽修服务";
		String detail = "车宝管家-汽修服务";
		String attach = "WEB";
		// 将充值金额扩大100转换成一个4位小数的float类型数
		Float totalFeeTemp = order.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() * 100;
		// 得到整数
		int totalFee = totalFeeTemp.intValue();
		// 参数：开始生成签名
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", appId);
		parameters.put("mch_id", mchId);
		parameters.put("nonce_str", nonceStr);
		parameters.put("body", body);
		parameters.put("detail", detail);
		parameters.put("attach", attach);
		parameters.put("out_trade_no", order.getOrderId());
		parameters.put("total_fee", totalFee);
		String timeStart = RandCharsUtils.timeStart(new Date());
		String timeExpire = RandCharsUtils.timeExpire(new Date());
		parameters.put("time_start", timeStart);
		parameters.put("time_expire", timeExpire);
		parameters.put("notify_url", notifyUrl);
		parameters.put("trade_type", "JSAPI");
		parameters.put("openid", users.getOpenId());
		parameters.put("spbill_create_ip", order.getIp());
		System.out.println("spbill_create_ip:" + order.getIp());
		String sign = WXSignUtils.createSign("UTF-8", parameters, payKey);
		LOG.info("签名是：" + sign);

		UnifiedOrder unifiedorder = new UnifiedOrder();
		unifiedorder.setAppId(appId);
		unifiedorder.setMchId(mchId);
		unifiedorder.setNonceStr(nonceStr);
		unifiedorder.setSign(sign);
		unifiedorder.setBody(body);
		unifiedorder.setDetail(detail);
		unifiedorder.setAttach(attach);
		unifiedorder.setOutTradeNo(order.getOrderId());
		unifiedorder.setTotalFee(totalFee);
		unifiedorder.setSpbillCreateIp(order.getIp());
		unifiedorder.setTimeStart(timeStart);
		unifiedorder.setTimeExpire(timeExpire);
		unifiedorder.setNotifyUrl(notifyUrl);
		unifiedorder.setTradeType("JSAPI");
		unifiedorder.setGmtCreate(new Date());
		unifiedorder.setGmtUpdate(new Date());
		unifiedorder.setOpenId(users.getOpenId());

		// 构造xml参数
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);

		String weixinPost = HttpXmlUtils.httpsRequest(BaseConstant.WX_URL, BaseConstant.PAY_METHOD, xmlInfo).toString();

		LOG.info("预支付返回：" + weixinPost);

		ParseXMLUtils.jdomParseXml(weixinPost, unifiedorder);
		// 保存微信支付信息
		unifiedOrderMapper.insertSelective(unifiedorder);
		// 获取支付返回信息对象
		UnifiedOrderResult unifiedOrderResult = JdomParseXmlUtils.getUnifiedorderResult(weixinPost);

		Long timeStamp = order.getCreateTime().getTime() / 1000;

		String nonceStrResult = MD5Util.MD5Encode("UTF-8", String.valueOf(timeStamp)).toUpperCase();

		unifiedOrderResult.setTimeStamp(timeStamp);
		// 设置随机字符串
		unifiedOrderResult.setNonceStr(nonceStrResult);
		// 返回给APP支付签名
		buildAppReturn(unifiedOrderResult);
		// 给订单绑定 “返回的支付信息对象”
		order.setUnifiedOrderResult(unifiedOrderResult);
		// 保存返回的支付信息对象
		unifiedOrderResultMapper.insertSelective(unifiedOrderResult);
		// 给订单绑定 “返回的支付信息对象id”
		order.setUnifiedOrderResultId(unifiedOrderResult.getUnifiedOrderResultId());

	}

	/**
	 * 返回给APP支付签名
	 * 
	 * @param entity
	 */
	public void buildAppReturn(UnifiedOrderResult entity) {
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appId", entity.getAppId());
		parameters.put("timeStamp", entity.getTimeStamp());
		parameters.put("nonceStr", entity.getNonceStr());
		parameters.put("package", "prepay_id="+entity.getPrepayId());
		parameters.put("signType", "MD5");
		String sign = WXSignUtils.createSign("UTF-8", parameters, payKey);
		entity.setSign(sign);
		LOG.info("返回给前端 sign:" + sign);
	}

	/**
	 * 退款流程
	 */
	public void returnPay(String orderId) {
		Order order = orderMapper.selectByOrderId(orderId);
		OrderReturnApply ora = saveReturnApply(order);
		SortedMap<Object, Object> param = buildReturnApply(ora);
		// 构造xml参数
		String xmlInfo = HttpXmlUtils.xmlReturnApplyInfo(param);

		String weixinPost = "";
		try {
			weixinPost = HttpClientSendPost.sendHttpClientData(BaseConstant.WX_URL_RETURN, xmlInfo, mchId);
		} catch (Exception e) {
			LOG.error(orderId + "退款报错");
			e.printStackTrace();
		}
		LOG.info("退款返回：" + weixinPost);
		PayBaseBean pbb = ParseXMLUtils.jdomParseXml(weixinPost);

		Order entity = new Order();
		entity.setOrderId(order.getOrderId());
		if (pbb.getReturnCode().equals("SUCCESS") && pbb.getResultCode().equals("SUCCESS")) {
			ora.setRefundStatus(RefundStatus.SUCCESS);
			ora.setRefundTime(new Date());
			entity.setPayStatus(OrderStatus.REFUND);
			orderMapper.updateByPrimaryKeySelective(entity);
		} else {
			ora.setRefundStatus(RefundStatus.FAIL);
			entity.setPayStatus(OrderStatus.REFUND_FAIL);
			orderMapper.updateByPrimaryKeySelective(entity);
		}
		orderReturnApplyService.updateByPrimaryKeySelective(ora);
	}

	public OrderReturnApply saveReturnApply(Order order) {
		OrderReturnApply ora = new OrderReturnApply();
		String requestNumber = this.sequenceService.getNextStuffSeq(SequenceKey.RETURN_KEY);
		ora.setRequestNumber(requestNumber);
		ora.setReturnAmount(order.getAmount());
		ora.setPayType(PayType.WEIXIN);
		ora.setOrderId(order.getOrderId());
		ora.setRefundStatus(RefundStatus.WAIT);
		orderReturnApplyService.insertSelective(ora);
		return ora;
	}

	public SortedMap<Object, Object> buildReturnApply(OrderReturnApply ora) {
		String nonceStr = RandCharsUtils.getRandomString(32);
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", appId);
		parameters.put("mch_id", mchId);
		parameters.put("nonce_str", nonceStr);
		parameters.put("out_trade_no", ora.getOrderId());
		parameters.put("out_refund_no", ora.getRequestNumber());
		Float totalFeeTemp = ora.getReturnAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() * 100;
		int totalFee = totalFeeTemp.intValue();
		parameters.put("total_fee", totalFee);
		parameters.put("refund_fee", totalFee);
		parameters.put("op_user_id", mchId);
		String sign = WXSignUtils.createSign("UTF-8", parameters, payKey);
		parameters.put("sign", sign);
		return parameters;
	}

	@Override
	public List<Order> querySettlementList(Order order) {
		return orderMapper.querySettlementList(order);
	}
}
