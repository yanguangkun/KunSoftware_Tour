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
import com.kunsoftware.bean.OrdersAttachmentRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Orders;
import com.kunsoftware.entity.OrdersAttachment;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.OrdersAttachmentService;
import com.kunsoftware.service.OrdersService;

@Controller 
@RequestMapping("/manager/ordersattachment")
public class OrdersAttachmentController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(OrdersAttachmentController.class);	
	
	@Autowired
	private OrdersAttachmentService service;
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("/list")
	public String listOrdersAttachment(ModelMap model,Integer ordersId) throws KunSoftwareException {
		 
		logger.info("订单附件列表");  
		 
		Orders orders = ordersService.selectByPrimaryKey(ordersId);
		List<OrdersAttachment> list = service.getOrdersAttachmentListAll(ordersId); 
		model.addAttribute("retList", list);
		model.addAttribute("orders", orders);
		return "manager/ordersattachment/ordersattachment-list";
	}
	
	@RequestMapping("/add")
	public String addOrdersAttachment(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加订单附件");
		 
		return "manager/ordersattachment/ordersattachment-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addOrdersAttachment(@RequestParam(value = "ordersattachmentFile", required = false) MultipartFile file,OrdersAttachmentRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存订单附件"); 
		requestBean.setFile(file);
		OrdersAttachment entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editOrdersAttachment(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑订单附件");
		OrdersAttachment entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/ordersattachment/ordersattachment-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editOrdersAttachment(@RequestParam(value = "ordersattachmentImageFile", required = false) MultipartFile file,OrdersAttachmentRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存订单附件"); 
		requestBean.setFile(file);
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delOrdersAttachment(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除订单附件");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

	
}
