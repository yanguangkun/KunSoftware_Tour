package com.kunsoftware.controller.manager;

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

import com.kunsoftware.bean.YamiDiaryRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.YamiDiary;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.YamiDiaryService;

@Controller 
@RequestMapping("/manager/yamidiary")
public class YamiDiaryController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(YamiDiaryController.class);	
	
	@Autowired
	private YamiDiaryService service;
	
	@RequestMapping("/list")
	public String listYamiDiary(ModelMap model,String keyword,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("目的雅米日记");  
		 
		List<YamiDiary> list = service.getYamiDiaryListPage(keyword,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/yamidiary/yamidiary-list";
	}
	
	@RequestMapping("/add")
	public String addYamiDiary(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加雅米日记");
		 
		return "manager/yamidiary/yamidiary-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addYamiDiary(@RequestParam(value = "yamidiaryImageFile", required = false) MultipartFile file,
			@RequestParam(value = "yamidiaryIndexImageFile", required = false) MultipartFile indexFile,
			YamiDiaryRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存雅米日记"); 
		requestBean.setImageFile(file);
		requestBean.setIndexImageFile(indexFile);
		YamiDiary entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editYamiDiary(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑雅米日记");
		YamiDiary entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/yamidiary/yamidiary-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editYamiDiary(@RequestParam(value = "yamidiaryImageFile", required = false) MultipartFile file,
			@RequestParam(value = "yamidiaryIndexImageFile", required = false) MultipartFile indexFile,
			YamiDiaryRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存雅米日记"); 
		requestBean.setImageFile(file);
		requestBean.setIndexImageFile(indexFile);
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delYamiDiary(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除雅米日记");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
