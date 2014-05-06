package com.kunsoftware.controller.front;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.bean.RequirementRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Customize;
import com.kunsoftware.entity.Destination;
import com.kunsoftware.entity.Requirement;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.CustomizeService;
import com.kunsoftware.service.DestinationService;
import com.kunsoftware.service.RequirementService;
import com.kunsoftware.service.ValueSetService;

@Controller 
@RequestMapping("/customize")
public class FrontCustomizeController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FrontCustomizeController.class);	
	
	@Autowired
	private ValueSetService valueSetService;
	
	@Autowired
	private CustomizeService customizeService;
	
	@Autowired
	private RequirementService service;
	
	@Autowired
	private DestinationService destinationService;
	
	@RequestMapping("/info1")
	public String info1(ModelMap model,String chufa,Integer destination) throws KunSoftwareException {
		 
		if(StringUtils.isEmpty(chufa)) {
			chufa = "上海";
		}
		
		Destination destinationEntity = destinationService.selectByPrimaryKey(destination);
		if(destinationEntity == null) { 
			destinationEntity = new Destination();
			destinationEntity.setId(9);
			destinationEntity.setName("巴厘岛");
		}
		
		logger.info("私家定制");
		model.addAttribute("chufa", chufa);
		model.addAttribute("destination", destinationEntity);
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		return "front/customize-info1";
	}
	
	@RequestMapping("/info1-save.json")
	@ResponseBody 
	public JsonBean info1Save(ModelMap model,RequirementRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存需求单");  
		Requirement entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}

	@RequestMapping("/info2")
	public String info2(ModelMap model,String destination,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("私家定制");
		pageInfo.setPageSize(6);
		List<Customize> list = customizeService.getFrontCustomizeListPage(destination,pageInfo);  
		model.addAttribute("retList", list);
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		
		PageUtil.frontPageInfo(model, pageInfo);
		return "front/customize-info2";
	}
}
