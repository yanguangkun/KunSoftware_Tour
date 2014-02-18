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
import com.kunsoftware.bean.MenuRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.SysMenu;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.MenuService;

@Controller 
@RequestMapping("/manager/menu")
public class MenuController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MenuController.class);	
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/list")
	public String list(ModelMap model,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("菜单列表");  
		 
		List<SysMenu> list = menuService.getMenuListPage(pageInfo); 
		model.addAttribute("retList", list);  
		PageUtil.pageInfo(model, pageInfo);
		return "manager/menu/menu-list";
	}
	
	@RequestMapping("/add")
	public String addMenu(ModelMap model) {
		 
		logger.info("添加菜单");
		 
		return "manager/menu/menu-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addMenu(MenuRequestBean menuRequestBean) throws KunSoftwareException {
		 
		logger.info("保存菜单"); 
		menuService.insert(menuRequestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
}
