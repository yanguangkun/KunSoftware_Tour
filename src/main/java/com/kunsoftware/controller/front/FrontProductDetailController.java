package com.kunsoftware.controller.front;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.kunsoftware.bean.BuyBean;
import com.kunsoftware.bean.CommentsRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Comments;
import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.entity.FlightChedulePlan;
import com.kunsoftware.entity.FlightChedulePrice;
import com.kunsoftware.entity.HeadIconTitle;
import com.kunsoftware.entity.Member;
import com.kunsoftware.entity.Product;
import com.kunsoftware.entity.ProductIntroduce;
import com.kunsoftware.entity.ProductPlanTpl;
import com.kunsoftware.entity.ProductPriceTpl;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.CommentsService;
import com.kunsoftware.service.FlightChedulePlanService;
import com.kunsoftware.service.FlightChedulePriceService;
import com.kunsoftware.service.FlightCheduleService;
import com.kunsoftware.service.FlightSegmentService;
import com.kunsoftware.service.GalleryService;
import com.kunsoftware.service.HeadIconTitleService;
import com.kunsoftware.service.MemberService;
import com.kunsoftware.service.ProductIntroduceService;
import com.kunsoftware.service.ProductPlanTplService;
import com.kunsoftware.service.ProductPriceTplService;
import com.kunsoftware.service.ProductResourceService;
import com.kunsoftware.service.ProductService;
import com.kunsoftware.service.ValueSetService;
import com.kunsoftware.util.WebUtil;

