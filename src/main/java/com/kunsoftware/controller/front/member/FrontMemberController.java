package com.kunsoftware.controller.front.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kunsoftware.bean.CommentsRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.bean.OrderViewBean;
import com.kunsoftware.bean.OrdersRequestBean;
import com.kunsoftware.bean.OrdersTravelRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Comments;
import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.entity.FlightChedulePlan;
import com.kunsoftware.entity.FlightChedulePrice;
import com.kunsoftware.entity.Orders;
import com.kunsoftware.entity.OrdersTravelList;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.CommentsService;
import com.kunsoftware.service.FlightChedulePlanService;
import com.kunsoftware.service.FlightChedulePriceService;
import com.kunsoftware.service.FlightCheduleService;
import com.kunsoftware.service.OrdersService;
import com.kunsoftware.service.OrdersTravelService;
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
	private FlightCheduleService flightCheduleService;
	
	@Autowired
	private FlightChedulePriceService flightChedulePriceService;
	
	@Autowired
	private FlightChedulePlanService flightChedulePlanService;
	
	@Autowired
	private ProductResourceService productResourceService;
	
	@Autowired
	private OrdersTravelService ordersTravelService;
	
	@Autowired
	private CommentsService commentsService;
	
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
	
	@RequestMapping(value="/orderEdit.json") 
	@ResponseBody 
	public JsonBean editOrders(OrdersRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存订单");  
		
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	
	@RequestMapping("/orderd")
	public String orderd(ModelMap model,Integer id) throws KunSoftwareException {
		
		logger.info("会员订单详情");  
		OrderViewBean orderViewBean = service.getFrontOrdersView(id); 
		
		ProductResource productResource = productResourceService.selectByPrimaryKey(new Integer(orderViewBean.getOrders().getProductId()));
		FlightChedule flightChedule = flightCheduleService.selectByPrimaryKey(orderViewBean.getOrders().getFlightCheduleId());
		if(flightChedule == null) flightChedule = new FlightChedule();
		
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
		model.addAttribute("flightChedule", flightChedule); 
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		
		return "front/m/orderd";
	}
	
	
	@RequestMapping("/orders-travel-edit")
	public String editOrdersTravel(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑客人名单");
		
		OrdersTravelList entity = ordersTravelService.selectByPrimaryKey(id);
		Orders orders = service.selectByPrimaryKey(entity.getOrdersId());
		model.addAttribute("entity", entity); 
		model.addAttribute("orders", orders);
		 
		return "front/m/orders-travel-edit";
	}
	
	@RequestMapping(value="/orders-travel-edit.json") 
	@ResponseBody 
	public JsonBean editOrdersTravel(@RequestParam(value = "orderstravelImageFile", required = false) MultipartFile file,OrdersTravelRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存客人名单"); 
		requestBean.setFile(file);
		ordersTravelService.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping("/orders-comment-edit")
	public String editComments(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑评论");
		
		ProductResource productResource = productResourceService.selectByPrimaryKey(id);
		Integer memberId = WebUtil.getMemberId();
		Comments entity = commentsService.selectByProduct(id,memberId);
		if(entity == null) entity = new Comments();
		model.addAttribute("entity", entity);
		model.addAttribute("productResource", productResource);
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		return "front/m/order-comment-edit";
	}
	
	@RequestMapping(value="/orders-comment-edit.json") 
	@ResponseBody 
	public JsonBean editComments(CommentsRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("编辑保存评论"); 
		requestBean.setMemberId(WebUtil.getMemberId());
		requestBean.setMemberUserName(WebUtil.getMemberUserName());
		commentsService.updateByProduct(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
}
