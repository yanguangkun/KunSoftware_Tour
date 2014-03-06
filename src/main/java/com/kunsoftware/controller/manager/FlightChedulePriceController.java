package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.FlightChedulePriceRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.entity.FlightChedulePrice;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.FlightChedulePriceService;
import com.kunsoftware.service.FlightCheduleService;
import com.kunsoftware.service.ProductResourceService;

@Controller 
@RequestMapping("/manager/flightcheduleprice")
public class FlightChedulePriceController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FlightChedulePriceController.class);	
	
	@Autowired
	private FlightChedulePriceService service;
	
	@Autowired
	private FlightCheduleService flightCheduleService;
	
	@Autowired
	private ProductResourceService productResourceService;
	
	@RequestMapping("/list")
	public String listFlightChedulePrice(ModelMap model,Integer flightCheduleId) throws KunSoftwareException {
		 
		logger.info("日价格列表");  
		
		FlightChedule flightChedule = flightCheduleService.selectByPrimaryKey(flightCheduleId);
		ProductResource productResource = productResourceService.selectByPrimaryKey(flightChedule.getProductResourceId());
		List<FlightChedulePrice> list = service.getFlightChedulePriceListAll(flightCheduleId); 
		model.addAttribute("retList", list);
		model.addAttribute("productResource", productResource);
		model.addAttribute("flightChedule", flightChedule);
		return "manager/flightcheduleprice/flightcheduleprice-list";
	}
	
	@RequestMapping("/add")
	public String addFlightChedulePrice(ModelMap model,Integer flightCheduleId) throws KunSoftwareException {
		 
		logger.info("添加日价格");
		FlightChedule flightChedule = flightCheduleService.selectByPrimaryKey(flightCheduleId);
		model.addAttribute("flightChedule", flightChedule);
		return "manager/flightcheduleprice/flightcheduleprice-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addFlightChedulePrice(FlightChedulePriceRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存日价格"); 
		 
		FlightChedulePrice entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editFlightChedulePrice(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑日价格");
		FlightChedulePrice entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/flightcheduleprice/flightcheduleprice-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editFlightChedulePrice(FlightChedulePriceRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存日价格");  
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delFlightChedulePrice(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除日价格");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
