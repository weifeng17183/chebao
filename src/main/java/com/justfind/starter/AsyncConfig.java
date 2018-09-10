package com.justfind.starter;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;


@Configuration
@EnableAsync(
	    mode = AdviceMode.PROXY, proxyTargetClass = true,
	    order = 1
)
public class AsyncConfig {
}
