package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.News;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.NewsService;

@Controller 
@RequestMapping("/manager/news")
public class NewsController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(NewsController.class);	
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("/list")
	public String list(ModelMap model,PageInfo pageInfo) {
		 
		logger.info("新闻列表");  
		
		List<News> list = newsService.getNewsListPage(pageInfo); 
		model.addAttribute("retList", list);  
		PageUtil.pageInfo(model, pageInfo);
		return "manager/news/news-list";
	}	
	
	@RequestMapping(value="read.json")
	@ResponseBody 
	public JsonBean deleteContact(Integer id) {
		 
		logger.info("新闻阅读");
		 
		newsService.readNews(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功");		 
		return jsonBean;
	}
}
