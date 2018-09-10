package com.justfind.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justfind.entity.Order;
import com.justfind.entity.ReturnWeiXinPay;
import com.justfind.service.OrderService;
import com.justfind.utils.HttpXmlUtils;
import com.justfind.utils.JdomParseXmlUtils;

@RestController
@RequestMapping(value = "/notify")
public class NotifyController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(NotifyController.class);

	@Autowired
	private OrderService ordersService;

	@Value("#{configProperties['pay_key']}")
	private String payKey;

	@RequestMapping(value = "/returnPay")
	public synchronized void returnPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String param = getNoRequestParam(request);
		// LOG.info("支付回调：\n"+param);
		ReturnWeiXinPay wxPayResult = JdomParseXmlUtils.getReturnWeiXinPay(param, payKey);
		Order order = new Order();
		order.setOrderId(wxPayResult.getOutTradeNo());
		PrintWriter writer = response.getWriter();
		if (wxPayResult.getReturnCode().equals("SUCCESS") && wxPayResult.getResultCode().equals("SUCCESS")) {
			Order dataBean = ordersService.selectByOrderId(wxPayResult.getOutTradeNo());
			// order.setTransactionId(wxPayResult.getTransactionId());
			dataBean.setPayStatus(6);
			ordersService.updateByPrimaryKeySelective(dataBean);
			writer.write(HttpXmlUtils.backWeixin("SUCCESS", "OK"));
		} else {
			LOG.error(order.getOrderId() + "支付失败");
			// order.setPayStatus(3);
			// ordersService.updateByPrimaryKeySelective(order);
			writer.write(HttpXmlUtils.backWeixin("FAIL", wxPayResult.getReturnCode()));
		}
		if (writer != null) {
			writer.close();
		}
	}

	@RequestMapping(value = "/sayHi")
	public String sayHi() throws Exception {
		return "sayHi";
	}

}
