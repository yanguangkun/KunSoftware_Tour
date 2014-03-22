package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.ArticleRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Article;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.ArticleService;

@Controller 
@RequestMapping("/manager/article")
public class ArticleController extends BaseController{

private static Logger logger = LoggerFactory.getLogger(ArticleController.class);	
	
	@Autowired
	private ArticleService service;
	
	@RequestMapping("/list")
	public String listArticle(ModelMap model,Integer classifyId,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("文章列表");  
		 
		List<Article> list = service.getArticleListPage(classifyId,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/article/article-list";
	}
	
	@RequestMapping("/add")
	public String addArticle(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加文章");
		 
		return "manager/article/article-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addArticle(ArticleRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存文章"); 

		Article entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editArticle(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑文章");
		Article entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/article/article-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editArticle(ArticleRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存文章"); 
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delArticle(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除文章");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}
}
