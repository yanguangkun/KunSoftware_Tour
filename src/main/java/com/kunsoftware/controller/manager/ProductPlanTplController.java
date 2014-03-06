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
import com.kunsoftware.bean.ProductPlanTplRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.ProductPlanTpl;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.ProductPlanTplService;
import com.kunsoftware.service.ProductResourceService;

@Controller 
@RequestMapping("/manager/productplantpl")
public class ProductPlanTplController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ProductPlanTplController.class);	
	
	@Autowired
	private ProductPlanTplService service;
	
	@Autowired
	private ProductResourceService productResourceService;
	
	@RequestMapping("/list")
	public String listProductPlanTpl(ModelMap model,Integer productResourceId) throws KunSoftwareException {
		 
		logger.info("套餐模板列表");  
		 
		ProductResource productResource = productResourceService.selectByPrimaryKey(productResourceId);
		List<ProductPlanTpl> list = service.getProductPlanTplListAll(productResourceId); 
		model.addAttribute("retList", list);
		model.addAttribute("productResource", productResource);
		return "manager/productplantpl/productplantpl-list";
	}
	
	@RequestMapping("/add")
	public String addProductPlanTpl(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加套餐模板");
		 
		return "manager/productplantpl/productplantpl-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addProductPlanTpl(ProductPlanTplRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存套餐模板"); 
		 
		ProductPlanTpl entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editProductPlanTpl(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑套餐模板");
		ProductPlanTpl entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/productplantpl/productplantpl-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editProductPlanTpl(ProductPlanTplRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存套餐模板");  
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delProductPlanTpl(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除套餐模板");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
