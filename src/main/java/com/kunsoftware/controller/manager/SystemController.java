package com.kunsoftware.controller.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.ValueSetService;

@Controller 
@RequestMapping("/manager/system")
public class SystemController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(SystemController.class);	
	
	@Autowired
	private ValueSetService service;

	@RequestMapping("/gcolor")
	public String editGcolor(ModelMap model) throws KunSoftwareException {
		 
		logger.info("编辑轮播颜色");
		
		model.addAttribute("color1", service.selectValueSetByCode("index_color1")); 
		model.addAttribute("color2", service.selectValueSetByCode("index_color2")); 
		model.addAttribute("color3", service.selectValueSetByCode("index_color3")); 
		model.addAttribute("color4", service.selectValueSetByCode("index_color4")); 
		 
		return "manager/system/system-gcolor";
	}
	
	@RequestMapping(value="/gcolor.json") 
	@ResponseBody 
	public JsonBean editGcolor(String color1,String color2,String color3,String color4) throws KunSoftwareException {
		 
		logger.info("编辑保存轮播颜色"); 
		
		service.saveGcolor(color1, color2, color3, color4);	
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping("/customize")
	public String editCustomize(ModelMap model) throws KunSoftwareException {
		 
		logger.info("编辑私家定制设置");
		
		model.addAttribute("num", service.selectValueSetByCode("customize_num")); 
		model.addAttribute("image", service.selectValueSetByCode("customize_image")); 
		 
		return "manager/system/system-customize";
	}
	
	@RequestMapping(value="/customize.json") 
	@ResponseBody 
	public JsonBean editCustomize(String num,@RequestParam(value = "image", required = false) MultipartFile image) throws KunSoftwareException {
		 
		logger.info("编辑保存私家定制设置"); 
		
		service.saveCustomize(num, image);	
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	

	@RequestMapping("/hideprice")
	public String editHidePrice(ModelMap model) throws KunSoftwareException {
		 
		logger.info("编辑隐藏价格");
		
		model.addAttribute("hidePrice", service.selectValueSetByCode("hide_price"));
		 
		return "manager/system/system-hideprice";
	}
	
	@RequestMapping(value="/hideprice.json") 
	@ResponseBody 
	public JsonBean editHidePrice(String hidePrice) throws KunSoftwareException {
		 
		logger.info("编辑保存隐藏价格"); 
		
		service.saveHidePrice(hidePrice);	
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping("/giftad")
	public String editGiftad(ModelMap model) throws KunSoftwareException {
		 
		logger.info("编辑赠品广告");
		
		model.addAttribute("link", service.selectValueSetByCode("giftad_link")); 
		model.addAttribute("image", service.selectValueSetByCode("giftad_image")); 
		 
		return "manager/system/system-giftad";
	}
	
	@RequestMapping(value="/giftad.json") 
	@ResponseBody 
	public JsonBean editGiftad(String link,@RequestParam(value = "image", required = false) MultipartFile image) throws KunSoftwareException {
		 
		logger.info("编辑保存赠品广告"); 
		
		service.saveGiftad(link, image);	
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
}
