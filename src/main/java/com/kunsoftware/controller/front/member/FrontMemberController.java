package com.kunsoftware.controller.front.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kunsoftware.bean.OrderViewBean;
import com.kunsoftware.bean.OrdersRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.FlightChedulePlan;
import com.kunsoftware.entity.FlightChedulePrice;
import com.kunsoftware.entity.Orders;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.FlightChedulePlanService;
import com.kunsoftware.service.FlightChedulePriceService;
import com.kunsoftware.service.OrdersService;
import com.kunsoftware.service.ProductResourceService;
import com.kunsoftware.service.ValueSetService;
import com.kunsoftware.util.WebUtil;

@Controller 
@RequestMapping("/m")
public class FrontMemberController extends BaseController{

	private static Logger logger = LoggerFactory.getLogger(FrontMemberController.class);	

	@Autowired
	private OrdersService service;
	
	@Autowired
	private ValueSetService valueSetService;
	 
	@Autowired
	private FlightChedulePriceService flightChedulePriceService;
	
	@Autowired
	private FlightChedulePlanService flightChedulePlanService;
	
	@Autowired
	private ProductResourceService productResourceService;
	
	@RequestMapping("/order")
	public String order(ModelMap model,PageInfo pageInfo) throws KunSoftwareException {
		
		logger.info("会员订单");  

		OrdersRequestBean bean = new OrdersRequestBean();
		bean.setUserId(WebUtil.getMemberId());
		
		List<Orders> list = service.getOrdersListPage(bean,pageInfo); 
		model.addAttribute("retList", list);
		model.addAttribute("memberUserName", WebUtil.getMemberUserName());
		model.addAttribute("memberTrueName", WebUtil.getMemberEntity().getTrueName());
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		
		PageUtil.frontPageInfo(model, pageInfo);
		return "front/m/order";
	}
	
	@RequestMapping("/orderd")
	public String orderd(ModelMap model,Integer id) throws KunSoftwareException {
		
		logger.info("会员订单详情");  
		OrderViewBean orderViewBean = service.getFrontOrdersView(id); 
		
		ProductResource productResource = productResourceService.selectByPrimaryKey(new Integer(orderViewBean.getOrders().getProductId()));
		 
		if("1".equals(productResource.getCombo())) {
			FlightChedulePlan flightChedulePlan = flightChedulePlanService.selectByPrimaryKey(orderViewBean.getOrders().getFlightChedulePlanPriceId());
			 
			model.addAttribute("name", flightChedulePlan.getName()); 
			model.addAttribute("decribe", flightChedulePlan.getPlanDescribe()); 
		} else {
			FlightChedulePrice flightChedulePrice = flightChedulePriceService.selectByPrimaryKey(orderViewBean.getOrders().getFlightChedulePlanPriceId());
			model.addAttribute("name", flightChedulePrice.getName()); 
			model.addAttribute("decribe", flightChedulePrice.getPriceDescribe()); 
		} 
		model.addAttribute("bean", orderViewBean); 
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		
		return "front/m/orderd";
	}
}
