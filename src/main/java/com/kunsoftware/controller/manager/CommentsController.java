package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.CommentsRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Comments;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.CommentsService;

@Controller 
@RequestMapping("/manager/comments")
public class CommentsController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(CommentsController.class);	
	
	@Autowired
	private CommentsService service;
	
	@RequestMapping("/list")
	public String listComments(ModelMap model,String audit,String reply,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("评论列表");  
		 
		List<Comments> list = service.getCommentsListPage(audit,reply,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/comments/comments-list";
	}
	
	@RequestMapping("/add")
	public String addComments(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加评论");
		 
		return "manager/comments/comments-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addComments(CommentsRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存评论"); 
		Comments entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editComments(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑评论");
		Comments entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/comments/comments-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editComments(CommentsRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存评论"); 
		
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delComments(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除评论");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}
 
}
