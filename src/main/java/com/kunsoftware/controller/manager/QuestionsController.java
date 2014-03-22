package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.QuestionsRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Questions;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.QuestionsService;

@Controller 
@RequestMapping("/manager/questions")
public class QuestionsController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(QuestionsController.class);	
	
	@Autowired
	private QuestionsService service;
	
	@RequestMapping("/list")
	public String listQuestions(ModelMap model,String audit,String reply,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("问答列表");  
		 
		List<Questions> list = service.getQuestionsListPage(audit,reply,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/questions/questions-list";
	}
	
	@RequestMapping("/add")
	public String addQuestions(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加问答");
		 
		return "manager/questions/questions-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addQuestions(QuestionsRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存问答"); 
		Questions entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editQuestions(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑问答");
		Questions entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/questions/questions-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editQuestions(QuestionsRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存问答"); 
		
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delQuestions(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除问答");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
