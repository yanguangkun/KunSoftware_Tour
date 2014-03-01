package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.FlightRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Flight;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.FlightService;

@Controller 
@RequestMapping("/manager/flight")
public class FlightController extends BaseController {

private static Logger logger = LoggerFactory.getLogger(DestinationController.class);	
	
	@Autowired
	private FlightService service;
	
	@RequestMapping("/list")
	public String listFlight(ModelMap model,String enable,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("航班列表");  
		 
		List<Flight> list = service.getFlightListPage(enable,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/flight/flight-list";
	}
	
	@RequestMapping("/add")
	public String addFlight(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加航班");
		 
		return "manager/flight/flight-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addFlight(FlightRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存航班");  
		Flight entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editFlight(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑航班");
		Flight entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/flight/flight-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editFlight(FlightRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存航班"); 
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delFlight(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除航班");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}
 
	 
}
