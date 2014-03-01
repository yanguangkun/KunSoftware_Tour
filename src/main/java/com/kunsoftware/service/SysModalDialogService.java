package com.kunsoftware.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import bsh.Interpreter;

import com.kunsoftware.bean.DialogBean;
import com.kunsoftware.bean.DialogResultBean;
import com.kunsoftware.bean.HeadColumnBean;
import com.kunsoftware.directive.ValueSetDirective;
import com.kunsoftware.entity.SysModalDialog;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.SysModalDialogMapper;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PagePlugin;
import com.kunsoftware.util.WebUtil;

@Service
public class SysModalDialogService {

private static Logger logger = LoggerFactory.getLogger(SysModalDialogService.class);	
	
	@Autowired
	private SysModalDialogMapper mapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public SysModalDialog selectByCode(String code) {
		 
		logger.info("query");
		return mapper.selectByCode(code);
	}
	
	public DialogBean getDialogInfo(String code,PageInfo pageInfo) throws KunSoftwareException {
		
		SysModalDialog entity = mapper.selectByCode(code);
		
		List<HeadColumnBean> headColumnList = headColumn(entity.getShowColumn(),entity.getShowColumnWidth()); 
		
		List<String> conditionList = getConditionList(entity.getDialogCondition());
		
		String dialogSql = parseSql(entity.getDialogSql()); 
		List resultList = getPageList(dialogSql,pageInfo); 
	 
		resultList = changeResultList(resultList,getColumnField(entity.getShowColumn()),entity.getType());
		
		DialogBean dialogBean = new DialogBean();
		dialogBean.setEntity(entity);
		dialogBean.setHeadColumnList(headColumnList);
		dialogBean.setConditionList(conditionList);
		dialogBean.setResultList(resultList);
		
		return dialogBean;
	}
	
	public List getColumnField(String showColumn) {
		
		List<String> retList = new ArrayList();
		
		String[] showColumns = StringUtils.split(showColumn,",");
		for(String s:showColumns) {
			retList.add(StringUtils.split(s, ":")[0]);
		}
		return retList;
	}
	
	public List getPageList(String sql,PageInfo pageInfo) {
		 
        String countSql = "select count(0) from (" + sql + ") t"; 
        Number number = jdbcTemplate.queryForObject(countSql, Integer.class); 
        int count = (number != null ? number.intValue() : 0);
        pageInfo.setTotalRecords(count);
        String pageSql = PagePlugin.generatePageSql(sql, pageInfo);
        
        return jdbcTemplate.queryForList(pageSql);
	}
	
	public List<String> getConditionList(String condition) {
	 
		List<String> conditionList = new ArrayList<String>();
		if(StringUtils.isEmpty(condition)) return conditionList;
		
		String[] conditions = StringUtils.split(condition);
		String sResult = "";
		String name = "";
		String nameDesc = "";
		String value = "";
		String[] names = null;
		for(String s:conditions) {
			if(s.startsWith("s:")) {
				names = StringUtils.split(s,":");
				name = names[1];
				nameDesc = names[2];
				value = WebUtil.getRequest().getParameter(name);
				if(StringUtils.isEmpty(value)) value = "";
				sResult = nameDesc + " <select name='"+name+"' id='"+name+"' class='form-control input-sm'>";
				sResult += "<option value=''>-请选择-</option>";
				sResult += ValueSetDirective.getResult(name, value, name, name);
				sResult += "</select>"; 
				conditionList.add(sResult);
			} else {
				names = StringUtils.split(s,":");
				name = names[0];
				nameDesc = names[1];
				value = WebUtil.getRequest().getParameter(name);
				if(StringUtils.isEmpty(value)) value = "";
				sResult = "<input type='text' class='form-control input-sm' name='"+name+"' id='"+name+"'  value='"+value+"' placeholder='"+nameDesc+"'>"; 
				conditionList.add(sResult);
			}
		}
		
		return conditionList;
	}
	
	public List<HeadColumnBean> headColumn(String showColumn,String showColumnWidth) {
		
		List<HeadColumnBean> list = new ArrayList<HeadColumnBean>();
		HeadColumnBean headColumn = null;
		 
		String[] dialogShowColumns = StringUtils.split(showColumn,",");
		String[] dialogShowColumnWidths = StringUtils.split(showColumnWidth,",");
		
		
		for(int i = 0;i < dialogShowColumns.length;i++) {
			headColumn = new HeadColumnBean();
			headColumn.setName(StringUtils.split(dialogShowColumns[i], ":")[1]);
			if(dialogShowColumnWidths != null && i < dialogShowColumnWidths.length - 1) {
				headColumn.setWidth(dialogShowColumnWidths[i]);
			}
			list.add(headColumn);
		}
		
		return list;
	}
	
	public String parseSql(String dialogSql) throws KunSoftwareException {  
		 
		try{
			Interpreter bsh = new Interpreter(); 
			Map map = WebUtil.getRequest().getParameterMap();
			
			HttpServletRequest request = WebUtil.getRequest();
			Enumeration paramNames = request.getParameterNames();
			
			while(paramNames.hasMoreElements()) {
				String paramName = (String)paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				bsh.set("$" + paramName ,paramValue);  
			} 
			 
			String common = "  boolean isNotEmpty(String str) {" +
							"  if(str == null || str == \"\") {"+
							"      return false;" +
							"  }" + 
							"      return true;" + 
							"  } ";
			common += dialogSql;
			Object obj = bsh.eval(common); 
			if(obj == null) { 
				throw new KunSoftwareException("dialogSql=" + dialogSql + " 返回sql为空,请检查sql配置是否正确.");  
			} else {
				String result = obj.toString();
				return result;
			}
		} catch(Exception e) {
			throw new KunSoftwareException(e);
		}
		 
	}	
	
	public List changeResultList(List list,List showColumns,String dialogType) {
		
		List retList = new ArrayList();
		
		
		Map newMap = null; 
		 
		DialogResultBean dialogResultBean = null;
		for(int i = 0; i < list.size();i++) {
			StringBuilder str = new StringBuilder();
			str.append("<div class='resultDialog' style='display:none'>[");
			dialogResultBean = new DialogResultBean();
			dialogResultBean.setType(dialogType);
			dialogResultBean.setValue("");
			retList.add(dialogResultBean);
			
			String v = null;
			String n = null;
			
			//spring3
			newMap = (Map)list.get(i);
			Iterator it =  newMap.entrySet().iterator();
			int j=0;
			while(it.hasNext()){
				 Map.Entry entry =  (Map.Entry) it.next();
				 v = entry.getValue()== null? "":entry.getValue().toString();
				 n = entry.getKey()+"";
				 if(showColumns.contains(n)) {
					 dialogResultBean = new DialogResultBean();
					 dialogResultBean.setValue(v);
					 dialogResultBean.setType("");
					 retList.add(dialogResultBean);		
				 }
				 if(j != 0)
					 str.append(",");				
				 str.append("{\"text\":\""+ StringUtils.replace(n, ".", "_") +"\",\"value\":\"" + v + "\"}");
				 j++;
			} 
			str.append("]</div>");
			dialogResultBean = new DialogResultBean();
			dialogResultBean.setValue(str.toString());
			dialogResultBean.setType("padding");
			retList.add(dialogResultBean); 
		}
		return retList;
	}
	 
}
