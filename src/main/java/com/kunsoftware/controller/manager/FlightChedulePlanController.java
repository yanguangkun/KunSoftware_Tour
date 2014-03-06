package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.FlightChedulePlanRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.entity.FlightChedulePlan;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.FlightChedulePlanService;
import com.kunsoftware.service.FlightCheduleService;
import com.kunsoftware.service.ProductResourceService;

@Controller 
@RequestMapping("/manager/flightcheduleplan")
public class FlightChedulePlanController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FlightChedulePlanController.class);	
	
	@Autowired
	private FlightChedulePlanService service;
	
	@Autowired
	private FlightCheduleService flightCheduleService;
	
	@Autowired
	private ProductResourceService productResourceService;
	
	@RequestMapping("/list")
	public String listFlightChedulePlan(ModelMap model,Integer flightCheduleId) throws KunSoftwareException {
		 
		logger.info("日套餐列表");  
		
		FlightChedule flightChedule = flightCheduleService.selectByPrimaryKey(flightCheduleId);
		ProductResource productResource = productResourceService.selectByPrimaryKey(flightChedule.getProductResourceId());
		List<FlightChedulePlan> list = service.getFlightChedulePlanListAll(flightCheduleId); 
		model.addAttribute("retList", list);
		model.addAttribute("productResource", productResource);
		model.addAttribute("flightChedule", flightChedule);
		return "manager/flightcheduleplan/flightcheduleplan-list";
	}
	
	@RequestMapping("/add")
	public String addFlightChedulePlan(ModelMap model,Integer flightCheduleId) throws KunSoftwareException {
		 
		logger.info("添加日套餐");
		FlightChedule flightChedule = flightCheduleService.selectByPrimaryKey(flightCheduleId);
		model.addAttribute("flightChedule", flightChedule);
		return "manager/flightcheduleplan/flightcheduleplan-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addFlightChedulePlan(FlightChedulePlanRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存日套餐"); 
		 
		FlightChedulePlan entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editFlightChedulePlan(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑日套餐");
		FlightChedulePlan entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/flightcheduleplan/flightcheduleplan-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editFlightChedulePlan(FlightChedulePlanRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存日套餐");  
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delFlightChedulePlan(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除日套餐");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
