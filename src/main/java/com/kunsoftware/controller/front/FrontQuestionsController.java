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
import com.kunsoftware.bean.QuestionsRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.HeadIconTitle;
import com.kunsoftware.entity.Member;
import com.kunsoftware.entity.Questions;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.HeadIconTitleService;
import com.kunsoftware.service.MemberService;
import com.kunsoftware.service.QuestionsService;
import com.kunsoftware.util.WebUtil;

import freemarker.template.Template;

@Controller 
@RequestMapping("/product")
public class FrontQuestionsController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FrontProductDetailController.class);	

	
	@Autowired
	private QuestionsService questionsService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HeadIconTitleService headIconTitleService; 
	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer = null;  
	
	@RequestMapping(value="/questions.json")
	@ResponseBody 
	public JsonBean comments(ModelMap model,Integer destination,String banner,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("问答列表");  
		 
		try {
			Template tpl =  freeMarkerConfigurer.getConfiguration().getTemplate("front/product-questions.html"); 
			 
			pageInfo.setPageSize(3);
			List<Questions> retList = questionsService.getFrontQuestionsListPage(destination,banner,pageInfo);
			
			for(Questions questions: retList) {
				Member member = memberService.selectByPrimaryKey(questions.getMemberId());
				if(member != null && member.getHeadIconId() != null) {
					HeadIconTitle headIconTitle = headIconTitleService.selectByPrimaryKey(member.getHeadIconId());
					HeadIconTitle headTitle = headIconTitleService.selectByPrimaryKey(member.getHeadTitleId());
					if(headIconTitle != null) { 
						questions.setMemberImagePath("/images/uploadDir" + headIconTitle.getName());						 
					}
					
					if(headTitle != null) {
						questions.setMemberUserName(headTitle.getName());
					}
					
				} else {
					HeadIconTitle headIconTitle = headIconTitleService.selectMemberInfo();
					if(headIconTitle != null) {
						questions.setMemberImagePath("/images/uploadDir" + headIconTitle.getName()); 
					}
				}
				
				if(StringUtils.isEmpty(questions.getMemberImagePath())) {
					questions.setMemberImagePath("/images/yami.jpg");
				}
				if(StringUtils.isEmpty(questions.getMemberUserName())) {
					questions.setMemberUserName("路人");
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
	
	@RequestMapping(value="/questionsSave.json")
	@ResponseBody 
	public JsonBean editComments(QuestionsRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("编辑保存问答"); 
		JsonBean jsonBean = new JsonBean(); 
		requestBean.setMemberId(WebUtil.getMemberId());
		requestBean.setMemberUserName(WebUtil.getMemberUserName());
		questionsService.insert(requestBean);	
		
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
}
