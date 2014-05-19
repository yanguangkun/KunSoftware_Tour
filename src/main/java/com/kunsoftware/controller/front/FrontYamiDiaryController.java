package com.kunsoftware.controller.front;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kunsoftware.controller.BaseController;
import com.kunsoftware.controller.manager.YamiDiaryController;
import com.kunsoftware.entity.YamiDiary;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.ValueSetService;
import com.kunsoftware.service.YamiDiaryService;

@Controller 
@RequestMapping("/yamidiary")
public class FrontYamiDiaryController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(YamiDiaryController.class);	
	
	@Autowired
	private YamiDiaryService service;
	
	@Autowired
	private ValueSetService valueSetService;
	
	@RequestMapping("/list")
	public String listYamiDiary(ModelMap model,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("目的雅米日记");  
		 
		List<YamiDiary> list = service.getYamiDiaryListPage("",pageInfo); 
		model.addAttribute("retList", list);
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		PageUtil.frontPageInfo(model, pageInfo);
		return "front/yamidiary-list";
	}
	
	@RequestMapping("/detail")
	public String detailYamiDiary(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("目的雅米日记");  
		 
		YamiDiary yamiDiary = null;
		if(id == null) {
			yamiDiary = service.selectByIndexRcommend();
		} else {
			yamiDiary = service.selectByPrimaryKey(id);
		}
		
		List list = service.getYamiDiaryNextPre(yamiDiary.getId());
		String nextId = "";
		String preId = "";
		if(list.size() == 1) {
			Map map = (Map)list.get(0);
			if("1".equals(map.get("type"))) {
				nextId = map.get("id").toString();
			} else {
				preId = map.get("id").toString();
			}
		}
		
		if(list.size() == 2) {
			Map map = (Map)list.get(0);
			nextId = map.get("id").toString();
			map = (Map)list.get(1);
			preId = map.get("id").toString();
		}
		
		model.addAttribute("yamiDiary", yamiDiary); 
		model.addAttribute("nextId", nextId); 
		model.addAttribute("preId", preId); 
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		return "front/yamidiary-detail";
	}

}
