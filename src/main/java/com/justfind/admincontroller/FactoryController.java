package com.justfind.admincontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justfind.controller.BaseController;
import com.justfind.dao.FactoryMapper;
import com.justfind.entity.Factory;
import com.justfind.utils.PageContext;

@Controller(value = "adminFactoryController")
@RequestMapping(value = "/admin/factory")
public class FactoryController extends BaseController {

	@Autowired
	private FactoryMapper factoryMapper;

	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, Factory factory) {
		try {
			factoryMapper.updateByPrimaryKeySelective(factory);
			model.addAttribute("message", "修改成功！");
			model.addAttribute("redirectUrl", "admin/factory/list");
			return SUCCESS;
		} catch (Exception e) {
			model.addAttribute("message", "修改失败！");
			return ERROR;
		}
	}

	@RequestMapping(value = "insert")
	public String insert(HttpServletRequest request, Model model, Factory factory) {
		try {
			factoryMapper.insertSelective(factory);
			model.addAttribute("message", "保存成功！");
			model.addAttribute("redirectUrl", "admin/factory/list");
			return SUCCESS;
		} catch (Exception e) {
			model.addAttribute("message", "爆次你失败！");
			return ERROR;
		}
	}

	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, Model model, Integer factoryId) {
		Factory factory = factoryMapper.selectByPrimaryKey(factoryId);
		model.addAttribute("factory", factory);
		return "factory/edit";
	}

	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, Model model) {
		return "factory/add";
	}

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model, Factory factory) {
		PageContext page = PageContext.getContext(request);
		List<Factory> factoryList = factoryMapper.queryFactoryList(factory);
		model.addAttribute("factoryList", factoryList);
		model.addAttribute("page", page);
		model.addAttribute("factory", factory);
		return "factory/list";
	}
}
