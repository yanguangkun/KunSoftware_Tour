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
import com.kunsoftware.bean.ProductResourceRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.ProductResourceService;

@Controller 
@RequestMapping("/manager/productresource")
public class ProductResourceController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ProductResourceController.class);	
	
	@Autowired
	private ProductResourceService service;
	
	@RequestMapping("/list")
	public String listProductResource(ModelMap model,ProductResourceRequestBean requestBean,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("产品资源列表");  
		 
		List<ProductResource> list = service.getProductResourceListPage(requestBean,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/productresource/productresource-list";
	}
	
	@RequestMapping("/add")
	public String addProductResource(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加产品资源");
		 
		return "manager/productresource/productresource-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addProductResource(ProductResourceRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存产品资源");  
		ProductResource entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editProductResource(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑产品资源");
		ProductResource entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/productresource/productresource-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editProductResource(ProductResourceRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存产品资源"); 
		 
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delProductResource(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除产品资源");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	} 

}
