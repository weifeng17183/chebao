package com.justfind.admincontroller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justfind.controller.BaseController;
import com.justfind.dao.PermissionTitleMapper;
import com.justfind.dao.RolePermissionMapper;
import com.justfind.entity.Permission;
import com.justfind.entity.PermissionTitle;
import com.justfind.entity.Role;
import com.justfind.entity.RolePermission;
import com.justfind.service.RoleService;
import com.justfind.utils.PageContext;

/**
 * 角色相关Controller
 * 
 * @author chebao
 *
 */
@Controller
@RequestMapping(value = "admin/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private PermissionTitleMapper permissionTitleMapper;

	/**
	 * 角色添加进入编辑页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, Model model) {
		List<PermissionTitle> list = permissionTitleMapper.listAll();
		Iterator<PermissionTitle> iterator = list.iterator();
		while (iterator.hasNext()) {
			PermissionTitle pt = iterator.next();
			if (CollectionUtils.isEmpty(pt.getPermissionList())) {
				iterator.remove();
				continue;
			}
		}
		model.addAttribute("list", list);
		return "role/edit";
	}

	/**
	 * 角色列表查询
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) {
		PageContext page = PageContext.getContext(request);
		List<Role> list = roleService.listAll();
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "role/list";
	}

	/**
	 * 角色保存
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, Role role, Integer[] permissionIds) {
		try {
			List<RolePermission> rpList = new ArrayList<RolePermission>();
			if (permissionIds != null) {
				for (Integer permissionId : permissionIds) {
					RolePermission rolePermission = new RolePermission(role.getRoleId(), permissionId);
					rpList.add(rolePermission);
				}
			}
			if (role.getRoleId() == null) {
				roleService.insert(role, rpList);
				model.addAttribute("message", "角色添加成功");
			} else {
				roleService.update(role, rpList);
				model.addAttribute("message", "角色修改成功");
			}
			model.addAttribute("redirectUrl", "admin/role/list");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "保存失败！");
			return ERROR;
		}
	}

	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, Model model, Integer id) {
		List<PermissionTitle> list = permissionTitleMapper.listAll();
		List<RolePermission> rpList = rolePermissionMapper.selectByRoleId(id);
		Iterator<PermissionTitle> iterator = list.iterator();
		while (iterator.hasNext()) {
			PermissionTitle pt = iterator.next();
			if (CollectionUtils.isEmpty(pt.getPermissionList())) {
				iterator.remove();
				continue;
			}
			for (RolePermission rp : rpList) {
				for (Permission permission : pt.getPermissionList()) {
					if (rp.getPermissionId() == permission.getPermissionId()) {
						permission.setIsChecked(true);
						break;
					}
				}
			}
		}
		Role role = roleService.selectById(id);
		model.addAttribute("list", list);
		model.addAttribute("role", role);
		return "role/edit";
	}

	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Model model, Integer id) {
		int istrue = roleService.delete(id);
		model.addAttribute("redirectUrl", "admin/role/list");
		if (istrue > 0) {
			model.addAttribute("message", "删除失败！有管理员关联此角色，请先修改管理员");
			return ERROR;
		} else {
			model.addAttribute("message", "删除成功！");
			return SUCCESS;
		}
	}
}
