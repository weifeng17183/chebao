package com.justfind.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;




public class PageContext extends Paginator  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7121138074583892801L;
	private static final Logger logger = Logger.getLogger(PageContext.class);

	private static ThreadLocal<PageContext> context = new ThreadLocal<PageContext>();


	public static PageContext getContext()
	{
		PageContext ci = context.get();
		if(ci == null)
		{
			ci = new PageContext();
			context.set(ci);
		}
		return ci;
	}
	public static PageContext getContext(HttpServletRequest request){
		PageContext pageContext =new PageContext();
		String pageSize = getParameterWithDefault(request, "pageSize", "15");   //每页十条
        pageContext.setPageSize(Integer.parseInt(pageSize));
        String currentPage = getParameterWithDefault(request, "currentPage", "1");
        pageContext.setCurrentPage(Integer.parseInt(currentPage));
        pageContext.setPagination(true);
        context.set(pageContext);
		return pageContext;
	}
	 public static String getParameterWithDefault(HttpServletRequest request, String name, String defaultValue)
	 {
	     String paramValue = request.getParameter(name);
	     return paramValue != null ? paramValue.trim() : defaultValue;
	  }
	public  static void removeContext()
	{
		context.remove();
	}
	protected void initialize() {

	}
	
	public static void setContent(PageContext pc){
	    context.set(pc);
	}
}
