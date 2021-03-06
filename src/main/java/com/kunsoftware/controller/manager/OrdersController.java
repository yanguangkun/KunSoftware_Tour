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
import com.kunsoftware.bean.OrderViewBean;
import com.kunsoftware.bean.OrdersRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Orders;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.OrdersService;

@Controller 
@RequestMapping("/manager/orders")
public class OrdersController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(OrdersController.class);	
	
	@Autowired
	private OrdersService service;
	
	@RequestMapping("/list")
	public String listOrders(ModelMap model,OrdersRequestBean bean,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("订单列表");  
		 
		List<Orders> list = service.getOrdersListPage(bean,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/orders/orders-list";
	}
	
	@RequestMapping("/add")
	public String addOrders(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加订单");
		 
		return "manager/orders/orders-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addOrders(OrdersRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存订单"); 
		 
		Orders entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editOrders(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑订单");
		Orders entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/orders/orders-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editOrders(OrdersRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存订单");  
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delOrders(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除订单");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	} 

	@RequestMapping("/view")
	public String viewOrders(ModelMap model,Integer ordersId) throws KunSoftwareException {
		 
		logger.info("订单详细");  
		 
		OrderViewBean orderViewBean = service.getOrdersView(ordersId); 
		model.addAttribute("bean", orderViewBean); 
		return "manager/orders/orders-view";
	}
}
