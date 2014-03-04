package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.bean.ProductRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Product;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.ProductService;

@Controller 
@RequestMapping("/manager/product")
public class ProductController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);	
	
	@Autowired
	private ProductService service;
	
	@RequestMapping("/list")
	public String listProduct(ModelMap model,String arriveDestination,String type,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("产品列表"); 
		 
		List<Product> list = service.getProductListPage(arriveDestination,type,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/product/product-list";
	}
	
	@RequestMapping("/add")
	public String addProduct(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加产品");
		 
		return "manager/product/product-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addProduct(@RequestParam(value = "productImageFile", required = false) MultipartFile file,ProductRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存产品");  
		requestBean.setImageFile(file);
		Product entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功");
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editProduct(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑产品");
		Product entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/product/product-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editProduct(@RequestParam(value = "productImageFile", required = false) MultipartFile file,ProductRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存产品"); 
		requestBean.setImageFile(file);
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delProduct(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除产品");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}
 
}
