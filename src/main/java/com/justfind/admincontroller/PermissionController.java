package com.justfind.admincontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justfind.controller.BaseController;
import com.justfind.dao.PermissionMapper;
import com.justfind.dao.PermissionTitleMapper;
import com.justfind.entity.Permission;
import com.justfind.entity.PermissionTitle;
import com.justfind.service.PermissionService;
import com.justfind.utils.PageContext;

/**
 * 权限相关controller
 * 
 * @author chebao
 *
 */
@Controller
@RequestMapping(value = "/admin/permission")
public class PermissionController extends BaseController {

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private PermissionTitleMapper permissionTitleMapper;

	@Autowired
	private PermissionMapper permissionMapper;

	/**
	 * 权限删除
	 * 
	 * @param request
	 * @param model
	 * @param permission
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, Model model, Permission permission) {
		int istrue = permissionService.delete(permission);
		model.addAttribute("redirectUrl", "admin/permission/list");
		if (istrue > 0) {
			model.addAttribute("message", "删除失败！有角色关联此对象，请先解除关联");
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
	public String listAll(HttpServletRequest request, Model model) {
		PageContext page = PageContext.getContext(request);
		List<Permission> list = permissionService.listAll();
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "permission/list";
	}

	@RequestMapping(value = "add")
	public String add(HttpServletRequest request, Model model) {
		List<PermissionTitle> list = permissionTitleMapper.listAll();
		model.addAttribute("list", list);
		return "permission/edit";
	}

	@RequestMapping(value = "edit")
	public String edit(HttpServletRequest request, Model model, Permission permission) {
		permission = permissionMapper.selectByPrimaryKey(permission.getPermissionId());
		List<PermissionTitle> list = permissionTitleMapper.listAll();
		model.addAttribute("permission", permission);
		model.addAttribute("list", list);
		return "permission/edit";
	}

	/**
	 * 角色保存
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, Permission permission) {
		try {
			if (permission.getPermissionId() == null) {
				permissionService.insertSelective(permission);
				model.addAttribute("message", "权限添加成功");
			} else {
				permissionService.updateByPrimaryKeySelective(permission);
				model.addAttribute("message", "权限修改成功");
			}
			model.addAttribute("redirectUrl", "admin/permission/list");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "保存失败！");
			return ERROR;
		}
	}

}
