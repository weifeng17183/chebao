package com.justfind.admincontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justfind.controller.BaseController;
import com.justfind.dao.RateMapper;
import com.justfind.entity.Admin;
import com.justfind.entity.Rate;
import com.justfind.service.AdminService;

@Controller
@RequestMapping(value = "admin/rate")
public class RateController extends BaseController {
	@Autowired
	private RateMapper rateMapper;
	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/enter")
	public String enter(HttpServletRequest request, Model model) {
		return "rate/enter";
	}

	@RequestMapping(value = "/pass")
	public String pass(HttpServletRequest request, Model model, String password) {
		Admin admin = adminService.selectByPrimaryKey(1);
		if (password != null && admin.getPassword().equals(password)) {
			Rate rate = rateMapper.selectOne();
			model.addAttribute("entity", rate);
			return "rate/edit";
		} else {
			model.addAttribute("message", "密码错误！");
			model.addAttribute("redirectUrl", "/admin/rate/enter");
			return ERROR;
		}
	}

	@RequestMapping(value = "/edit")
	public String editRate(HttpServletRequest request, Model model) {
		Rate rate = rateMapper.selectOne();
		model.addAttribute("entity", rate);
		return "rate/edit";
	}

	@RequestMapping(value = "/updateRate")
	public String updateRate(HttpServletRequest request, Model model, Rate rate, String password) {
		try {
			model.addAttribute("redirectUrl", "/admin/rate/edit");
			Admin admin = adminService.selectByPrimaryKey(1);
			if (password != null && admin.getPassword().equals(password)) {
				if (rate.getFactoryRate() + rate.getInvaterRate() + rate.getPlatformRate() != 100) {
					model.addAttribute("message", "修改失败,三个比例相加不等于100！");
					return ERROR;
				}
				rateMapper.updateSelective(rate);
				model.addAttribute("message", "修改成功！");
				return SUCCESS;
			} else {
				model.addAttribute("message", "修改失败,密码错误！");
				return ERROR;
			}
		} catch (Exception e) {
			model.addAttribute("message", "修改失败！");
			return ERROR;
		}
	}
}
