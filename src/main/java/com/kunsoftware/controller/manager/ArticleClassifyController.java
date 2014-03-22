package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.ArticleClassifyRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.entity.ArticleClassify;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.ArticleClassifyService;

@Controller 
@RequestMapping("/manager/articleclassify")
public class ArticleClassifyController {

	private static Logger logger = LoggerFactory.getLogger(ArticleClassifyController.class);	
	
	@Autowired
	private ArticleClassifyService service;
	
	@RequestMapping("/list")
	public String listArticleClassify(ModelMap model) throws KunSoftwareException {
		 
		logger.info("文章分类列表");  
		 
		List<ArticleClassify> list = service.getArticleClassifyListAll(); 
		model.addAttribute("retList", list);
		return "manager/articleclassify/articleclassify-list";
	}
	
	@RequestMapping("/add")
	public String addArticleClassify(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加文章分类");
		 
		return "manager/articleclassify/articleclassify-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addArticleClassify(ArticleClassifyRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存文章分类");  ;
		ArticleClassify entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editArticleClassify(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑文章分类");
		ArticleClassify entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/articleclassify/articleclassify-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editArticleClassify(ArticleClassifyRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存文章分类");  
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delArticleClassify(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除文章分类");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}
}
