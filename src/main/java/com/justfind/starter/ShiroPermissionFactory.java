package com.justfind.starter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.justfind.entity.Permission;
import com.justfind.service.PermissionService;

public class ShiroPermissionFactory extends ShiroFilterFactoryBean {

	@Autowired
	private PermissionService permissionService;

	/** 记录配置中的过滤链 */
	public static String filterChainDefinitions = "";// 这个要和配置文件中的名称要一样

	/**
	 * 初始化设置过滤链
	 */
	@Override
	public void setFilterChainDefinitions(String definitions) {
		filterChainDefinitions = definitions;// 记录配置的静态过滤链
		Map<String, String> otherChains = new LinkedHashMap<String, String>();
		List<Permission> list = permissionService.listAll();
		for (Permission permission : list) {
			if (!StringUtils.isEmpty(permission.getPath())) {
				otherChains.put(permission.getPath(), "perms[" + permission.getPermissionName() + "]");
			}
		}
		otherChains.put("/admin/**", "authc");
		// 加载配置默认的过滤链
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		// 加上数据库中过滤链
		section.putAll(otherChains);
		setFilterChainDefinitionMap(section);

	}
}
