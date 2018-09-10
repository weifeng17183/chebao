package com.justfind.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.justfind.entity.ReturnWeiXinPay;
import com.justfind.entity.UnifiedOrderResult;
import com.justfind.entity.WXPayResult;

/**
 * 微信解析xml：带有CDATA格式的
 */
public class JdomParseXmlUtils {
	private static final Logger LOG = LoggerFactory.getLogger(JdomParseXmlUtils.class);

	/**
	 * 1、统一下单获取微信返回 解析的时候自动去掉CDMA
	 * 
	 * @param xml
	 */
	@SuppressWarnings("unchecked")
	public static UnifiedOrderResult getUnifiedorderResult(String xml) {
		UnifiedOrderResult unifieorderResult = new UnifiedOrderResult();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc;
			doc = (Document) sb.build(source);

			Element root = doc.getRootElement();// 指向根节点
			List<Element> list = root.getChildren();

			if (list != null && list.size() > 0) {
				for (Element element : list) {
					/*
					 * <xml> <return_code><![CDATA[SUCCESS]]></return_code>
					 * <return_msg><![CDATA[OK]]></return_msg>
					 * <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
					 * <mch_id><![CDATA[10000100]]></mch_id>
					 * <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>
					 * <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>
					 * <result_code><![CDATA[SUCCESS]]></result_code>
					 * <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]
					 * ]></prepay_id> <trade_type><![CDATA[JSAPI]]></trade_type>
					 * </xml>
					 */
					System.out.println("key是：" + element.getName() + "，值是：" + element.getText());

					if ("return_code".equals(element.getName())) {
						unifieorderResult.setReturnCode(element.getText());
					}

					if ("return_msg".equals(element.getName())) {
						unifieorderResult.setReturnMsg(element.getText());
					}

					if ("appid".equals(element.getName())) {
						unifieorderResult.setAppId(element.getText());
					}

					if ("mch_id".equals(element.getName())) {
						unifieorderResult.setMchId(element.getText());
					}

					if ("nonce_str".equals(element.getName())) {
						unifieorderResult.setNonceStr(element.getText());
					}

					if ("sign".equals(element.getName())) {
						unifieorderResult.setSign(element.getText());
					}

					if ("result_code".equals(element.getName())) {
						unifieorderResult.setResultCode(element.getText());
					}

					if ("prepay_id".equals(element.getName())) {
						unifieorderResult.setPrepayId(element.getText());
					}

					if ("trade_type".equals(element.getName())) {
						unifieorderResult.setTradeType(element.getText());
					}
				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return unifieorderResult;
	}

	/**
	 * 2、微信回调后参数解析 解析的时候自动去掉CDMA
	 * 
	 * @param xml
	 */
	@SuppressWarnings("unchecked")
	public static WXPayResult getWXPayResult(String xml) {
		WXPayResult wXPayResult = new WXPayResult();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc;
			doc = (Document) sb.build(source);

			Element root = doc.getRootElement();// 指向根节点
			List<Element> list = root.getChildren();

			if (list != null && list.size() > 0) {
				for (Element element : list) {
					System.out.println("key是：" + element.getName() + "，值是：" + element.getText());

					if ("return_code".equals(element.getName())) {
						wXPayResult.setReturnCode(element.getText());
					}

					if ("return_msg".equals(element.getName())) {
						wXPayResult.setReturnMsg(element.getText());
					}

					if ("appid".equals(element.getName())) {
						wXPayResult.setAppId(element.getText());
					}

					if ("mch_id".equals(element.getName())) {
						wXPayResult.setMchId(element.getText());
					}

					if ("nonce_str".equals(element.getName())) {
						wXPayResult.setNonceStr(element.getText());
					}

					if ("sign".equals(element.getName())) {
						wXPayResult.setSign(element.getText());
					}

					if ("result_code".equals(element.getName())) {
						wXPayResult.setResultCode(element.getText());
					}

					if ("return_msg".equals(element.getName())) {
						wXPayResult.setReturnMsg(element.getText());
					}

					if ("return_msg".equals(element.getName())) {
						wXPayResult.setReturnMsg(element.getText());
					}

					if ("return_msg".equals(element.getName())) {
						wXPayResult.setReturnMsg(element.getText());
					}

					if ("return_msg".equals(element.getName())) {
						wXPayResult.setReturnMsg(element.getText());
					}

					if ("return_msg".equals(element.getName())) {
						wXPayResult.setReturnMsg(element.getText());
					}

					if ("return_msg".equals(element.getName())) {
						wXPayResult.setReturnMsg(element.getText());
					}

					if ("return_msg".equals(element.getName())) {
						wXPayResult.setReturnMsg(element.getText());
					}

					if ("return_msg".equals(element.getName())) {
						wXPayResult.setReturnMsg(element.getText());
					}

				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return wXPayResult;
	}

	/**
	 * 3、支付成功微信返回 解析的时候自动去掉CDMA
	 * 
	 * @param xml
	 */
	@SuppressWarnings("unchecked")
	public static ReturnWeiXinPay getReturnWeiXinPay(String xml, String payKey) {
		ReturnWeiXinPay pay = new ReturnWeiXinPay();
		String sign = "";
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc;
			doc = (Document) sb.build(source);

			Element root = doc.getRootElement();// 指向根节点
			List<Element> list = root.getChildren();

			if (list != null && list.size() > 0) {
				for (Element element : list) {

					String returnCode = "";
					if ("return_code".equals(element.getName())) {
						returnCode = element.getText();
						pay.setReturnCode(element.getText());
						parameters.put("return_code", element.getText());
					}

					if ("out_trade_no".equals(element.getName())) {
						pay.setOutTradeNo(element.getText());
						parameters.put("out_trade_no", element.getText());
					}
					if ("sign".equals(element.getName())) {
						pay.setSign(element.getText());
						sign = element.getText();
					}
					if ("result_code".equals(element.getName())) {
						pay.setResultCode(element.getText());
						parameters.put("result_code", element.getText());
					}
					if ("appid".equals(element.getName())) {
						pay.setAppId(element.getText());
						parameters.put("appid", element.getText());
					}

					if ("bank_type".equals(element.getName())) {
						pay.setBankType(element.getText());
						parameters.put("bank_type", element.getText());
					}

					if ("cash_fee".equals(element.getName())) {
						pay.setCashFee(element.getText());
						parameters.put("cash_fee", element.getText());
					}

					if ("device_info".equals(element.getName())) {
						pay.setDeviceInfo(element.getText());
						parameters.put("device_info", element.getText());
					}

					if ("fee_type".equals(element.getName())) {
						pay.setFeeType(element.getText());
						parameters.put("fee_type", element.getText());
					}

					if ("is_subscribe".equals(element.getName())) {
						pay.setIsSubscribe(element.getText());
						parameters.put("is_subscribe", element.getText());
					}

					if ("mch_id".equals(element.getName())) {
						pay.setMchId(element.getText());
						parameters.put("mch_id", element.getText());
					}

					if ("nonce_str".equals(element.getName())) {
						pay.setNonceStr(element.getText());
						parameters.put("nonce_str", element.getText());
					}
					if ("openid".equals(element.getName())) {
						pay.setOpenId(element.getText());
						parameters.put("openid", element.getText());
					}
					if ("time_end".equals(element.getName())) {
						pay.setTimeEnd(element.getText());
						parameters.put("time_end", element.getText());
					}
					if ("total_fee".equals(element.getName())) {
						pay.setTotalFee(element.getText());
						parameters.put("total_fee", element.getText());
					}
					if ("trade_type".equals(element.getName())) {
						pay.setTradeType(element.getText());
						parameters.put("trade_type", element.getText());
					}
					if ("transaction_id".equals(element.getName())) {
						pay.setTransactionId(element.getText());
						parameters.put("transaction_id", element.getText());
					}

				}
			}
			String signClient = WXSignUtils.createSign("UTF-8", parameters, payKey);
			if (!signClient.equals(sign)) {
				LOG.info("支付返回sign不正确-----返回sign:" + sign + ",服务器组装sign:" + signClient);
				// pay.setResultCode("FAIL");
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pay;
	}

}
