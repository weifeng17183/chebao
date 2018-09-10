package com.justfind.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.justfind.bean.Template;
import com.justfind.bean.TemplateParam;
import com.justfind.bean.Token;
import com.justfind.constant.BaseConstant;
import com.justfind.constant.BaseConstant.SequenceKey;
import com.justfind.dao.OrderImageMapper;
import com.justfind.dao.OrderItemMapper;
import com.justfind.dao.ProductMapper;
import com.justfind.entity.Order;
import com.justfind.entity.OrderImage;
import com.justfind.entity.OrderItem;
import com.justfind.entity.Product;
import com.justfind.exception.CheckServiceException;
import com.justfind.exception.ErrorCode;
import com.justfind.service.OrderService;
import com.justfind.service.SequenceService;
import com.justfind.utils.CommonUtil;
import com.justfind.utils.PageContext;
import com.justfind.view.Message;

@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private OrderImageMapper orderImageMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Autowired
	private ProductMapper productMapper;

	public static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public ResponseEntity<Message> addOrder(HttpServletRequest request, Order order, Integer[] productIds,
			HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		try {
			order.setOrderId(sequenceService.getNextStuffSeq(SequenceKey.ORDER_KEY));
			order.setCreateTime(new Date());
			order.setPayStatus(1);
			orderService.insertSelective(order);
			order = orderService.selectByOrderId(order.getOrderId());
			if (productIds != null && productIds.length > 0) {
				BigDecimal amount = BigDecimal.ZERO;
				for (Integer productId : productIds) {
					Product product = productMapper.selectByPrimaryKey(productId);
					OrderItem orderItem = new OrderItem();
					orderItem.setOrderId(order.getId());
					orderItem.setType(1);
					orderItem.setProductId(product.getId());
					orderItem.setProductName(product.getProductName());
					orderItem.setPrice(product.getPrice());
					orderItem.setDiscount(product.getDiscount());
					orderItem.setStatus(0);
					if (orderItem.getDiscount() != null && orderItem.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
						orderItem.setItemAmount(
								orderItem.getPrice().multiply(orderItem.getDiscount()).multiply(new BigDecimal(0.1)));
					} else {
						orderItem.setItemAmount(orderItem.getPrice());
					}
					orderItemMapper.insertSelective(orderItem);
					amount = amount.add(orderItem.getItemAmount());
				}
				order.setAmount(amount);
				orderService.updateByPrimaryKeySelective(order);
				// String ip = getIPAddr(request);
				// order.setIp(ip);
				// orderService.buildPayParam(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CheckServiceException(ErrorCode.ORDER_ERROR, "下单失败！");
		}
		return buildReturnEntity(new Message(0, "ok", order));
	}

	@RequestMapping(value = "/goPay", method = RequestMethod.POST)
	public ResponseEntity<Message> goPay(HttpServletRequest request, Order order, HttpServletResponse response)
			throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		order = orderService.selectByPrimaryKey(order.getId());
		if (order != null && order.getUnifiedOrderResult() != null
				&& order.getUnifiedOrderResult().getPrepayId() != null) {

		}
		String ip = getIPAddr(request);
		order.setIp(ip);
		orderService.buildPayParam(order);
		return buildReturnEntity(new Message(0, "ok", order));
	}

	@RequestMapping(value = "/reciveOrder", method = RequestMethod.POST)
	public ResponseEntity<Message> reciveOrder(HttpServletRequest request, Order order,
			@RequestParam("picFiles") CommonsMultipartFile[] picFiles, HttpServletResponse response) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		Order dbOrder = orderService.selectByPrimaryKey(order.getId());
		if (dbOrder.getPayStatus() == 1) {
			order.setPayStatus(2);
		}
		order.setUpdateTime(new Date());
		if (picFiles != null && picFiles.length > 0) {
			String path = request.getSession().getServletContext().getRealPath("");
			List<OrderImage> list = new ArrayList<OrderImage>();
			for (int i = 0; i < picFiles.length; i++) {
				String tempPath = path;
				OrderImage entity = new OrderImage();
				MultipartFile multipartFile = picFiles[i];
				if (multipartFile.getSize() > 0) {
					String fileName = multipartFile.getOriginalFilename();
					fileName = sdf1.format(new Date()) + i + ".jpg";
					Thread.sleep(100);
					String relativelyPath = "/" + "images" + "/" + BaseConstant.ORDER_PIC + "/" + dbOrder.getOrderId()
							+ "/";
					tempPath += "WEB-INF" + relativelyPath;
					createPicAndChangeSize(tempPath, fileName, multipartFile);
					entity.setOrderImageUrl(relativelyPath + fileName);
					entity.setOrderId(order.getId());
					list.add(entity);
				}
			}
			if (CollectionUtils.isNotEmpty(list)) {
				for (OrderImage entity : list) {
					orderImageMapper.insertSelective(entity);
				}
			}
		}
		orderService.updateByPrimaryKeySelective(order);
		Template template = new Template();
		template.setTemplateId("Jv-ON6DltLVxWbH7D9_nuY6nKYJTfBDXFKL03WCoWj4"); // tempid
		template.setTouser(dbOrder.getUser().getOpenId());
		Token token = CommonUtil.getToken();
		String tken = token.getAccessToken();
		List<TemplateParam> paras = new ArrayList<TemplateParam>();
		paras.add(new TemplateParam("first", "尊敬的车主，您的车辆我们已接到并安排服务！", "#173177"));
		paras.add(new TemplateParam("keyword1", dbOrder.getCarInfo().getCarNumber(), "#173177"));
		paras.add(new TemplateParam("keyword3", sdf.format(new Date()), "#173177"));
		paras.add(new TemplateParam("keyword5", "易养车管家", "#173177"));
		paras.add(new TemplateParam("remark", "车辆到厂途中，请耐心等待！", "#173177"));
		template.setTemplateParamList(paras);
		CommonUtil.sendTemplateMsg(tken, template);
		return buildReturnEntity(new Message(0, "ok", null));
	}

	@RequestMapping(value = "/selectProductList", method = RequestMethod.POST)
	public ResponseEntity<Message> selectProductList(HttpServletRequest request, Product product,
			HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		PageContext page = PageContext.getContext(request);
		List<Product> list = productMapper.queryList(product);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("page", page);
		return buildReturnEntity(new Message(0, "ok", map));
	}

	@RequestMapping(value = "/selectOrderList", method = RequestMethod.POST)
	public ResponseEntity<Message> selectOrderList(HttpServletRequest request, Order order,
			HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		PageContext page = PageContext.getContext(request);
		List<Order> list = orderService.queryOrderList(order);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("page", page);
		return buildReturnEntity(new Message(0, "ok", map));
	}

	@RequestMapping(value = "/selectOrderDetail", method = RequestMethod.POST)
	public ResponseEntity<Message> selectOrderDetail(HttpServletRequest request, Order order,
			HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		order = orderService.selectByPrimaryKey(order.getId());
		return buildReturnEntity(new Message(0, "ok", order));
	}

	@RequestMapping(value = "/backCar", method = RequestMethod.POST)
	public ResponseEntity<Message> backCar(HttpServletRequest request, Order order, HttpServletResponse response)
			throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		Order dbOrder = orderService.selectByPrimaryKey(order.getId());
		if (dbOrder.getPayStatus() == 6) {
			order.setPayStatus(7);
		}
		orderService.updateByPrimaryKeySelective(order);
		return buildReturnEntity(new Message(0, "ok", null));
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ResponseEntity<Message> confirm(HttpServletRequest request, Order order, HttpServletResponse response)
			throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		Order dbOrder = orderService.selectByPrimaryKey(order.getId());
		if (dbOrder.getPayStatus() == 3) {
			order.setPayStatus(4);
		}
		orderService.updateByPrimaryKeySelective(order);
		return buildReturnEntity(new Message(0, "ok", null));
	}
}
