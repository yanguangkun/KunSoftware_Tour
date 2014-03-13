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
import com.kunsoftware.bean.OrdersStatusRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Orders;
import com.kunsoftware.entity.OrdersStatus;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.OrdersService;
import com.kunsoftware.service.OrdersStatusService;

@Controller 
@RequestMapping("/manager/ordersstatus")
public class OrdersStatusController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(OrdersStatusController.class);	
	
	@Autowired
	private OrdersStatusService service;
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("/list")
	public String listOrdersStatus(ModelMap model,Integer ordersId) throws KunSoftwareException {
		 
		logger.info("订单状态列表");  
		 
		Orders orders = ordersService.selectByPrimaryKey(ordersId);
		List<OrdersStatus> list = service.getOrdersStatusListAll(ordersId); 
		model.addAttribute("retList", list);
		model.addAttribute("orders", orders);
		return "manager/ordersstatus/ordersstatus-list";
	}
	
	@RequestMapping("/add")
	public String addOrdersStatus(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加订单状态");
		 
		return "manager/ordersstatus/ordersstatus-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addOrdersStatus(OrdersStatusRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存订单状态"); 
		OrdersStatus entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editOrdersStatus(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑订单状态");
		OrdersStatus entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/ordersstatus/ordersstatus-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editOrdersStatus(OrdersStatusRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存订单状态"); 
		 
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delOrdersStatus(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除订单状态");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
