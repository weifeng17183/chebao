package com.justfind.utils;

import java.util.Date;

public class DateUtil {
	/**
	 * 两个时间的时间间隔
	 * 
	 */
	public static long secondsBetween(Date d1, Date d2) {
		return Math.abs((d1.getTime() - d2.getTime()) / 1000);
	}

	public static Date getEndDate(Date endDate) {
		int dayMis = 1000 * 60 * 60 * 24;
		long curMillisecond = endDate.getTime();
		long resultMis = curMillisecond + (dayMis - 1);
		Date resultDate = new Date(resultMis);
		return resultDate;
	}
}
