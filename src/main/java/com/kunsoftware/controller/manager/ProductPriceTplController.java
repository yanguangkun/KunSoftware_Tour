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
import com.kunsoftware.bean.ProductPriceTplRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.ProductPriceTpl;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.ProductPriceTplService;
import com.kunsoftware.service.ProductResourceService;

@Controller 
@RequestMapping("/manager/productpricetpl")
public class ProductPriceTplController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ProductPriceTplController.class);	
	
	@Autowired
	private ProductPriceTplService service;
	
	@Autowired
	private ProductResourceService productResourceService;
	
	@RequestMapping("/list")
	public String listProductPriceTpl(ModelMap model,Integer productResourceId) throws KunSoftwareException {
		 
		logger.info("价格模板列表");  
		 
		ProductResource productResource = productResourceService.selectByPrimaryKey(productResourceId);
		List<ProductPriceTpl> list = service.getProductPriceTplListAll(productResourceId); 
		model.addAttribute("retList", list);
		model.addAttribute("productResource", productResource);
		return "manager/productpricetpl/productpricetpl-list";
	}
	
	@RequestMapping("/add")
	public String addProductPriceTpl(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加价格模板");
		 
		return "manager/productpricetpl/productpricetpl-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addProductPriceTpl(ProductPriceTplRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存价格模板"); 
		 
		ProductPriceTpl entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editProductPriceTpl(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑价格模板");
		ProductPriceTpl entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/productpricetpl/productpricetpl-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editProductPriceTpl(ProductPriceTplRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存价格模板");  
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delProductPriceTpl(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除价格模板");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
