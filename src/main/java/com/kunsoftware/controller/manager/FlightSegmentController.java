package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.FlightSegmentRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Flight;
import com.kunsoftware.entity.FlightSegment;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.FlightSegmentService;
import com.kunsoftware.service.FlightService;

@Controller 
@RequestMapping("/manager/flightsegment")
public class FlightSegmentController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FlightSegmentController.class);	
	
	@Autowired
	private FlightSegmentService service;
	
	@Autowired
	private FlightService flightService;
	
	@RequestMapping("/list")
	public String listFlightSegment(ModelMap model,Integer flightId) throws KunSoftwareException {
		 
		logger.info("航段列表");  
		 
		Flight flight = flightService.selectByPrimaryKey(flightId);
		List<FlightSegment> list = service.getFlightSegmentListAll(flightId); 
		model.addAttribute("retList", list);
		model.addAttribute("flight", flight);
		return "manager/flightsegment/flightsegment-list";
	}
	
	@RequestMapping("/add")
	public String addFlightSegment(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加航段");
		 
		return "manager/flightsegment/flightsegment-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addFlightSegment(FlightSegmentRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存航段"); 
		 
		FlightSegment entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editFlightSegment(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑航段");
		FlightSegment entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/flightsegment/flightsegment-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editFlightSegment(FlightSegmentRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存航段");  
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delFlightSegment(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除航段");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
