package com.kunsoftware.controller.front;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Customize;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.CustomizeService;
import com.kunsoftware.service.ValueSetService;

@Controller 
@RequestMapping("/customize")
public class FrontCustomizeController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FrontCustomizeController.class);	
	
	@Autowired
	private ValueSetService valueSetService;
	
	@Autowired
	private CustomizeService customizeService;
	
	@RequestMapping("/info1")
	public String info1(ModelMap model) throws KunSoftwareException {
		 
		logger.info("私家定制");
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		return "front/customize-info1";
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
