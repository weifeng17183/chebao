package com.justfind.admincontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.justfind.controller.BaseController;
import com.justfind.entity.StuffType;
import com.justfind.service.StuffTypeService;
import com.justfind.utils.PageContext;

/**
 * 大类controller
 * 
 * @author chebao
 *
 */
@Controller(value = "adminStuffTypeController")
@RequestMapping(value = "/admin/stuffType")
public class StuffTypeController extends BaseController {

	@Autowired
	private StuffTypeService stuffTypeService;

	/**
	 * 
	 * @param request
	 * @param model
	 * @param stuffType
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, Model model, Integer stuffTypeId) {
		int istrue = stuffTypeService.delete(stuffTypeId);
		model.addAttribute("redirectUrl", "admin/stuffType/list");
		if (istrue > 0) {
			model.addAttribute("message", "删除失败！有项目使用此分类");
			return ERROR;
		} else {
			model.addAttribute("message", "删除成功！");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String listAll(HttpServletRequest request, Model model, StuffType stuffType) {
		PageContext page = PageContext.getContext(request);
		List<StuffType> list = stuffTypeService.queryList(stuffType);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "stuffType/list";
	}

	@RequestMapping(value = "add")
	public String add(HttpServletRequest request, Model model) {
		return "stuffType/edit";
	}

	@RequestMapping(value = "edit")
	public String edit(HttpServletRequest request, Model model, StuffType stuffType) {
		stuffType = stuffTypeService.selectByPrimaryKey(stuffType.getStuffTypeId());
		model.addAttribute("stuffType", stuffType);
		return "stuffType/edit";
	}

	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, StuffType stuffType) {
		try {
			if (stuffType.getStuffTypeId() == null) {
				stuffTypeService.insertSelective(stuffType);
				model.addAttribute("message", "大类添加成功");
			} else {
				stuffTypeService.updateByPrimaryKeySelective(stuffType);
				model.addAttribute("message", "大类修改成功");
			}
			model.addAttribute("redirectUrl", "admin/stuffType/list");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "保存失败！");
			return ERROR;
		}
	}

	@RequestMapping(value = "/checkName")
	@ResponseBody
	public String checkName(HttpServletRequest request, Model model, StuffType stuffType) {
		StuffType dbStuffType = stuffTypeService.selectByName(stuffType.getStuffTypeName());
		if (dbStuffType != null
				&& (stuffType.getStuffTypeId() == null || dbStuffType.getStuffTypeId() != stuffType.getStuffTypeId())) {
			return "false";
		}
		return "true";
	}
}
