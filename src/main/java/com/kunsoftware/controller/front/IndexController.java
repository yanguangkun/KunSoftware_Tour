package com.kunsoftware.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kunsoftware.entity.Gallery;
import com.kunsoftware.service.GalleryService;
import com.kunsoftware.service.ValueSetService;

@Controller
public class IndexController {

	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private ValueSetService valueSetService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		
		List<Gallery> galleryList = galleryService.getGalleryListAll("1");
		model.addAttribute("galleryList", galleryList); 
		model.addAttribute("color1", valueSetService.selectValueSetByCode("index_color1")); 
		model.addAttribute("color2", valueSetService.selectValueSetByCode("index_color2")); 
		model.addAttribute("color3", valueSetService.selectValueSetByCode("index_color3")); 
		model.addAttribute("color4", valueSetService.selectValueSetByCode("index_color4"));
		model.addAttribute("customizeNum", valueSetService.selectValueSetByCode("customize_num")); 
		model.addAttribute("customizeImage", valueSetService.selectValueSetByCode("customize_image")); 
		model.addAttribute("giftadLink", valueSetService.selectValueSetByCode("giftad_link")); 
		model.addAttribute("giftadImage", valueSetService.selectValueSetByCode("giftad_image")); 
		
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		return "front/index";
	}

}