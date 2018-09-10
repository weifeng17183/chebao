package com.justfind.admincontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.justfind.controller.BaseController;
import com.justfind.entity.SecondType;
import com.justfind.entity.StuffType;
import com.justfind.service.SecondTypeService;
import com.justfind.service.StuffTypeService;
import com.justfind.utils.PageContext;

/**
 * 小类controller
 * 
 * @author chebao
 *
 */
@Controller
@RequestMapping(value = "/admin/secondType")
public class SecondTypeController extends BaseController {

	@Autowired
	private SecondTypeService secondTypeService;

	@Autowired
	private StuffTypeService stuffTypeService;

	/**
	 * 权限删除
	 * 
	 * @param request
	 * @param model
	 * @param secondType
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, Model model, Integer secondTypeId) {
		int istrue = secondTypeService.delete(secondTypeId);
		model.addAttribute("redirectUrl", "admin/secondType/list");
		if (istrue > 0) {
			model.addAttribute("message", "删除失败！有项目使用此分类");
			return ERROR;
		} else {
			model.addAttribute("message", "删除成功！");
			return SUCCESS;
		}
	}

	/**
	 * 查询所有权限
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String listAll(HttpServletRequest request, Model model, SecondType searchBean) {
		PageContext page = PageContext.getContext(request);
		List<SecondType> list = secondTypeService.queryList(searchBean);
		List<StuffType> stuffTypeList = stuffTypeService.selectAll();
		model.addAttribute("stuffTypeList", stuffTypeList);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("searchBean", searchBean);
		return "secondType/list";
	}

	@RequestMapping(value = "add")
	public String add(HttpServletRequest request, Model model) {
		List<StuffType> stuffTypeList = stuffTypeService.selectAll();
		model.addAttribute("stuffTypeList", stuffTypeList);
		return "secondType/edit";
	}

	@RequestMapping(value = "edit")
	public String edit(HttpServletRequest request, Model model, SecondType secondType) {
		List<StuffType> stuffTypeList = stuffTypeService.selectAll();
		model.addAttribute("stuffTypeList", stuffTypeList);
		secondType = secondTypeService.selectByPrimaryKey(secondType.getSecondTypeId());
		model.addAttribute("secondType", secondType);
		return "secondType/edit";
	}

	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, SecondType secondType) {
		try {
			if (secondType.getSecondTypeId() == null) {
				secondTypeService.insertSelective(secondType);
				model.addAttribute("message", "小类添加成功");
			} else {
				secondTypeService.updateByPrimaryKeySelective(secondType);
				model.addAttribute("message", "小类修改成功");
			}
			model.addAttribute("redirectUrl", "admin/secondType/list");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "保存失败！");
			return ERROR;
		}
	}

	@RequestMapping(value = "/checkName")
	@ResponseBody
	public String checkName(HttpServletRequest request, Model model, SecondType secondType) {
		SecondType dbSecondType = secondTypeService.selectByName(secondType.getSecondTypeName());
		if (dbSecondType != null && (secondType.getSecondTypeId() == null
				|| dbSecondType.getSecondTypeId() != secondType.getSecondTypeId())) {
			return "false";
		}
		return "true";
	}
}