import freemarker.template.Template;

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
	
	@Autowired
	private CommentsService commentsService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HeadIconTitleService headIconTitleService;
	
	@Autowired
	private FreeMarkerConfigurer  freeMarkerConfigurer = null;  
	
	@Autowired
	private ValueSetService valueSetService;
	
	@RequestMapping("/detail")
	public String detailProduct(ModelMap model,Integer id,BuyBean buyBean,PageInfo pageInfo) throws KunSoftwareException {
		 
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
			ProductPlanTpl tpl = null;
			ProductPlanTpl firstTpl = null;
			boolean isSelected = false;
			for(int i = 0;i < tplList.size();i++) {
				tpl = (ProductPlanTpl)tplList.get(i);
				if(firstTpl == null) firstTpl = tpl;
				if(tpl.getId().equals(buyBean.getTplId())) {
					tpl.setSelected("active");
					isSelected = true;
				}
			}
			if(!isSelected) {
				firstTpl.setSelected("active");
			}
		} else {
			tplList = productPriceTplService.getProductPriceTplListAll(productResource.getId());
			if(!tplList.isEmpty()) {
				ProductPriceTpl productPriceTpl = (ProductPriceTpl)tplList.get(0);
				model.addAttribute("chargeUnit", productPriceTpl.getChargeUnit());
			}			
			ProductPriceTpl tpl = null;
			ProductPriceTpl firstTpl = null;
			boolean isSelected = false;
			for(int i = 0;i < tplList.size();i++) {
				tpl = (ProductPriceTpl)tplList.get(i);
				if(firstTpl == null) firstTpl = tpl;
				if(tpl.getId().equals(buyBean.getTplId())) {
					tpl.setSelected("active");
					isSelected = true;
				}
			}
			if(!isSelected) {
				firstTpl.setSelected("active");
			}
		} 
		
		Calendar calendarStart = Calendar.getInstance();
		calendarStart.setTime(productResource.getFlightCheduleStart()); 
		int monthStart = calendarStart.get(Calendar.MONTH) + 1;
		calendarStart.setTime(productResource.getFlightCheduleEnd()); 
		int monthEnd = calendarStart.get(Calendar.MONTH) + 1;
		
		List flightCheduleList = flightCheduleService.getFlightCheduleListAll(productResource.getId(),null);
		Map map = null;
		Map firstMap = null;
		boolean isSelected = false;
		for(int i = 0;i < flightCheduleList.size();i++) {
			map = (Map)flightCheduleList.get(i);
			if(firstMap == null) firstMap = map;
			if(map.get("startDate").equals(buyBean.getCheduleDay())) {
				map.put("selected", "active");
				isSelected = true;
			}
		}
		if(!isSelected) {
			firstMap.put("selected", "active");
		}
		
		List monthList = new ArrayList();
		Map monthMap = null;
		int j = 0;
		for(int i = monthStart;i <= monthEnd;i++) {
			monthMap = new HashMap();
			monthMap.put("startMonth", i);
			if(j == 0 && buyBean.getCheduleMonth() == null) {
				monthMap.put("selected", "active"); 
			} else if((i+ "").equals(buyBean.getCheduleMonth())) {
				monthMap.put("selected", "active");
			}
			j++;
			monthList.add(monthMap);
		}
		
		if(buyBean.getNum1() == null || buyBean.getNum1().equals(0))
			buyBean.setNum1(1);
		if(buyBean.getNum6() == null || buyBean.getNum6().equals(0))
			buyBean.setNum6(1);
		model.addAttribute("product", product);
		model.addAttribute("galleryList", galleryList); 
		model.addAttribute("productResource", productResource);
		model.addAttribute("flightSegmentList", flightSegmentList);
		model.addAttribute("productIntroduce", productIntroduce);
		model.addAttribute("productIntroducelist", productIntroducelist);
		model.addAttribute("tplList", tplList);
		model.addAttribute("monthStart", monthStart);
		model.addAttribute("monthEnd", monthEnd);
		model.addAttribute("monthList", monthList);
		model.addAttribute("flightCheduleList", flightCheduleList);
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		model.addAttribute("buyBean", buyBean);
		return "front/product-detail";
	}
	
	@RequestMapping(value="/cheduleDay.json")
	@ResponseBody 
	public JsonBean cheduleDay(ModelMap model,Integer id,String startMonth) throws KunSoftwareException {
		
		startMonth = StringUtils.leftPad(startMonth, 2, "0");
		List flightCheduleList = flightCheduleService.getFlightCheduleListAll(id,startMonth);
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("days", flightCheduleList);
		return jsonBean;
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
		if(flightChedule == null) {
			jsonBean.setSuccess("0");
			jsonBean.setMessage("班期不存在!");
			return jsonBean;
		}
		
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
	
	
	@RequestMapping(value="/comments.json")
	@ResponseBody 
	public JsonBean comments(ModelMap model,Integer productResourceId,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("评论列表");  
		 
		try {
			Template tpl =  freeMarkerConfigurer.getConfiguration().getTemplate("front/product-coments.html"); 
			 
			pageInfo.setPageSize(3);
			List<Comments> retList = commentsService.getFrontCommentsListPage(productResourceId,pageInfo);
			
			for(Comments comments: retList) {
				Member member = memberService.selectByPrimaryKey(comments.getMemberId());
				if(member != null && member.getHeadIconId() != null) {
					HeadIconTitle headIconTitle = headIconTitleService.selectByPrimaryKey(member.getHeadIconId());
					if(headIconTitle != null) { 
						comments.setMemberImagePath("/images/uploadDir" + headIconTitle.getName()); 						
					}
				}
				
				if(StringUtils.isEmpty(comments.getMemberImagePath())) {
					comments.setMemberImagePath("/images/yami.jpg");
				}
			}
			
			
			
			Map map  = new HashMap();  
			map.put("retList",retList);
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
	
	@RequestMapping(value="/commentsSave.json")
	@ResponseBody 
	public JsonBean editComments(CommentsRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("编辑保存评论"); 
		JsonBean jsonBean = new JsonBean();
		if(WebUtil.getMemberId() == null) {
			jsonBean.setMessage("请登录后在评论。"); 	
			return jsonBean;
		}
		Comments comments = commentsService.selectByProduct(requestBean.getProductResourceId(), WebUtil.getMemberId());
		if(comments != null) {
			jsonBean.setMessage("此产品已经评论,不能重复评论。"); 	
			return jsonBean;
		}
		
		requestBean.setMemberId(WebUtil.getMemberId());
		requestBean.setMemberUserName(WebUtil.getMemberUserName());
		commentsService.updateByProduct(requestBean);		
		
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	

	@RequestMapping(value="/checkLogin.json")
	@ResponseBody 
	public JsonBean checkLogin(CommentsRequestBean requestBean) throws KunSoftwareException {
		 
		JsonBean jsonBean = new JsonBean();
		if(WebUtil.getMemberId() == null) {
			jsonBean.setMessage("请登录后在预订。"); 
			jsonBean.put("isLogin", "0");
			return jsonBean;
		}
		 
		jsonBean.put("isLogin", "1");
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
}
