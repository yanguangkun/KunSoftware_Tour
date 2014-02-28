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

import com.kunsoftware.bean.DestinationRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Destination;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.DestinationService;

@Controller 
@RequestMapping("/manager/destination")
public class DestinationController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(DestinationController.class);	
	
	@Autowired
	private DestinationService service;
	
	@RequestMapping("/list")
	public String listDestination(ModelMap model,String keyword,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("目的地列表");  
		 
		List<Destination> list = service.getDestinationListPage(keyword,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/destination/destination-list";
	}
	
	@RequestMapping("/add")
	public String addDestination(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加目的地");
		 
		return "manager/destination/destination-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addDestination(@RequestParam(value = "destinationImageFile", required = false) MultipartFile file,DestinationRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存目的地"); 
		requestBean.setImageFile(file);
		Destination destination = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", destination.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editDestination(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑目的地");
		Destination entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/destination/destination-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editDestination(@RequestParam(value = "destinationImageFile", required = false) MultipartFile file,DestinationRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存目的地"); 
		requestBean.setImageFile(file);
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delDestination(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除目的地");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

	@RequestMapping(value="/enable.json")
	@ResponseBody 
	public JsonBean enableDestination(Integer[] id,String enable) throws KunSoftwareException {
		 
		logger.info("激活停用目的地"); 
		 
		service.updateEnableByIds(id, enable);
		JsonBean jsonBean = new JsonBean(); 
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	 
}
