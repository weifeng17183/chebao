package com.justfind.starter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = { "classpath:applicationContext.xml", "classpath:applicationContext-shiro.xml" })
@ComponentScan(basePackages = { "com.justfind.service", "com.justfind.dao", "com.justfind.authz" })
public class CoreConfig {

}
