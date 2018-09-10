package com.justfind.admincontroller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.justfind.controller.BaseController;
import com.justfind.entity.Admin;
import com.justfind.service.AdminService;

/**
 * 后台登录和退出相关
 * 
 * @author pc
 *
 */
@Controller
public class LoginController extends BaseController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("admin", new Admin());
		return "/login";
	}

	@RequestMapping(value = "/clogin", method = RequestMethod.GET)
	public String cloginForm(Model model) {
		model.addAttribute("admin", new Admin());
		return "/clogin";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid Admin admin, BindingResult bindingResult, Model model, HttpServletRequest request) {
		try {
			if (bindingResult.hasErrors()) {
				return "/login";
			}
			SecurityUtils.getSubject().login(new UsernamePasswordToken(admin.getUserName(), admin.getPassword()));
			SecurityUtils.getSubject().getSession().setTimeout(-1000l);
			admin = adminService.selectByUserName(admin.getUserName());
			request.getSession().setAttribute("loginAdmin", admin);
			if (admin.getFactoryId() == null) {
				return "redirect:/admin/page/main";
			} else {
				model.addAttribute("message", "用户名或密码错误");
				model.addAttribute("redirectUrl", "login");
				return ERROR;
			}
		} catch (AuthenticationException e) {
			model.addAttribute("message", "用户名或密码错误");
			model.addAttribute("redirectUrl", "login");
			return ERROR;
		}
	}

	@RequestMapping(value = "/clogin", method = RequestMethod.POST)
	public String clogin(@Valid Admin admin, BindingResult bindingResult, Model model, HttpServletRequest request) {
		try {
			if (bindingResult.hasErrors()) {
				return "/clogin";
			}
			SecurityUtils.getSubject().login(new UsernamePasswordToken(admin.getUserName(), admin.getPassword()));
			SecurityUtils.getSubject().getSession().setTimeout(-1000l);
			admin = adminService.selectByUserName(admin.getUserName());
			request.getSession().setAttribute("loginAdmin", admin);
			if (admin.getFactoryId() != null) {
				return "redirect:/admin/page/cmain";
			} else {
				model.addAttribute("message", "用户名或密码错误");
				model.addAttribute("redirectUrl", "clogin");
				return ERROR;
			}
		} catch (AuthenticationException e) {
			model.addAttribute("message", "用户名或密码错误");
			model.addAttribute("redirectUrl", "clogin");
			return ERROR;
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		SecurityUtils.getSubject().logout();
		return "/login";
	}

	@RequestMapping(value = "/clogout", method = RequestMethod.GET)
	public String clogout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		SecurityUtils.getSubject().logout();
		return "/clogin";
	}

	@RequestMapping("/403")
	public String unauthorizedRole() {
		return "/403";
	}
}
