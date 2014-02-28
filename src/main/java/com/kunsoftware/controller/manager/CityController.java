package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.bean.ValueSetRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.directive.ValueSetDirective;
import com.kunsoftware.entity.ValueSet;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.ValueSetService;

@Controller 
@RequestMapping("/manager/city")
public class CityController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MenuController.class);	
	
	@Autowired
	private ValueSetService service;
	
	@RequestMapping("/list")
	public String listCity(ModelMap model,String keyword,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("城市列表");  
		 
		List<ValueSet> list = service.getValueSetListPage("city",keyword,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/city/city-list";
	}
	
	@RequestMapping("/add")
	public String addCity(ModelMap model) {
		 
		logger.info("添加城市");
		 
		return "manager/city/city-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addCity(ValueSetRequestBean valueSetRequestBean) throws KunSoftwareException {
		 
		logger.info("保存城市"); 
		valueSetRequestBean.setCode("city"); 
		valueSetRequestBean.setType(ValueSetDirective.type0);
		
		ValueSet entity = service.insert(valueSetRequestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editCity(ModelMap model,Integer id) {
		 
		logger.info("编辑城市");
		ValueSet entity = service.selectByPrimaryKey(id);
		model.addAttribute("valueSet", entity);  
		 
		return "manager/city/city-edit";
	}
	
	@RequestMapping(value="/edit.json")
	@ResponseBody 
	public JsonBean editCity(ValueSetRequestBean valueSetRequestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存城市"); 
		service.updateByPrimaryKey(valueSetRequestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delCity(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除城市");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
