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
import com.kunsoftware.service.ValueSetService;

@Controller 
@RequestMapping("/manager/country")
public class CountryController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MenuController.class);	
	
	@Autowired
	private ValueSetService service;
	
	@RequestMapping("/list")
	public String listCountry(ModelMap model,String keyword,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("国家列表");  
		 
		List<ValueSet> list = service.getValueSetListAll("country"); 
		model.addAttribute("retList", list);  
		return "manager/country/country-list";
	}
	
	@RequestMapping("/add")
	public String addCountry(ModelMap model) {
		 
		logger.info("添加国家");
		 
		return "manager/country/country-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addCountry(ValueSetRequestBean valueSetRequestBean) throws KunSoftwareException {
		 
		logger.info("保存国家"); 
		valueSetRequestBean.setCode("country");
		valueSetRequestBean.setType(ValueSetDirective.type0);
		
		ValueSet entity = service.insert(valueSetRequestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editCountry(ModelMap model,Integer id) {
		 
		logger.info("编辑国家");
		ValueSet entity = service.selectByPrimaryKey(id);
		model.addAttribute("valueSet", entity);  
		 
		return "manager/country/country-edit";
	}
	
	@RequestMapping(value="/edit.json")
	@ResponseBody 
	public JsonBean editCountry(ValueSetRequestBean valueSetRequestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存国家"); 
		service.updateByPrimaryKey(valueSetRequestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delCountry(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除城市");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
