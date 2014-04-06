package com.kunsoftware.controller.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kunsoftware.controller.BaseController;
import com.kunsoftware.exception.KunSoftwareException;

@Controller 
@RequestMapping("/customize")
public class FrontCustomizeController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FrontCustomizeController.class);	
	
	@RequestMapping("/info1")
	public String info1(ModelMap model) throws KunSoftwareException {
		 
		logger.info("产品列表");
		return "front/customize-info1";
	}

}
