package com.kunsoftware.page;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.kunsoftware.util.WebUtil;

public class PageUtil {

	public static void pageInfo(ModelMap model,PageInfo pageInfo) {
		
		model.addAttribute("pageInfo",pageToString(pageInfo));
		model.addAttribute("pageParam",getPageParam(pageInfo));
	}
	
	public static void dialogPageInfo(ModelMap model,PageInfo pageInfo) {
		
		model.addAttribute("pageInfo",dialogPageToString(pageInfo));
		model.addAttribute("pageParam",getDialogPageParam(pageInfo));
	}
	
	public static void frontPageInfo(Map model,PageInfo pageInfo) {
		
		model.put("pageInfo",frontPageToString(pageInfo));
		model.put("pageParam",getPageParam(pageInfo));
	}
	
	public static void frontPageInfo(ModelMap model,PageInfo pageInfo) {
		
		model.addAttribute("pageInfo",frontPageToString(pageInfo));
		model.addAttribute("pageParam",getPageParam(pageInfo));
	}
	
	public static void frontPageInfo2(ModelMap model,PageInfo pageInfo) {
		
		model.addAttribute("pageInfo",frontPageToString2(pageInfo));
		model.addAttribute("pageParam",getPageParam(pageInfo));
	}
	
	public static String dialogPageToString(PageInfo pageInfo) {
		
		if(pageInfo == null || pageInfo.getTotalPages() <= 1) return "&nbsp;";
		
		String retStr = "<table border=\"0\" cellpadding=\"0\" style=\"padding-left:3px;\" cellspacing=\"0\">\n";
		retStr +="  <tr>\n";
		retStr +="    <td width=\"12\">\n";

		if(pageInfo.getPageNo() == 1) {
			retStr +="<img src=\""+WebUtil.getContextPath()+"/images/pagination-arrow-first-disabled.gif\" width=\"12\" height=\"12\"/></td>\n";
			retStr +="    <td width=\"10\"></td>\n";
			retStr +="    <td width=\"8\">\n";
			retStr +="<img src=\""+WebUtil.getContextPath()+"/images/pagination-arrow-prev-disabled.gif\" width=\"8\" height=\"12\" /></td>\n";
		} else {
			retStr +="<img class=\"dialogPage\" page=\"1\" type=\"1\" src=\""+WebUtil.getContextPath()+"/images/pagination-arrow-first.gif\" width=\"12\" height=\"12\"/></td>\n";
			retStr +="    <td width=\"10\"></td>\n";
			retStr +="    <td width=\"8\">\n";
			retStr +="<img class=\"dialogPage\" page=\""+(pageInfo.getPageNo() -1)+"\" type=\"2\" src=\""+WebUtil.getContextPath()+"/images/pagination-arrow-prev.gif\" width=\"8\" height=\"12\" /></td>\n";
		}

		retStr +="    <td width=\"10\"></td>\n"; 
		retStr +="    <td class=\"page_span\">共</td>\n";
		retStr +="    <td width=\"10\">&nbsp;</td>\n";
		retStr +="    <td class=\"page_span\"><a href=\"###\" class=\"dialogPage\"  page=\""+pageInfo.getTotalPages()+"\">"+pageInfo.getTotalPages()+"</a></td>\n";
		retStr +="    <td width=\"10\">&nbsp;</td>\n";
		retStr +="    <td align=\"center\">\n";

		if(pageInfo.getPageNo() == pageInfo.getTotalPages()) 
			retStr +="<img src=\""+WebUtil.getContextPath()+"/images/pagination-arrow-next-disabled.gif\" width=\"8\" height=\"12\" /></td>\n";
		else {
			retStr +="<img class=\"dialogPage\" title=\"下一页\"  page=\""+(pageInfo.getPageNo() + 1)+"\" type=\"3\" src=\""+WebUtil.getContextPath()+"/images/pagination-arrow-next.gif\" width=\"8\" height=\"12\" /></td>\n";
		}

		retStr +="  </tr>\n";
		retStr +="</table>\n";
		
		return retStr;
	}
	
