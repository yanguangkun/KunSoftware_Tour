package com.kunsoftware.controller.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.bean.ProductResourceRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Destination;
import com.kunsoftware.entity.Ground;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.entity.ValueSet;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.DestinationService;
import com.kunsoftware.service.GalleryService;
import com.kunsoftware.service.GroundService;
import com.kunsoftware.service.GroundTagService;
import com.kunsoftware.service.ProductResourceService;
import com.kunsoftware.service.ProductService;
import com.kunsoftware.service.ValueSetService;
import com.kunsoftware.util.WebUtil;

import freemarker.template.Template;

@Controller 
@RequestMapping("/product")
public class FrontProductController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FrontProductController.class);	
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private DestinationService destinationService;
	
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private GroundService groundService;
	
	@Autowired
	private GroundTagService groundTagService;
	
	@Autowired
	private FreeMarkerConfigurer  freeMarkerConfigurer = null;  
	   
	@Autowired
	private ValueSetService valueSetService;
	
	@Autowired
	private ProductResourceService productResourceService;
	
	@RequestMapping("/list")
	public String listProduct(ModelMap model,ProductResourceRequestBean requestBean,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("产品列表"); 
		
		
		if(StringUtils.isEmpty(requestBean.getProductType())) {
			requestBean.setProductType("-1");
		}
		
		String productType = requestBean.getProductType();
		if("-1".equals(requestBean.getProductType())) {
			requestBean.setSalePrice("1");
			requestBean.setProductType("");
		}
		
		Destination destination = null;
		if(requestBean.getArriveDestination() == null) {
			destination = destinationService.selectByName(requestBean.getDestination());
		} else {
			destination = destinationService.selectByPrimaryKey(requestBean.getArriveDestination());
		}
		requestBean.setArriveDestination(destination.getId());
		 
		pageInfo.setPageSize(2);
		
		PageInfo groundPageInfo = new PageInfo();
		groundPageInfo.setPageSize(4);
		
		List list = service.getProductResourceListPage(requestBean,pageInfo); 
		List galleryList = galleryService.getGalleryListAll(null, destination.getId().toString(), null);
		List groundList = groundService.getGroundByDestinationListPage(destination.getId(),groundPageInfo);
		model.addAttribute("retList", list);
		model.addAttribute("galleryList", galleryList); 
		model.addAttribute("groundList", groundList); 
		model.addAttribute("destination", destination);
		model.addAttribute("productType", productType);
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		PageUtil.pageInfo(model, pageInfo);
		return "front/product-list";
	}
	
	@RequestMapping("/list-g")
	public String listProductG(ModelMap model,ProductResourceRequestBean requestBean,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("产品列表");  
		
		if(StringUtils.isEmpty(requestBean.getProductType())) {
			requestBean.setProductType("-1");
		}
		
		String productType = requestBean.getProductType();
		if("-1".equals(requestBean.getProductType())) {
			requestBean.setSalePrice("1");
			requestBean.setProductType("");
		}
		
		Destination destination = null;
		if(requestBean.getArriveDestination() == null) {
			destination = destinationService.selectByName(requestBean.getDestination());
		} else {
			destination = destinationService.selectByPrimaryKey(requestBean.getArriveDestination());
		}
		requestBean.setArriveDestination(destination.getId());
		 
		Ground ground = groundService.selectByPrimaryKey(requestBean.getGroundId());
		pageInfo.setPageSize(2);
		
		List<ValueSet> groundTagList = groundTagService.getValueSetListByGround(ground.getDestination(), ground.getId());
		 
		List list = service.getProductResourceListPage(requestBean,pageInfo); 
		List galleryList = galleryService.getGalleryListAll(null, destination.getId().toString(), null);
		model.addAttribute("retList", list);
		model.addAttribute("galleryList", galleryList); 
		model.addAttribute("ground", ground); 
		model.addAttribute("groundTagList", groundTagList); 
		model.addAttribute("destination", destination);
		model.addAttribute("productType", productType);
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		PageUtil.pageInfo(model, pageInfo);
		return "front/product-list-g";
	}
	
	@RequestMapping("/list-m")
	public String listProductM(ModelMap model,ProductResourceRequestBean requestBean,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("产品列表"); 
		 
		
		Destination destination = null;
		if(requestBean.getArriveDestination() == null) {
			destination = destinationService.selectByName(requestBean.getDestination());
		} else {
			destination = destinationService.selectByPrimaryKey(requestBean.getArriveDestination());
		}
		List galleryList = null;
		if(destination != null) {
			requestBean.setArriveDestination(destination.getId());
			galleryList = galleryService.getGalleryListAll(null, destination.getId().toString(), null);
		} else {
			galleryList = galleryService.getGalleryListAll("2");
			destination = new Destination();
			requestBean.setMarryRecommend("1");
		}
		
		requestBean.setProductType("4");
		pageInfo.setPageSize(2);
		List list = service.getProductResourceListPage(requestBean,pageInfo); 
		 
		model.addAttribute("retList", list);
		model.addAttribute("galleryList", galleryList);  
		model.addAttribute("destination", destination); 
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		
		PageUtil.pageInfo(model, pageInfo);
		return "front/product-list-m";
	}
	
	@RequestMapping(value="/more.json")
	@ResponseBody 
	public JsonBean listMoreProduct(ModelMap model,ProductResourceRequestBean requestBean,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("产品列表"); 
		if("-1".equals(requestBean.getProductType())) {
			requestBean.setSalePrice("1");
			requestBean.setProductType("");
		}
		try {
			Template tpl =  freeMarkerConfigurer.getConfiguration().getTemplate("front/product-more.html"); 
			Destination destination = null;
			if(requestBean.getArriveDestination() == null) {
				destination = destinationService.selectByName(requestBean.getDestination());
			} else {
				destination = destinationService.selectByPrimaryKey(requestBean.getArriveDestination());
			}
			
			if(destination != null)
				requestBean.setArriveDestination(destination.getId());
			pageInfo.setPageSize(2);
			List list = service.getProductResourceListPage(requestBean,pageInfo);
			 
			Map map  = new HashMap();  
			map.put("retList",list);  
			map.put("contextPath",WebUtil.getContextPath());
		        
			JsonBean jsonBean = new JsonBean();
			jsonBean.put("result", FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map));
			jsonBean.put("totalPages", pageInfo.getTotalPages());
			jsonBean.setMessage("操作成功");
			 		 
			return jsonBean;
		} catch(Exception e) {
			throw new KunSoftwareException(e);
		}
	}
	
	@RequestMapping(value="/ground.json")
	@ResponseBody 
	public JsonBean ground(ModelMap model,Integer destination,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("地接列表");  
		 
		try {
			Template tpl =  freeMarkerConfigurer.getConfiguration().getTemplate("front/product-ground.html"); 
			 
			pageInfo.setPageSize(4);
			List groundList = groundService.getGroundByDestinationListPage(destination,pageInfo);
			
			Map map  = new HashMap();  
			map.put("retList",groundList);  
			map.put("destination",destination);  
			map.put("pageInfo",pageInfo);  
			map.put("contextPath",WebUtil.getContextPath());
			PageUtil.frontPageInfo(map, pageInfo);
		    
			
			JsonBean jsonBean = new JsonBean();
			
			jsonBean.put("result", FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map)); 
			jsonBean.setMessage("操作成功");
			 		 
			return jsonBean;
		} catch(Exception e) {
			throw new KunSoftwareException(e);
		}
	}
	
	@RequestMapping(value="/praise.json")
	@ResponseBody 
	public JsonBean somePraise(Integer id) throws KunSoftwareException {
		
		productResourceService.updateSomePraise(id);
		JsonBean jsonBean = new JsonBean();
		 
		ProductResource productResource = productResourceService.selectByPrimaryKey(id);
		jsonBean.setMessage("操作成功");
		jsonBean.put("somePraise", productResource.getSomePraise());
		return jsonBean;
	}
}
