package com.justfind.utils;

import java.util.HashMap;
import java.util.Map;

public class PagerHelper {
	public static Map<String, Object> buildParamMap(Integer pageNo,
			Integer pageSize) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null || pageSize.equals(0)) {
			pageSize = 10;
		}
		paramMap.put("pageNo", pageNo - 1);
		paramMap.put("pageSize", pageSize);
		return paramMap;
	}

	public static int calculatePageCount(int totalCount, Integer pageSize) {
		int pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount++;
		}
		return pageCount;
	}
}