	/** 分页参数 */
	@SuppressWarnings("rawtypes")
	public static String getDialogPageParam(PageInfo pageInfo) {
		
		if(pageInfo == null) return "";
		
		HttpServletRequest request = WebUtil.getRequest();
		Enumeration paramNames = request.getParameterNames();
		String retStr = "<form name=\"dialogPageFrm\" id=\"dialogPageFrm\" method=\"post\" action=\""+request.getContextPath()+"/manager/dialog/list\">";
		retStr += "<input type=\"hidden\" id=\"pageNo\" name=\"pageNo\" value=\"" + pageInfo.getPageNo() + "\">";
		retStr += "<input type=\"hidden\" id=\"pagesize\" name=\"pagesize\" value=\"" + pageInfo.getPageSize() + "\">";
		
		while(paramNames.hasMoreElements()) {
			String paramName = (String)paramNames.nextElement();
			if(!("pageNo".equals(paramName)||"pagesize".equals(paramName))) {
				String[] paramValues = request.getParameterValues(paramName);
				for(int i = 0; i < paramValues.length; i++) {
					String paramValue = paramValues[i];
					retStr += "<input type=\"hidden\" name=\"" + paramName + "\" id=\"" + paramName + "\" value=\"" + paramValue + "\">";
				}
			}
		}
		
		retStr += "</form>";
		return retStr;
	}
	
	public static String pageToString(PageInfo pageInfo) {
		
		if(pageInfo == null || pageInfo.getTotalPages() <= 1) return "&nbsp;";
		
		String retStr = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n";
		retStr +="  <tr>\n";
		retStr +="    <td width=\"12\">\n";

		if(pageInfo.getPageNo() == 1) {
			retStr +="<img src=\""+WebUtil.getContextPath()+"/images/pagination-arrow-first-disabled.gif\" width=\"12\" height=\"12\"/></td>\n";
			retStr +="    <td width=\"10\"></td>\n";
			retStr +="    <td width=\"8\">\n";
			retStr +="<img src=\""+WebUtil.getContextPath()+"/images/pagination-arrow-prev-disabled.gif\" width=\"8\" height=\"12\" /></td>\n";
		} else {
			retStr +="<img class=\"defaultPage\" page=\"1\" type=\"1\" src=\""+WebUtil.getContextPath()+"/images/pagination-arrow-first.gif\" width=\"12\" height=\"12\"/></td>\n";
			retStr +="    <td width=\"10\"></td>\n";
			retStr +="    <td width=\"8\">\n";
			retStr +="<img class=\"defaultPage\" page=\""+(pageInfo.getPageNo() -1)+"\" type=\"2\" src=\""+WebUtil.getContextPath()+"/images/pagination-arrow-prev.gif\" width=\"8\" height=\"12\" /></td>\n";
		}

		retStr +="    <td width=\"10\"></td>\n";
		retStr +="    <td><input type=\"text\" class=\"form-control input-sm\" style=\"width:50px\" name=\"cli_page\" value=\""+pageInfo.getPageNo()+"\" id=\"cli_page\"/></td>\n";
		retStr +="    <td width=\"10\"></td>\n";
		retStr +="    <td class=\"page_span\">共</td>\n";
		retStr +="    <td width=\"10\">&nbsp;</td>\n";
		retStr +="    <td><a href=\"###\" class=\"defaultPage\" page=\""+pageInfo.getTotalPages()+"\">"+pageInfo.getTotalPages()+"</a></td>\n";
		retStr +="    <td width=\"10\">&nbsp;</td>\n";
		retStr +="    <td align=\"center\">\n";

		if(pageInfo.getPageNo() == pageInfo.getTotalPages()) 
			retStr +="<img src=\""+WebUtil.getContextPath()+"/images/pagination-arrow-next-disabled.gif\" width=\"8\" height=\"12\" /></td>\n";
		else {
			retStr +="<img title=\"下一页\" class=\"defaultPage\" page=\""+(pageInfo.getPageNo() + 1)+"\" src=\""+WebUtil.getContextPath()+"/images/pagination-arrow-next.gif\" width=\"8\" height=\"12\" /></td>\n";
		}

		retStr +="  </tr>\n";
		retStr +="</table>\n";
		
		return retStr;
	}
	
