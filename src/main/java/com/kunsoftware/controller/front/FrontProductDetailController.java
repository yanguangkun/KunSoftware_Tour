package com.kunsoftware.controller.front;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.entity.FlightChedulePlan;
import com.kunsoftware.entity.FlightChedulePrice;
import com.kunsoftware.entity.Product;
import com.kunsoftware.entity.ProductIntroduce;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.service.FlightChedulePlanService;
import com.kunsoftware.service.FlightChedulePriceService;
import com.kunsoftware.service.FlightCheduleService;
import com.kunsoftware.service.FlightSegmentService;
import com.kunsoftware.service.GalleryService;
import com.kunsoftware.service.ProductIntroduceService;
import com.kunsoftware.service.ProductPlanTplService;
import com.kunsoftware.service.ProductPriceTplService;
import com.kunsoftware.service.ProductResourceService;
import com.kunsoftware.service.ProductService;

@Controller 
@RequestMapping("/product")
public class FrontProductDetailController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FrontProductDetailController.class);	
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductResourceService productResourceService;
	
	@Autowired
	private FlightSegmentService flightSegmentService;
	
	@Autowired
	private ProductIntroduceService productIntroduceService;
	
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private ProductPriceTplService productPriceTplService;
	
	@Autowired
	private ProductPlanTplService productPlanTplService;
	
	@Autowired
	private FlightCheduleService flightCheduleService;
	
	@Autowired
	private FlightChedulePriceService flightChedulePriceService;
	
	@Autowired
	private FlightChedulePlanService flightChedulePlanService;
	
	@RequestMapping("/detail")
	public String detailProduct(ModelMap model,Integer id,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("产品详情");  
		
		ProductResource productResource = productResourceService.selectByPrimaryKey(id);
		Product product = service.selectByPrimaryKey(productResource.getProductId());
		List flightSegmentList = flightSegmentService.getFlightSegmentAirlineListAll(productResource.getFlightId());
		List<ProductIntroduce> productIntroducelist = productIntroduceService.getProductIntroduceListAll(product.getId()); 
		ProductIntroduce productIntroduce = null;
		if(!productIntroducelist.isEmpty()) {
			productIntroduce = productIntroducelist.get(0);
			productIntroducelist.remove(0);
		} else {
			productIntroduce = new ProductIntroduce();
		}
		List galleryList = galleryService.getGalleryListAll(null, null, productResource.getId().toString());
		List tplList = null;
		
		if("1".equals(productResource.getCombo())) {
			tplList = productPlanTplService.getProductPlanTplListAll(productResource.getId());
		} else {
			tplList = productPriceTplService.getProductPriceTplListAll(productResource.getId());
		} 
		
		Calendar calendarStart = Calendar.getInstance();
		calendarStart.setTime(productResource.getFlightCheduleStart()); 
		int monthStart = calendarStart.get(Calendar.MONTH) + 1;
		calendarStart.setTime(productResource.getFlightCheduleEnd()); 
		int monthEnd = calendarStart.get(Calendar.MONTH) + 1;
		
		List flightCheduleList = flightCheduleService.getFlightCheduleListAll(productResource.getId());
		
		model.addAttribute("product", product);
		model.addAttribute("galleryList", galleryList); 
		model.addAttribute("productResource", productResource);
		model.addAttribute("flightSegmentList", flightSegmentList);
		model.addAttribute("productIntroduce", productIntroduce);
		model.addAttribute("productIntroducelist", productIntroducelist);
		model.addAttribute("tplList", tplList);
		model.addAttribute("monthStart", monthStart);
		model.addAttribute("monthEnd", monthEnd);
		model.addAttribute("flightCheduleList", flightCheduleList);
		
		return "front/product-detail";
	}
	
	@RequestMapping(value="/price.json")
	@ResponseBody 
	public JsonBean productPrice(ModelMap model,Integer id,String tplId,String cheduleMonth,String cheduleDay) throws KunSoftwareException {
		
		ProductResource productResource = productResourceService.selectByPrimaryKey(id);
		
		Date date = new Date();
		date = DateUtils.setMonths(date, new Integer(cheduleMonth) - 1);
		date = DateUtils.setDays(date, new Integer(cheduleDay));
		
		FlightChedule flightChedule = flightCheduleService.selectByResource(id, DateFormatUtils.format(date, "yyyy-MM-dd"));
		
		JsonBean jsonBean = new JsonBean();
		
		if("1".equals(productResource.getCombo())) {
			FlightChedulePlan flightChedulePlan = flightChedulePlanService.selectByFlightCheduleId(flightChedule.getId(), new Integer(tplId));
			 
			jsonBean.put("adultPrice", flightChedulePlan.getAdultPrice());
			jsonBean.put("adultExtraBedPrice", flightChedulePlan.getAdultExtraBedPrice());
			jsonBean.put("childBedPrice", flightChedulePlan.getChildBedPrice());
			jsonBean.put("childNoBedPrice", flightChedulePlan.getChildBedPrice());
			jsonBean.put("singleRoom", flightChedulePlan.getSingleRoom());
		} else {
			FlightChedulePrice flightChedulePrice = flightChedulePriceService.selectByFlightCheduleId(flightChedule.getId(), new Integer(tplId));
			jsonBean.put("price", flightChedulePrice.getPrice());
		} 
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}
}
