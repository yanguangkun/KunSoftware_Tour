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
import com.kunsoftware.bean.OrdersCashRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Orders;
import com.kunsoftware.entity.OrdersCash;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.OrdersCashService;
import com.kunsoftware.service.OrdersService;

@Controller 
@RequestMapping("/manager/orderscash")
public class OrdersCashController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(OrdersCashController.class);	
	
	@Autowired
	private OrdersCashService service;
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("/list")
	public String listOrdersCash(ModelMap model,Integer ordersId) throws KunSoftwareException {
		 
		logger.info("收款列表");  
		 
		Orders orders = ordersService.selectByPrimaryKey(ordersId);
		List<OrdersCash> list = service.getOrdersCashListAll(ordersId); 
		model.addAttribute("retList", list);
		model.addAttribute("orders", orders);
		return "manager/orderscash/orderscash-list";
	}
	
	@RequestMapping("/add")
	public String addOrdersCash(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加收款");
		 
		return "manager/orderscash/orderscash-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addOrdersCash(OrdersCashRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存收款"); 

		OrdersCash entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editOrdersCash(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑收款");
		OrdersCash entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/orderscash/orderscash-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editOrdersCash(OrdersCashRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存收款"); 
		 
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delOrdersCash(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除收款");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
