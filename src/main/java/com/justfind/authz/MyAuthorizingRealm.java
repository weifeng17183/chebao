package com.justfind.authz;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justfind.dao.PermissionMapper;
import com.justfind.dao.RoleMapper;
import com.justfind.entity.Admin;
import com.justfind.entity.Permission;
import com.justfind.entity.Role;
import com.justfind.service.AdminService;

@Service
@Transactional
public class MyAuthorizingRealm extends AuthorizingRealm {

	@Resource
	private AdminService adminService;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private PermissionMapper permissionMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录时输入的用户名
		String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
		// 到数据库查是否有此对象
		Admin admin = adminService.selectByUserName(loginName);
		if (admin != null) {
			// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 用户的角色
			List<Role> roleList = roleMapper.selectByAdminId(admin.getAdminId());
			for (Role role : roleList) {
				info.addRole(role.getRoleName());
			}
			// 用户的角色对应的所有权限
			List<Permission> permissionList = permissionMapper.selectByAdminId(admin.getAdminId());
			for (Permission permission : permissionList) {
				info.addStringPermission(permission.getPermissionName());
			}
			return info;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		token.setRememberMe(true);
		Admin admin = adminService.selectByUserName(token.getUsername());
		if (admin != null && admin.getPassword().equals(new String((char[]) token.getCredentials()))) {
			return new SimpleAuthenticationInfo(admin.getUserName(), admin.getPassword(), getName());
		}
		return null;
	}
}
