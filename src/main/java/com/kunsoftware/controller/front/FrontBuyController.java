package com.kunsoftware.controller.front;

import java.util.ArrayList;
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

import com.kunsoftware.bean.BuyBean;
import com.kunsoftware.bean.CommentsRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Comments;
import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.entity.FlightChedulePlan;
import com.kunsoftware.entity.FlightChedulePrice;
import com.kunsoftware.entity.Orders;
import com.kunsoftware.entity.OrdersDetail;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.FlightChedulePlanService;
import com.kunsoftware.service.FlightChedulePriceService;
import com.kunsoftware.service.FlightCheduleService;
import com.kunsoftware.service.OrdersService;
import com.kunsoftware.service.ProductResourceService;
import com.kunsoftware.service.ValueSetService;
import com.kunsoftware.util.WebUtil;

@Controller 
@RequestMapping("/product")
public class FrontBuyController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FrontBuyController.class);	
	
	@Autowired
	private OrdersService service;
	
	@Autowired
	private ProductResourceService productResourceService;
	
	@Autowired
	private FlightCheduleService flightCheduleService;
	
	@Autowired
	private FlightChedulePriceService flightChedulePriceService;
	
	@Autowired
	private FlightChedulePlanService flightChedulePlanService;
	
	@Autowired
	private ValueSetService valueSetService; 
	
	@RequestMapping("/buy")
	public String buy(ModelMap model,BuyBean buyBean) throws KunSoftwareException {
		
		
		
		logger.info("购买");  
		ProductResource productResource = productResourceService.selectByPrimaryKey(buyBean.getId());
		
		Date date = new Date();
		date = DateUtils.setMonths(date, new Integer(buyBean.getCheduleMonth()) - 1);
		date = DateUtils.setDays(date, new Integer(buyBean.getCheduleDay()));
		
		FlightChedule flightChedule = flightCheduleService.selectByResource(buyBean.getId(), DateFormatUtils.format(date, "yyyy-MM-dd"));
		List retList = new ArrayList();
		OrdersDetail ordersDetail = null;
		double allTotal = 0;
		if("1".equals(productResource.getCombo())) {
			FlightChedulePlan flightChedulePlan = flightChedulePlanService.selectByFlightCheduleId(flightChedule.getId(), new Integer(buyBean.getTplId()));
			model.addAttribute("name", flightChedulePlan.getName()); 
			model.addAttribute("decribe", flightChedulePlan.getPlanDescribe()); 
			
			if(buyBean.getNum1() > 0) {
				ordersDetail = new OrdersDetail();
				ordersDetail.setType("1");
				ordersDetail.setUnitPrice(flightChedulePlan.getAdultPrice());
				ordersDetail.setQuantity(buyBean.getNum1());
				retList.add(ordersDetail);
				
				allTotal = allTotal + flightChedulePlan.getAdultPrice().doubleValue() * buyBean.getNum1();
			}
			
			if(buyBean.getNum2() > 0) {
				ordersDetail = new OrdersDetail();
				ordersDetail.setType("2");
				ordersDetail.setUnitPrice(flightChedulePlan.getChildBedPrice());
				ordersDetail.setQuantity(buyBean.getNum2());
				retList.add(ordersDetail);
				
				allTotal = allTotal + flightChedulePlan.getChildBedPrice().doubleValue() * buyBean.getNum2();
			}
			
			if(buyBean.getNum3() > 0) {
				ordersDetail = new OrdersDetail();
				ordersDetail.setType("3");
				ordersDetail.setUnitPrice(flightChedulePlan.getAdultExtraBedPrice());
				ordersDetail.setQuantity(buyBean.getNum3());
				retList.add(ordersDetail);
				
				allTotal = allTotal + flightChedulePlan.getAdultExtraBedPrice().doubleValue() * buyBean.getNum3();
			}
			
			if(buyBean.getNum4() > 0) {
				ordersDetail = new OrdersDetail();
				ordersDetail.setType("4");
				ordersDetail.setUnitPrice(flightChedulePlan.getChildNoBedPrice());
				ordersDetail.setQuantity(buyBean.getNum4());
				retList.add(ordersDetail);
				allTotal = allTotal + flightChedulePlan.getChildNoBedPrice().doubleValue() * buyBean.getNum4();
			}
			
			int allNum = buyBean.getNum1() + buyBean.getNum2() + buyBean.getNum3() + buyBean.getNum4();
			if(allNum > 1 && allNum %2 != 0) { 				 
				ordersDetail = new OrdersDetail();
				ordersDetail.setName(flightChedulePlan.getName());
				ordersDetail.setType("5");
				ordersDetail.setUnitPrice(flightChedulePlan.getSingleRoom());
				ordersDetail.setQuantity(1);
				retList.add(ordersDetail);
				allTotal = allTotal + flightChedulePlan.getSingleRoom().doubleValue() * 1;
				 
			} 
		} else {
			FlightChedulePrice flightChedulePrice = flightChedulePriceService.selectByFlightCheduleId(flightChedule.getId(), new Integer(buyBean.getTplId()));
			model.addAttribute("name", flightChedulePrice.getName()); 
			model.addAttribute("decribe", flightChedulePrice.getPriceDescribe()); 
			if(buyBean.getNum6() > 0) {
				ordersDetail = new OrdersDetail();
				ordersDetail.setType("6");
				ordersDetail.setUnitPrice(flightChedulePrice.getPrice());
				ordersDetail.setQuantity(buyBean.getNum6());
				retList.add(ordersDetail);
				allTotal = allTotal + flightChedulePrice.getPrice().doubleValue() * buyBean.getNum6();
			}
		}		
		
		buyBean.setAllTotal(allTotal);
		model.addAttribute("productResource", productResource);
		model.addAttribute("flightChedule", flightChedule);
		
		model.addAttribute("retList", retList); 
		model.addAttribute("buyBean", buyBean);
		
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		return "front/product-buy";
	}
	
	
	@RequestMapping("/buy2")
	public String buy2(ModelMap model,BuyBean buyBean) throws KunSoftwareException {
		 
		logger.info("购买2");  
		
		Integer num1 = buyBean.getNum1() + buyBean.getNum3();
		Integer num2 = buyBean.getNum2() + buyBean.getNum4();
		  
		model.addAttribute("num1", num1);		
		model.addAttribute("num2", num2);
		model.addAttribute("buyBean", buyBean);		
		
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		return "front/product-buy2";
	}
	
	@RequestMapping("/buy3")
	public String buy3(ModelMap model,BuyBean buyBean) throws KunSoftwareException {
		 
		logger.info("购买3");
		ProductResource productResource = productResourceService.selectByPrimaryKey(buyBean.getId());
		
		Date date = new Date();
		date = DateUtils.setMonths(date, new Integer(buyBean.getCheduleMonth()) - 1);
		date = DateUtils.setDays(date, new Integer(buyBean.getCheduleDay()));
		
		FlightChedule flightChedule = flightCheduleService.selectByResource(buyBean.getId(), DateFormatUtils.format(date, "yyyy-MM-dd"));
		List retList = new ArrayList();
		OrdersDetail ordersDetail = null;
		double allTotal = 0;
		Integer flightChedulePlanPriceId = null;
		if("1".equals(productResource.getCombo())) {
			FlightChedulePlan flightChedulePlan = flightChedulePlanService.selectByFlightCheduleId(flightChedule.getId(), new Integer(buyBean.getTplId()));
			model.addAttribute("name", flightChedulePlan.getName()); 
			model.addAttribute("decribe", flightChedulePlan.getPlanDescribe()); 
			flightChedulePlanPriceId = flightChedulePlan.getId();
			if(buyBean.getNum1() > 0) {
				ordersDetail = new OrdersDetail();
				ordersDetail.setType("1");
				ordersDetail.setUnitPrice(flightChedulePlan.getAdultPrice());
				ordersDetail.setQuantity(buyBean.getNum1());
				retList.add(ordersDetail);
				
				allTotal = allTotal + flightChedulePlan.getAdultPrice().doubleValue() * buyBean.getNum1();
			}
			
			if(buyBean.getNum2() > 0) {
				ordersDetail = new OrdersDetail();
				ordersDetail.setType("2");
				ordersDetail.setUnitPrice(flightChedulePlan.getChildBedPrice());
				ordersDetail.setQuantity(buyBean.getNum2());
				retList.add(ordersDetail);
				
				allTotal = allTotal + flightChedulePlan.getChildBedPrice().doubleValue() * buyBean.getNum2();
			}
			
			if(buyBean.getNum3() > 0) {
				ordersDetail = new OrdersDetail();
				ordersDetail.setType("3");
				ordersDetail.setUnitPrice(flightChedulePlan.getAdultExtraBedPrice());
				ordersDetail.setQuantity(buyBean.getNum3());
				retList.add(ordersDetail);
				
				allTotal = allTotal + flightChedulePlan.getAdultExtraBedPrice().doubleValue() * buyBean.getNum3();
			}
			
			if(buyBean.getNum4() > 0) {
				ordersDetail = new OrdersDetail();
				ordersDetail.setType("4");
				ordersDetail.setUnitPrice(flightChedulePlan.getChildNoBedPrice());
				ordersDetail.setQuantity(buyBean.getNum4());
				retList.add(ordersDetail);
				allTotal = allTotal + flightChedulePlan.getChildNoBedPrice().doubleValue() * buyBean.getNum4();
			}
			
			int allNum = buyBean.getNum1() + buyBean.getNum2() + buyBean.getNum3() + buyBean.getNum4();
			if(allNum > 1 && allNum %2 != 0) { 				 
				ordersDetail = new OrdersDetail();
				ordersDetail.setName(flightChedulePlan.getName());
				ordersDetail.setType("5");
				ordersDetail.setUnitPrice(flightChedulePlan.getSingleRoom());
				ordersDetail.setQuantity(1);
				retList.add(ordersDetail);
				allTotal = allTotal + flightChedulePlan.getSingleRoom().doubleValue() * 1;
				 
			}
		} else {
			FlightChedulePrice flightChedulePrice = flightChedulePriceService.selectByFlightCheduleId(flightChedule.getId(), new Integer(buyBean.getTplId()));
			flightChedulePlanPriceId = flightChedulePrice.getId();
			model.addAttribute("name", flightChedulePrice.getName()); 
			model.addAttribute("decribe", flightChedulePrice.getPriceDescribe()); 
			if(buyBean.getNum6() > 0) {
				ordersDetail = new OrdersDetail();
				ordersDetail.setName(flightChedulePrice.getName());
				ordersDetail.setType("6");
				ordersDetail.setUnitPrice(flightChedulePrice.getPrice());
				ordersDetail.setQuantity(buyBean.getNum6());
				retList.add(ordersDetail);
				allTotal = allTotal + flightChedulePrice.getPrice().doubleValue() * buyBean.getNum5();
			}
		} 
		
		buyBean.setFlightChedulePlanPriceId(flightChedulePlanPriceId);
		Orders orders = service.insertMemberOrder(buyBean, productResource, flightChedule, retList);
		 
		model.addAttribute("orders",orders);
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		return "front/product-buy3";
	}
}
