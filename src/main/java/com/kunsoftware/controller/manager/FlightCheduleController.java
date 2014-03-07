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
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.FlightCheduleService;
import com.kunsoftware.service.ProductResourceService;

@Controller 
@RequestMapping("/manager/flightchedule")
public class FlightCheduleController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FlightCheduleController.class);	
	
	@Autowired
	private FlightCheduleService service;
	
	@Autowired
	private ProductResourceService productResourceService;
	
	@RequestMapping("/list")
	public String listFlightChedule(ModelMap model,String valid,String audit,String status,String startDate,Integer productResourceId,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("产品班期列表");  
		 
		ProductResource productResource = productResourceService.selectByPrimaryKey(productResourceId);
		List<FlightChedule> list = service.getFlightCheduleListPage(productResourceId,valid, audit, status, startDate,pageInfo);
		model.addAttribute("retList", list);
		model.addAttribute("productResource", productResource);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/flightchedule/flightchedule-list";
	} 
	 
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delFlightChedule(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除产品班期");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}


}
