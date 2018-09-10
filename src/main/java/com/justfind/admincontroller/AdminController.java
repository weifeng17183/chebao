package com.justfind.admincontroller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.justfind.controller.BaseController;
import com.justfind.dao.AdminMapper;
import com.justfind.dao.AdminRoleMapper;
import com.justfind.dao.FactoryMapper;
import com.justfind.entity.Admin;
import com.justfind.entity.AdminRole;
import com.justfind.entity.Factory;
import com.justfind.entity.Role;
import com.justfind.service.AdminService;
import com.justfind.service.RoleService;
import com.justfind.utils.PageContext;

/**
 * 管理员相关的Controller(包括增删改查方法)
 * 
 * @author pc
 *
 */
@Controller
@RequestMapping(value = "/admin/admin")
public class AdminController extends BaseController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private RoleService roleService;

	@Resource
	private AdminRoleMapper adminRoleMapper;
	
	@Resource
	private AdminMapper adminMapper;

	@Resource
	private FactoryMapper factoryMapper;

	/**
	 * 后台查看所有的管理员列表
	 * 
	 * @param request
	 * @param model
	 * @param searchBean
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model, Admin searchBean) {
		PageContext page = PageContext.getContext(request);
		List<Admin> adminList = adminService.selectAdminList(searchBean);
		model.addAttribute("adminList", adminList);
		model.addAttribute("page", page);
		model.addAttribute("searchBean", searchBean);
		return "admin/list";
	}
	
	@RequestMapping(value = "/factorylist")
	public String factorylist(HttpServletRequest request, Model model, Admin searchBean) {
		PageContext page = PageContext.getContext(request);
		List<Admin> adminList = adminMapper.queryFactoryAdminList(searchBean);
		model.addAttribute("adminList", adminList);
		model.addAttribute("page", page);
		model.addAttribute("searchBean", searchBean);
		return "admin/factorylist";
	}

	/**
	 * 保存管理员
	 * 
	 * @param request
	 * @param model
	 * @param admin
	 * @return
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, Admin admin, Integer[] roleIds) {
		try {
			List<AdminRole> arList = new ArrayList<AdminRole>();
			if (roleIds != null) {
				for (Integer roleId : roleIds) {
					AdminRole adminRole = new AdminRole();
					adminRole.setRoleId(roleId);
					arList.add(adminRole);
				}
			}
			admin.setArList(arList);
			if (admin.getAdminId() == null) {
				adminService.insertSelective(admin);
				model.addAttribute("message", "添加管理员成功！");
			} else {
				adminService.updateByPrimaryKeySelective(admin);
				model.addAttribute("message", "修改管理员成功！");
			}
			if (admin.getFactoryId()!=null) {
				model.addAttribute("redirectUrl", "admin/admin/factorylist");
			}else{
				model.addAttribute("redirectUrl", "admin/admin/list");
			}
			return SUCCESS;
		} catch (Exception e) {
			model.addAttribute("message", "保存失败！");
			return ERROR;
		}
	}

	/**
	 * 修改进入编辑
	 */
	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, Model model, Integer adminId) {
		List<Role> list = roleService.selectAll();
		List<AdminRole> adminRoleList = adminRoleMapper.selectByAdminId(adminId);
		for (AdminRole adminRole : adminRoleList) {
			for (Role role : list) {
				if (role.getRoleId() == adminRole.getRoleId()) {
					role.setIsChecked(true);
					break;
				}
			}
		}
		Admin admin = adminService.selectByPrimaryKey(adminId);
		model.addAttribute("roleList", list);
		model.addAttribute("admin", admin);
		return "admin/edit";
	}
	
	/**
	 * 修改进入编辑
	 */
	@RequestMapping(value = "/editfactory")
	public String editfactory(HttpServletRequest request, Model model, Integer adminId) {
		List<Role> list = roleService.selectAll();
		List<AdminRole> adminRoleList = adminRoleMapper.selectByAdminId(adminId);
		for (AdminRole adminRole : adminRoleList) {
			for (Role role : list) {
				if (role.getRoleId() == adminRole.getRoleId()) {
					role.setIsChecked(true);
					break;
				}
			}
		}
		Admin admin = adminService.selectByPrimaryKey(adminId);
		model.addAttribute("roleList", list);
		model.addAttribute("admin", admin);
		return "admin/editfactory";
	}

	/**
	 * 修改进入编辑
	 */
	@RequestMapping(value = "/reloadPass")
	public String reloadPass(HttpServletRequest request, Model model, Integer adminId) {
		Admin admin = adminService.selectByPrimaryKey(adminId);
		model.addAttribute("admin", admin);
		return "admin/reloadPass";
	}

	/**
	 * 保存管理员
	 * 
	 * @param request
	 * @param model
	 * @param admin
	 * @return
	 */
	@RequestMapping(value = "/savePassWord")
	public String savePassWord(HttpServletRequest request, Model model, Admin admin, Integer[] roleIds) {
		try {
			adminService.updateByPrimaryKey(admin);
			if (admin.getFactory() != null) {
				factoryMapper.updateByPrimaryKey(admin.getFactory());
			}
			model.addAttribute("message", "修改成功！");
			model.addAttribute("redirectUrl", "login");
			return SUCCESS;
		} catch (Exception e) {
			model.addAttribute("message", "保存失败！");
			return ERROR;
		}
	}
	
	/**
	 * 修改进入编辑
	 */
	@RequestMapping(value = "/creloadPass")
	public String creloadPass(HttpServletRequest request, Model model, Integer adminId) {
		Admin admin = adminService.selectByPrimaryKey(adminId);
		model.addAttribute("admin", admin);
		return "admin/creloadPass";
	}
	
	/**
	 * 保存管理员
	 * 
	 * @param request
	 * @param model
	 * @param admin
	 * @return
	 */
	@RequestMapping(value = "/csavePassWord")
	public String csavePassWord(HttpServletRequest request, Model model, Admin admin, Integer[] roleIds) {
		try {
			adminService.updateByPrimaryKey(admin);
			if (admin.getFactory() != null) {
				factoryMapper.updateByPrimaryKey(admin.getFactory());
			}
			model.addAttribute("message", "修改成功！");
			model.addAttribute("redirectUrl", "clogin");
			return SUCCESS;
		} catch (Exception e) {
			model.addAttribute("message", "保存失败！");
			return ERROR;
		}
	}

	/**
	 * 删除管理员
	 * 
	 * @param request
	 * @param model
	 * @param adminId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Model model, Integer adminId) {
		adminService.deleteByPrimaryKey(adminId);
		model.addAttribute("message", "删除成功！");
		model.addAttribute("redirectUrl", "admin/admin/list");
		return SUCCESS;
	}

	/**
	 * 添加进入编辑
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, Model model) {
		List<Role> list = roleService.selectAll();
		model.addAttribute("roleList", list);
		return "admin/edit";
	}
	
	/**
	 * 添加进入编辑
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addfactory")
	public String addfactory(HttpServletRequest request, Model model) {
		List<Role> list = roleService.selectAll();
		List<Factory> factoryList = factoryMapper.selectAll();
		model.addAttribute("roleList", list);
		model.addAttribute("factoryList", factoryList);
		return "admin/editfactory";
	}

	@RequestMapping(value = "/checkUserName")
	@ResponseBody
	public String checkUserName(HttpServletRequest request, Model model, Admin admin) {
		Admin dbAdmin = adminService.selectByUserName(admin.getUserName());
		if (dbAdmin != null && (admin.getAdminId() == null || dbAdmin.getAdminId() != admin.getAdminId())) {
			return "false";
		}
		return "true";
	}
}
