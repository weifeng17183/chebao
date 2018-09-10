package com.justfind.utils;

import javax.servlet.http.HttpServletRequest;



/**
 * Description:获取分页器对象
 * @author  LiChunming
 * @version V1.0 
 * @createDateTime：2010-11-11 下午02:09:07 
 * @Company: MSD. 
 * @Copyright: Copyright (c) 2010
 */
public class PaginatorUtil {
	  public static Paginator getPaginator(HttpServletRequest request)
	    {
	        Paginator paginator = new Paginator();
	        String pageSize = getParameterWithDefault(request, "pageSize", "20");   //每页十条
	        paginator.setPageSize(Integer.parseInt(pageSize));
	        String currentPage = getParameterWithDefault(request, "currentPage", "1");
	        paginator.setCurrentPage(Integer.parseInt(currentPage));
	        return paginator;
	    }

	    public static String getParameterWithDefault(HttpServletRequest request, String name, String defaultValue)
	    {
	        String paramValue = request.getParameter(name);
	        return paramValue != null ? paramValue.trim() : defaultValue;
	    }

}
