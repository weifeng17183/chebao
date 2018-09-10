package com.justfind.starter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ImportResource(value = {"classpath:spring-mvc.xml"})
@ComponentScan(basePackages = {"com.justfind.controller","com.justfind.admincontroller","com.justfind.websitecontroller"})
public class MVCConfig 
{
}
