package com.kunsoftware.controller.front;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Article;
import com.kunsoftware.entity.ArticleClassify;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.service.ArticleClassifyService;
import com.kunsoftware.service.ArticleService;

@Controller 
@RequestMapping("/article")
public class FrontArticleController extends BaseController{

	private static Logger logger = LoggerFactory.getLogger(FrontArticleController.class);	

	@Autowired
	private ArticleService service;
	
	@Autowired
	private ArticleClassifyService articleClassifyService;
	
	@RequestMapping("/detail")
	public String detailArticle(ModelMap model,Integer id) throws KunSoftwareException {
		
		logger.info("文章");  
		Article entity = service.selectByPrimaryKey(id);
		ArticleClassify articleClassify = articleClassifyService.selectByPrimaryKey(entity.getClassifyId());
		List<Article> list = service.getArticleListPage(entity.getClassifyId(), new PageInfo());
		model.addAttribute("entity", entity);  
		model.addAttribute("articleClassify", articleClassify);  
		model.addAttribute("retList", list); 
		
		return "front/article-detail";
	}
}
