package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.ProductIntroduceRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Product;
import com.kunsoftware.entity.ProductIntroduce;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.ProductIntroduceService;
import com.kunsoftware.service.ProductService;

@Controller 
@RequestMapping("/manager/productintroduce")
public class ProductIntroduceController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ProductIntroduceController.class);	
	
	@Autowired
	private ProductIntroduceService service;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/list")
	public String listProductIntroduce(ModelMap model,Integer productId) throws KunSoftwareException {
		 
		logger.info("产品介绍列表");  
		 
		Product product = productService.selectByPrimaryKey(productId);
		List<ProductIntroduce> list = service.getProductIntroduceListAll(productId); 
		model.addAttribute("retList", list);
		model.addAttribute("product", product);
		return "manager/productintroduce/productintroduce-list";
	}
	
	@RequestMapping("/add")
	public String addProductIntroduce(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加产品介绍");
		 
		return "manager/productintroduce/productintroduce-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addProductIntroduce(ProductIntroduceRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存产品介绍"); 
		 
		ProductIntroduce entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editProductIntroduce(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑产品介绍");
		ProductIntroduce entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/productintroduce/productintroduce-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editProductIntroduce(ProductIntroduceRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存产品介绍");  
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delProductIntroduce(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除产品介绍");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
