package com.justfind.admincontroller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justfind.bean.Template;
import com.justfind.bean.TemplateParam;
import com.justfind.bean.Token;
import com.justfind.controller.BaseController;
import com.justfind.dao.FactoryMapper;
import com.justfind.dao.OrderItemMapper;
import com.justfind.dao.ProductMapper;
import com.justfind.dao.RateMapper;
import com.justfind.dao.StuffTypeMapper;
import com.justfind.entity.Admin;
import com.justfind.entity.Factory;
import com.justfind.entity.Order;
import com.justfind.entity.OrderItem;
import com.justfind.entity.Product;
import com.justfind.entity.Rate;
import com.justfind.entity.StuffType;
import com.justfind.entity.Users;
import com.justfind.service.OrderService;
import com.justfind.service.UsersService;
import com.justfind.utils.CommonUtil;
import com.justfind.utils.PageContext;

/**
 * 接车controller
 * 
 * @author chebao
 *
 */
@Controller(value = "adminCOrderController")
@RequestMapping(value = "/admin/corder")
public class COrderController extends BaseController {

	@Autowired
	private RateMapper rateMapper;
	@Autowired
	private OrderService orderService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private StuffTypeMapper stuffTypeMapper;

	@Autowired
	private FactoryMapper factoryMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Autowired
	private ProductMapper productMapper;

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model, Order order) {
		PageContext page = PageContext.getContext(request);
		Admin admin = (Admin) request.getSession().getAttribute("loginAdmin");
		order.setFactoryId(admin.getFactoryId());
		List<Order> list = orderService.queryList(order);
		Users users = new Users();
		users.setUserType(1);
		users.setFactoryId(admin.getFactoryId());
		List<Users> userList = usersService.selectByType(users);
		List<Factory> factoryList = factoryMapper.selectAll();
		model.addAttribute("factoryList", factoryList);
		model.addAttribute("userList", userList);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "corder/list";
	}

	@RequestMapping(value = "/settlement")
	public String settlement(HttpServletRequest request, Model model, Order order) {
		PageContext page = PageContext.getContext(request);
		Admin admin = (Admin) request.getSession().getAttribute("loginAdmin");
		order.setPayStatus(6);
		order.setFactoryId(admin.getFactoryId());
		List<Order> list = orderService.querySettlementList(order);
		Rate rate = rateMapper.selectOne();
		model.addAttribute("rate", rate);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "corder/settlement";
	}

	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, Order order) {
		orderService.updateByPrimaryKeySelective(order);
		Order dbOrder = orderService.selectByPrimaryKey(order.getId());
		Template template = new Template();
		template.setTemplateId("-0D_SX0mALHSLuqRSeEMZANdmpj4xBOjBkyM8ZuL5Ao"); // tempid
		template.setTouser(dbOrder.getUser().getOpenId());
		Token token = CommonUtil.getToken();
		String tken = token.getAccessToken();
		List<TemplateParam> paras = new ArrayList<TemplateParam>();
		paras.add(new TemplateParam("first", "尊敬的车主，已为您的订单安排接车员！", "#173177"));
		paras.add(new TemplateParam("keyword1", dbOrder.getCarInfo().getCarNumber(), "#173177"));
		paras.add(new TemplateParam("keyword2",
				dbOrder.getOrderUser().getName() + "(" + dbOrder.getOrderUser().getMobileNum() + ")", "#173177"));
		StringBuffer sb = new StringBuffer();
		List<OrderItem> list = dbOrder.getItemList();
		for (OrderItem orderItem : list) {
			sb.append(orderItem.getProductName() + " ");
		}
		paras.add(new TemplateParam("keyword3", sb.toString(), "#173177"));
		paras.add(new TemplateParam("keyword4", "易养车管家", "#173177"));
		paras.add(new TemplateParam("keyword5", dbOrder.getOrderId(), "#173177"));
		paras.add(new TemplateParam("remark", "请耐心等候接车员上门取车！", "#173177"));
		template.setTemplateParamList(paras);
		CommonUtil.sendTemplateMsg(tken, template);
		model.addAttribute("message", "设置成功");
		model.addAttribute("redirectUrl", "admin/corder/list");
		return SUCCESS;
	}

	@RequestMapping(value = "/saveFactory")
	public String saveFactory(HttpServletRequest request, Model model, Order order) {
		orderService.updateByPrimaryKeySelective(order);
		model.addAttribute("message", "设置成功");
		model.addAttribute("redirectUrl", "admin/corder/list");
		return SUCCESS;
	}

	@RequestMapping(value = "/saveOrder")
	public String saveOrder(HttpServletRequest request, Model model, Integer id, Order pageorder) {
		String[] productIds = request.getParameterValues("productIds");
		Map<Integer, String> hashMap = new HashMap<Integer, String>();
		for (String productId : productIds) {
			hashMap.put(Integer.parseInt(productId), productId);
		}
		Order dbOrder = orderService.selectByPrimaryKey(id);
		List<OrderItem> itemList = dbOrder.getItemList();
		for (OrderItem orderItem : itemList) {
			if (hashMap.get(orderItem.getProductId()) == null) {
				orderItemMapper.deleteByPrimaryKey(orderItem.getItemId());
			}
		}
		for (String productId : productIds) {
			OrderItem orderItem = orderItemMapper.selectByOrderIdAndProductId(id, Integer.parseInt(productId));
			if (orderItem == null) {
				Product product = productMapper.selectByPrimaryKey(Integer.parseInt(productId));
				OrderItem item = new OrderItem();
				item.setOrderId(id);
				item.setProductId(product.getId());
				item.setProductName(product.getProductName());
				item.setPrice(product.getPrice());
				item.setDiscount(product.getDiscount());
				item.setType(2);
				item.setStatus(0);
				if (item.getDiscount() != null && item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
					item.setItemAmount(item.getPrice().multiply(item.getDiscount()).multiply(new BigDecimal(0.1)));
				} else {
					item.setItemAmount(item.getPrice());
				}
				orderItemMapper.insertSelective(item);
			}
		}
		if (pageorder != null && CollectionUtils.isNotEmpty(pageorder.getItemList())) {
			List<OrderItem> list = pageorder.getItemList();
			for (OrderItem orderItem : list) {
				if (StringUtils.isNotBlank(orderItem.getProductName()) && orderItem.getPrice() != null) {
					orderItem.setOrderId(id);
					orderItem.setType(2);
					orderItem.setStatus(0);
					if (orderItem.getDiscount() != null && orderItem.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
						orderItem.setItemAmount(
								orderItem.getPrice().multiply(orderItem.getDiscount()).multiply(new BigDecimal(0.1)));
					} else {
						orderItem.setItemAmount(orderItem.getPrice());
					}
					orderItemMapper.insertSelective(orderItem);
				}
			}
		}
		dbOrder = orderService.selectByPrimaryKey(id);
		Order order = new Order();
		order.setId(id);
		if (dbOrder.getPayStatus() == 2) {
			order.setPayStatus(3);
		}
		itemList = dbOrder.getItemList();
		BigDecimal amount = BigDecimal.ZERO;
		for (OrderItem orderItem : itemList) {
			amount = amount.add(orderItem.getItemAmount());
		}
		order.setAssessStatus(1);
		order.setAmount(amount);
		orderService.updateByPrimaryKeySelective(order);
		dbOrder = orderService.selectByPrimaryKey(id);
		Template template = new Template();
		template.setTemplateId("bJ4fw_zkdIgRqtibY8wXpL3tW4InwISsrP5VZuS0fpI"); // tempid
		template.setTouser(dbOrder.getUser().getOpenId());
		Token token = CommonUtil.getToken();
		String tken = token.getAccessToken();
		List<TemplateParam> paras = new ArrayList<TemplateParam>();
		paras.add(new TemplateParam("first", "尊敬的车主，您的车辆维修保养已评估完成！", "#173177"));
		paras.add(new TemplateParam("keyword1", dbOrder.getCarInfo().getCarNumber(), "#173177"));
		StringBuffer sb = new StringBuffer();
		List<OrderItem> list = dbOrder.getItemList();
		for (OrderItem orderItem : list) {
			sb.append(orderItem.getProductName() + " ");
		}
		paras.add(new TemplateParam("keyword2", sb.toString(), "#173177"));
		paras.add(new TemplateParam("remark", "请您查看订单中的维修保养项目及相应金额并确认！", "#173177"));
		template.setTemplateParamList(paras);
		CommonUtil.sendTemplateMsg(tken, template);
		model.addAttribute("message", "修改成功");
		model.addAttribute("redirectUrl", "admin/corder/list");
		return SUCCESS;
	}

	@RequestMapping(value = "/relist")
	public String reList(HttpServletRequest request, Model model, Order order) {
		PageContext page = PageContext.getContext(request);
		Admin admin = (Admin) request.getSession().getAttribute("loginAdmin");
		Users users = new Users();
		users.setUserType(1);
		users.setFactoryId(admin.getFactoryId());
		List<Users> userList = usersService.selectByType(users);
		List<Order> list = orderService.queryList(order);
		model.addAttribute("userList", userList);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "corder/relist";
	}

	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, Model model, Integer id) {
		List<StuffType> list = stuffTypeMapper.getProductList();
		Order order = orderService.selectByPrimaryKey(id);
		Iterator<StuffType> iterator = list.iterator();
		while (iterator.hasNext()) {
			StuffType pt = iterator.next();
			for (OrderItem rp : order.getItemList()) {
				for (Product product : pt.getProductList()) {
					if (rp.getProductId() == product.getId()) {
						product.setIsChecked(true);
						if (rp.getStatus() == 1) {
							product.setIsDisabled(true);
						}
						break;
					}
				}
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("order", order);
		return "corder/edit";
	}

	@RequestMapping(value = "/info")
	public String info(HttpServletRequest request, Model model, Integer id) {
		Order order = orderService.selectByPrimaryKey(id);
		model.addAttribute("order", order);
		return "corder/info";
	}

	@RequestMapping(value = "/confirm")
	public String confirm(HttpServletRequest request, Order order, HttpServletResponse response, Model model)
			throws IOException {
		Order dbOrder = orderService.selectByPrimaryKey(order.getId());
		if (dbOrder.getPayStatus() == 4) {
			order.setPayStatus(5);
		}
		orderService.updateByPrimaryKeySelective(order);
		Template template = new Template();
		template.setTemplateId("dZgLMP1b5ukGCLuHLOlOLum9EHeIpk8Tc04WP4UlyDk"); // tempid
		template.setTouser(dbOrder.getUser().getOpenId());
		Token token = CommonUtil.getToken();
		String tken = token.getAccessToken();
		List<TemplateParam> paras = new ArrayList<TemplateParam>();
		paras.add(new TemplateParam("first", "尊敬的车主，您的车辆维修保养已完成！", "#173177"));
		paras.add(new TemplateParam("keyword1", dbOrder.getCarInfo().getCarNumber(), "#173177"));
		paras.add(new TemplateParam("keyword2", sdf.format(new Date()), "#173177"));
		paras.add(new TemplateParam("keyword3", "易养车管家", "#173177"));
		paras.add(new TemplateParam("keyword4", dbOrder.getAmount().toString(), "#173177"));
		paras.add(new TemplateParam("remark", "请您查看相应订单并支付费用！", "#173177"));
		template.setTemplateParamList(paras);
		CommonUtil.sendTemplateMsg(tken, template);
		model.addAttribute("message", "设置成功");
		model.addAttribute("redirectUrl", "admin/corder/list");
		return SUCCESS;
	}
}
