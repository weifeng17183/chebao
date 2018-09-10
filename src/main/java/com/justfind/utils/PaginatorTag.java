package  com.justfind.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class PaginatorTag extends TagSupport{
	private static Logger logger=Logger.getLogger(PaginatorTag.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String form;// 页面的form名

	private String action;// 跳转的action URL		

	private String name; // 分页器的名称

	private String scope = "request"; // 分页器所在位置page/request/session/application

	private static final Map<String,Integer> scopeMap = new HashMap<String,Integer>(); // 属性范围Map

	static {
		// 初始化属性范围Map
		scopeMap.put("page", PageContext.PAGE_SCOPE);
		scopeMap.put("request",PageContext.REQUEST_SCOPE);
		scopeMap.put("session",PageContext.SESSION_SCOPE);
		scopeMap.put("application", PageContext.APPLICATION_SCOPE);
	}
	public int doEndTag() throws JspTagException {
		Paginator paginator = (Paginator) pageContext.getAttribute(name,
				((Integer) scopeMap.get(scope)).intValue());
		JspWriter out = pageContext.getOut();
		StringBuffer buffer = new StringBuffer(1000);
		int totalPage = paginator.getTotalPage();
		try {
			if (paginator.getTotalCount() > 0 && totalPage>1) {// 有记录
				buffer.append("<script language=\"JavaScript\">\n");
				buffer.append("<!--\n");
				buffer.append("function GotoPage(id,flag) {\n");
				buffer.append("document." + form + ".action='" + action
						+ "';\n");
				buffer.append("if(id<=0){id=1;}\n");
				buffer.append("document." + form + ".currentPage.value=id;\n");
				buffer.append("document." + form + ".currentFlag.value=flag;\n");
				buffer.append("document." + form + ".submit();\n");
				buffer.append("}\n");
				buffer.append("function checkPage(size,page,total) {\n");
				buffer.append("if(total)");
				buffer.append("document." + form + ".action='" + action
						+ "';\n");
				buffer.append("document." + form + ".currentPage.value=id;\n");
				buffer.append("document." + form + ".submit();\n");
				buffer.append("}\n");		
				
				buffer.append("function checkPageSize(page,size,total){\n");
				buffer.append("var reg = /[^0-9]/;\n");
				buffer.append("if(size.match(reg) != null){\n");
				buffer.append("alert('每页记录数请使用数字');\n");				
				buffer.append("document."+form+".pageSize.focus();}\n");
				buffer.append("if(size<1){\n");
				buffer.append("document."+form+".pageSize.value=10;}\n");
				buffer.append("if(size>300){\n");
				buffer.append("document."+form+".pageSize.value=300;}\n");
				buffer.append("var newsize = document."+form+".pageSize.value;\n");
				buffer.append("checkPage(page,newsize,total);\n");
				buffer.append("}\n");
				buffer.append("function checkPage(page,size,total){\n");
				buffer.append("var reg = /[^0-9]/;\n");
				buffer.append("if(page.match(reg) != null){\n");
				buffer.append("alert('跳转页数请使用数字');\n");				
				buffer.append("document."+form+".currentPage.focus();}\n");
				buffer.append("var num = total/size;  \n");
				buffer.append("var nums = Math.round(num+0.4999999);\n");
				buffer.append("if(page<1){\n");
				buffer.append("document."+form+".currentPage.value=1;}\n");
				buffer.append("if(page > nums){\n");
				buffer.append("document."+form+".currentPage.value=nums;}\n");
				buffer.append("}\n");
				buffer.append("//-->\n");
				buffer.append("</script>\n");
				
				/**=====================以上为跳转的js 以下为显示的html========================*/
				buffer.append("<div class=\"clearall\">");
				buffer.append("<div class='pagination'>" );
				if (paginator.getCurrentPage() > 1) {// 不是第一页，显示“第一页”，“上一页”链接

					buffer.append("<a href=\"javascript:GotoPage("+ (paginator.getCurrentPage() - 1)+ ",0)\" class=\"page-prev\" title=\"上一页\"><span>上一页</span></a>"); 
					if (paginator.getCurrentPage() > 4 && totalPage>5){
				 	}
				} else {// 是第一页，不显示“第一页”，“上一页”链接
//					buffer.append("<input type=\"button\" class=\"first\" value=\"首&nbsp;&nbsp;页\" disabled=\"disabled\" />");
//					buffer.append("<input type=\"button\" class=\"prev\"  value=\"上一页\" disabled=\"disabled\" />");
				}
				//对中间页面的处理
				if(paginator.getTotalPage()>=paginator.getShowPage()){
					int frist=getFrist(paginator);
					if(frist>1){
						buffer.append("<a href=\"javascript:GotoPage(1,0)\" class=\"next\" title=\"首页\"><span>1</span></a>");
						if(frist>(1+1)){
							buffer.append("<span class=\"page-break\">...</span>");
						}
					}
					for (int i=frist;i<frist+paginator.getShowPage();i++){
						//判断是否为当前页
						if(paginator.getCurrentPage()==i){
							buffer.append("<span class=\"page-cur\" >"+i+ "</span>");
						}else{
							buffer.append("<a href=\"javascript:GotoPage("+i+ ",0)\" class=\"next\" title=\"\"><span>"+i+"</span></a>");
						}
					}
					if(frist+paginator.getShowPage()<paginator.getTotalPage()){
						buffer.append("<span class=\"page-break\">...</span>");
					}
				}else{
					for(int i=1;i<=paginator.getTotalPage();i++){
						//判断是否为当前页
						if(paginator.getCurrentPage()==i){
							buffer.append("<span class=\"page-cur\" >"+i+ "</span>");
						}else{
							buffer.append("<a href=\"javascript:GotoPage("+i+ ",0)\" class=\"next\" title=\"\"><span>"+i+"</span></a>");
						}
					}
				}
				if (paginator.getCurrentPage() == totalPage || totalPage <= 1) {// 是最后页或共只有一页，不显示“下一页”，“最后页”链接
//					buffer.append("<input type=\"button\" class=\"next\" value=\"下一页\" disabled=\"disabled\" />");
//					buffer.append("<input type=\"button\" class=\"last\" value=\"尾&nbsp;&nbsp;页\" disabled=\"disabled\" />");
				} else {// 不是最后页且总页数大于1页，显示“下一页”，“最后页”链接
					int calIndex=totalPage-paginator.getCurrentPage();
					if(calIndex>3 && totalPage>7){
						buffer.append("<a href=\"javascript:GotoPage("+ totalPage+ ",0)\" class=\"next\" title=\"尾页\"><span>"+totalPage+"</span></a>"); 
					}
					buffer.append("<a href=\"javascript:GotoPage("+ (paginator.getCurrentPage() + 1)+ ",0)\" class=\"page-next\" title=\"下一页\"><span>下一页</span></a>");
				}
				buffer.append(" <input type='hidden'  name='currentPage' class=\"page-skip\" value='"+ paginator.getCurrentPage() + "'onkeyup=\"value=value.replace(/^[^1-9]+|[^\\d]/g,'');\" onblur='checkPage(document.all.currentPage.value,document.all.pageSize.value,"+paginator.getTotalCount()+")'/>");
				buffer.append(" <input type='hidden'  name='currentFlag' class=\"page-skip\" onkeyup=\"value=value.replace(/^[^1-9]+|[^\\d]/g,'');\" onblur='checkPage(document.all.currentPage.value,document.all.pageSize.value,"+paginator.getTotalCount()+")'/>");
				//buffer.append("页" );	
				//buffer.append("<button type=\"submit\" title=\"指定页码\" onclick=\"javascript:GotoPage(document.all.currentPage.value);\">确定</button></span>");
//				buffer.append("<a href=\"javascript:GotoPage(document.all.currentPage.value);\" class=\"bt_go\" title=\"GO\"><span>GO</span></a>" );	
				
				buffer.append("&nbsp;&nbsp; 到第&nbsp;<input type='text'  name='pageIndex' maxlenght='4' class=\"page-go\" value='"+ paginator.getCurrentPage() + "'onkeyup=\"value=value.replace(/^[^1-9]+|[^\\d]/g,'');\" onblur='checkPage(document.all.pageIndex.value,document.all.pageSize.value,"+paginator.getTotalCount()+")'/>");
				buffer.append("&nbsp;页" );	
				buffer.append("&nbsp;<a href=\"javascript:GotoPage(document.all.pageIndex.value,0);\" class=\"bt_go next\" title=\"确定\"><span>确定</span></a>" );	

				
				buffer.append("<span class='page-skip'><span>共"+paginator.getTotalCount()+"条</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				buffer.append("<span>第"+paginator.getCurrentPage()+"页/共"+totalPage+"页</span></span>");
				buffer.append("</div></div> ");
			} 
			out.write(buffer.toString());
			out.flush();
		} catch (Exception e) {
			logger.error("生成分页html发生错误",e);			
		}

		return EVAL_PAGE;
	}

	public void setForm(String form) {
		this.form = form;
	}
	public void setAction(String action) {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		this.action = request.getContextPath() + action;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String getImagePath(){
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		return request.getContextPath()+"/images/go.gif";
	}
	/**
	 * 获取第一页
	 * @param paginator 分页对象
	 * @return
	 */
	private int getFrist(Paginator paginator){
		int frist=1;
		//当前页面
		int curPage=paginator.getCurrentPage();
		//总页数
		int totalPage=paginator.getTotalPage();
		//获取当前页面的位置
		int s=paginator.getShowPage()/2;
		if(curPage-s<1){
			frist=1;
		}else if((curPage+s)>=totalPage){
			frist=totalPage-paginator.getShowPage()+1;
		}else{
			frist=curPage-s;
		}
		return frist;
	}
}
