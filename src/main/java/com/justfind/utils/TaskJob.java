package com.justfind.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component("taskJob")
public class TaskJob {

	@Scheduled(cron = "0 0/10 * * * ?")
	public void job() {
	}
}
