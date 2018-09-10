package com.justfind.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.justfind.starter.ShiroPermissionFactory;

@Service("filterChainDefinitionsService")
public class FilterChainDefinitionsService {

	@Resource
	private ShiroPermissionFactory permissionFactory;

	public void reloadFilterChains() {
		synchronized (permissionFactory) { // 强制同步，控制线程安全
			AbstractShiroFilter shiroFilter = null;

			try {
				shiroFilter = (AbstractShiroFilter) permissionFactory.getObject();

				PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) shiroFilter
						.getFilterChainResolver();
				// 过滤管理器
				DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver.getFilterChainManager();
				// 清除权限配置
				manager.getFilterChains().clear();
				permissionFactory.getFilterChainDefinitionMap().clear();
				// 重新设置权限
				permissionFactory.setFilterChainDefinitions(ShiroPermissionFactory.filterChainDefinitions);// 传入配置中的filterchains

				Map<String, String> chains = permissionFactory.getFilterChainDefinitionMap();
				// 重新生成过滤链
				if (!CollectionUtils.isEmpty(chains)) {
					for (Map.Entry<String, String> chain : chains.entrySet()) {
						manager.createChain(chain.getKey(), chain.getValue().replace(" ", ""));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
