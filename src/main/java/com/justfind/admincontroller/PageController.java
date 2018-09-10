package com.justfind.admincontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/page")
public class PageController {
	// 后台
	@RequestMapping(value = "/main")
	public String main() {
		return "page/main";
	}

	// 后台Header
	@RequestMapping(value = "/header")
	public String header() {
		return "page/header";
	}

	// 后台菜单
	@RequestMapping(value = "/menu/{type}", method = RequestMethod.GET)
	public String menu(Model model, HttpServletRequest request, @PathVariable("type") String type) {
		return "menu/" + type;
	}

	// 后台中间(显示/隐藏菜单)
	@RequestMapping(value = "/middle")
	public String middle() {
		return "page/middle";
	}

	// 后台首页
	@RequestMapping(value = "/index")
	public String index() {
		return "page/index";
	}

	// 后台
	@RequestMapping(value = "/cmain")
	public String cmain() {
		return "cpage/main";
	}

	// 后台菜单
	@RequestMapping(value = "/cmenu/{type}", method = RequestMethod.GET)
	public String cmenu(Model model, HttpServletRequest request, @PathVariable("type") String type) {
		return "cmenu/" + type;
	}

	// 后台首页
	@RequestMapping(value = "/cindex")
	public String cindex() {
		return "cpage/index";
	}

	// 后台Header
	@RequestMapping(value = "/cheader")
	public String cheader() {
		return "cpage/header";
	}
}
