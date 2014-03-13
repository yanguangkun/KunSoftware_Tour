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
import com.kunsoftware.bean.OrdersDetailRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Orders;
import com.kunsoftware.entity.OrdersDetail;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.OrdersService;
import com.kunsoftware.service.OrdersDetailService;

@Controller 
@RequestMapping("/manager/ordersdetail")
public class OrdersDetailController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(OrdersDetailController.class);	
	
	@Autowired
	private OrdersDetailService service;
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("/list")
	public String listOrdersDetail(ModelMap model,Integer ordersId) throws KunSoftwareException {
		 
		logger.info("订单明细列表");  
		 
		Orders orders = ordersService.selectByPrimaryKey(ordersId);
		List<OrdersDetail> list = service.getOrdersDetailListAll(ordersId); 
		model.addAttribute("retList", list);
		model.addAttribute("orders", orders);
		return "manager/ordersdetail/ordersdetail-list";
	}
	
	@RequestMapping("/add")
	public String addOrdersDetail(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加订单明细");
		 
		return "manager/ordersdetail/ordersdetail-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addOrdersDetail(OrdersDetailRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存订单明细"); 
		OrdersDetail entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editOrdersDetail(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑订单明细");
		OrdersDetail entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/ordersdetail/ordersdetail-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editOrdersDetail(OrdersDetailRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存订单明细"); 
		 
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delOrdersDetail(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除订单明细");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
