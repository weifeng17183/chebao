package com.justfind.admincontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.justfind.controller.BaseController;
import com.justfind.entity.Product;
import com.justfind.entity.SecondType;
import com.justfind.entity.StuffType;
import com.justfind.service.ProductService;
import com.justfind.service.SecondTypeService;
import com.justfind.service.StuffTypeService;
import com.justfind.utils.PageContext;
import com.justfind.view.Message;

/**
 * 项目controller
 * 
 * @author chebao
 *
 */
@Controller
@RequestMapping(value = "/admin/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SecondTypeService secondTypeService;

	@Autowired
	private StuffTypeService stuffTypeService;

	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, Model model, Integer productId) {
		productService.deleteByPrimaryKey(productId);
		model.addAttribute("message", "删除成功！");
		return SUCCESS;
	}

	@RequestMapping(value = "list")
	public String list(HttpServletRequest request, Model model, Product product) {
		PageContext page = PageContext.getContext(request);
		List<Product> list = productService.queryList(product);
		List<StuffType> stuffTypeList = stuffTypeService.selectAll();
		model.addAttribute("stuffTypeList", stuffTypeList);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "product/list";
	}

	@RequestMapping(value = "add")
	public String add(HttpServletRequest request, Model model) {
		List<StuffType> stuffTypeList = stuffTypeService.selectAll();
		model.addAttribute("stuffTypeList", stuffTypeList);
		return "product/edit";
	}

	@RequestMapping(value = "edit")
	public String edit(HttpServletRequest request, Model model, Product product) {
		product = productService.selectByPrimaryKey(product.getId());
		List<StuffType> stuffTypeList = stuffTypeService.selectAll();
		model.addAttribute("stuffTypeList", stuffTypeList);
		model.addAttribute("product", product);
		return "product/edit";
	}

	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, Product product) {
		try {
			if (product.getId() == null) {
				productService.insertSelective(product);
				model.addAttribute("message", "项目添加成功");
			} else {
				productService.updateByPrimaryKeySelective(product);
				model.addAttribute("message", "项目修改成功");
			}
			model.addAttribute("redirectUrl", "admin/product/list");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "保存失败！");
			return ERROR;
		}
	}

	@RequestMapping(value = "/checkName")
	@ResponseBody
	public String checkName(HttpServletRequest request, Model model, Product product) {
		Product dbProduct = productService.selectByName(product.getProductName());
		if (dbProduct != null && (product.getId() == null || dbProduct.getId() != product.getId())) {
			return "false";
		}
		return "true";
	}

	@RequestMapping(value = "/getSecondTypeList")
	@ResponseBody
	public ResponseEntity<Message> getSecondTypeList(HttpServletRequest request, Model model, Integer stuffTypeId) {
		List<SecondType> list = secondTypeService.selectByStuffTypeId(stuffTypeId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return buildSuccessReturnEntity(map);
	}

	@RequestMapping(value = "/selectDetail")
	@ResponseBody
	public ResponseEntity<Message> selectDetail(HttpServletRequest request, Model model, Integer id) {
		Product product = productService.selectByPrimaryKey(id);
		return buildSuccessReturnEntity(product);
	}
}
