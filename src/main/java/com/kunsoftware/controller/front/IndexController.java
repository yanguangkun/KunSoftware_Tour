package com.kunsoftware.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kunsoftware.entity.Gallery;
import com.kunsoftware.service.GalleryService;

@Controller
public class IndexController {

	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		
		List<Gallery> galleryList = galleryService.getGalleryListAll("1");
		model.addAttribute("galleryList", galleryList); 
		return "front/index";
	}

}