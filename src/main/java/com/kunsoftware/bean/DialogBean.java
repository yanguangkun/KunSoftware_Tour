package com.kunsoftware.bean;

import java.util.List;

import com.kunsoftware.entity.SysModalDialog;

@SuppressWarnings("rawtypes")
public class DialogBean {

	private SysModalDialog entity;
	
	private List<HeadColumnBean> headColumnList; 
	
	private List<String> conditionList;	
	
	private List resultList;
	
	public SysModalDialog getEntity() {
		return entity;
	}

	public void setEntity(SysModalDialog entity) {
		this.entity = entity;
	}

	public List<HeadColumnBean> getHeadColumnList() {
		return headColumnList;
	}

	public void setHeadColumnList(List<HeadColumnBean> headColumnList) {
		this.headColumnList = headColumnList;
	}

	public List<String> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<String> conditionList) {
		this.conditionList = conditionList;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	
	
	
}
