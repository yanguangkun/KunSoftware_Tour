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
import com.kunsoftware.bean.OrdersTravelRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Orders;
import com.kunsoftware.entity.OrdersTravelList;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.OrdersService;
import com.kunsoftware.service.OrdersTravelService;

@Controller 
@RequestMapping("/manager/orderstravel")
public class OrdersTravelController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(OrdersTravelController.class);	
	
	@Autowired
	private OrdersTravelService service;
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("/list")
	public String listOrdersTravel(ModelMap model,Integer ordersId) throws KunSoftwareException {
		 
		logger.info("客人名单列表");  
		 
		Orders orders = ordersService.selectByPrimaryKey(ordersId);
		List<OrdersTravelList> list = service.getOrdersTravelListAll(ordersId); 
		model.addAttribute("retList", list);
		model.addAttribute("orders", orders);
		return "manager/orderstravel/orderstravel-list";
	}
	
	@RequestMapping("/add")
	public String addOrdersTravel(ModelMap model,Integer ordersId) throws KunSoftwareException {
		 
		logger.info("添加客人名单");
		Orders orders = ordersService.selectByPrimaryKey(ordersId);
		model.addAttribute("orders", orders);
		 
		return "manager/orderstravel/orderstravel-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addOrdersTravel(@RequestParam(value = "orderstravelFile", required = false) MultipartFile file,OrdersTravelRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存客人名单"); 
		requestBean.setFile(file);
		OrdersTravelList entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editOrdersTravel(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑客人名单");
		
		OrdersTravelList entity = service.selectByPrimaryKey(id);
		Orders orders = ordersService.selectByPrimaryKey(entity.getOrdersId());
		model.addAttribute("entity", entity); 
		model.addAttribute("orders", orders);
		 
		return "manager/orderstravel/orderstravel-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editOrdersTravel(@RequestParam(value = "orderstravelImageFile", required = false) MultipartFile file,OrdersTravelRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存客人名单"); 
		requestBean.setFile(file);
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delOrdersTravel(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除客人名单");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