	public static String frontPageToString(PageInfo pageInfo) {
		
		if(pageInfo == null || pageInfo.getTotalPages() <= 1) return "&nbsp;"; 
		String retStr = "";
		retStr +="<ul>";
		retStr +="<li class=\"page\" value=\""+pageInfo.getPreviousPageNo()+"\">《</li>";
		
		int currentPage = pageInfo.getPageNo();
		int startPage = currentPage - 1;
		int endPage = currentPage + 1;
		
		if(startPage <= 0) {
			startPage = 1;
			endPage += 1;
		}
		if(endPage >= pageInfo.getTotalPages()) {
			endPage = pageInfo.getTotalPages();
		}
		 
		if(endPage - startPage == 1) {
			startPage -=1;
			if(startPage <= 0) {
				startPage = 1; 
			}
		}
		
		for(int i = startPage;i <=endPage;i++) {
			if(i == currentPage) {
				retStr += "<li class=\"page active\" value=\""+i+"\">"+i+"</li>";
			} else {
				retStr += "<li class=\"page\" value=\""+i+"\">"+i+"</li>";				
			}
			
		}
		if(pageInfo.getTotalPages() > endPage) {
			retStr += "<li class=\"end\">.</li>";	
		}
		
		retStr += "<li class=\"page\" value=\""+pageInfo.getNextPageNo()+"\">》</li>";
		return retStr;
	}
	
	public static String frontPageToString2(PageInfo pageInfo) {
		
		if(pageInfo == null || pageInfo.getTotalPages() <= 1) return "&nbsp;"; 
		String retStr = "";
		retStr +="<ul>";
		retStr +="<li class=\"page\" value=\""+pageInfo.getPreviousPageNo()+"\">《</li>";
		
		int currentPage = pageInfo.getPageNo();
		int startPage = currentPage - 3;
		int endPage = currentPage + 3;
		
		if(startPage <= 0) startPage = 1;
		if(endPage >= pageInfo.getTotalPages()) endPage = pageInfo.getTotalPages();
		 
		for(int i = startPage;i <=endPage;i++) {
			if(i == currentPage) {
				retStr += "<li class=\"page active\" value=\""+i+"\">"+i+"</li>";
			} else {
				retStr += "<li class=\"page\" value=\""+i+"\">"+i+"</li>";				
			}
			
		}
		
		retStr += "<li class=\"page\" value=\""+pageInfo.getNextPageNo()+"\">》</li>";
		return retStr;
	}
	
	/** 分页参数 */
	@SuppressWarnings("rawtypes")
	public static String getPageParam(PageInfo pageInfo) {
		
		if(pageInfo == null) return "";
		
		HttpServletRequest request = WebUtil.getRequest();
		Enumeration paramNames = request.getParameterNames();
		String retStr = "<form name=\"pageFrm\" method=\"post\" action=\""+request.getRequestURI()+"\">";
		retStr += "<input type=\"hidden\" name=\"pageNo\" value=\"" + pageInfo.getPageNo() + "\">";
		retStr += "<input type=\"hidden\" name=\"pagesize\" value=\"" + pageInfo.getPageSize() + "\">";
		
		while(paramNames.hasMoreElements()) {
			String paramName = (String)paramNames.nextElement();
			if(!("pageNo".equals(paramName)||"pagesize".equals(paramName))) {
				String[] paramValues = request.getParameterValues(paramName);
				for(int i = 0; i < paramValues.length; i++) {
					String paramValue = paramValues[i];
					retStr += "<input type=\"hidden\" name=\"" + paramName + "\" value=\"" + paramValue + "\">";
				}
			}
		}
		
		retStr += "</form>";
		return retStr;
	}
}
