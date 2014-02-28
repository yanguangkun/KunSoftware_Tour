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

import com.kunsoftware.bean.AirlineRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Airline;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.AirlineService;

@Controller 
@RequestMapping("/manager/airline")
public class AirlineController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(DestinationController.class);	
	
	@Autowired
	private AirlineService service;
	
	@RequestMapping("/list")
	public String listAirline(ModelMap model) throws KunSoftwareException {
		 
		logger.info("目的地列表");  
		 
		List<Airline> list = service.getAirlineListAll(); 
		model.addAttribute("retList", list);
		return "manager/airline/airline-list";
	}
	
	@RequestMapping("/add")
	public String addAirline(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加目的地");
		 
		return "manager/airline/airline-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addAirline(@RequestParam(value = "airlineImageFile", required = false) MultipartFile file,AirlineRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存目的地"); 
		requestBean.setImageFile(file);
		Airline entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editAirline(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑目的地");
		Airline entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/airline/airline-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editAirline(@RequestParam(value = "airlineImageFile", required = false) MultipartFile file,AirlineRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存目的地"); 
		requestBean.setImageFile(file);
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delAirline(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除目的地");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}
}
