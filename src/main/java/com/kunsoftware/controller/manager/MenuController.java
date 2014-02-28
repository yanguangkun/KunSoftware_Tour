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
	private MenuService service;
	
	@RequestMapping("/list")
	public String list(ModelMap model,MenuRequestBean menuRequestBean,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("菜单列表");  
		 
		List<SysMenu> list = service.getMenuListPage(menuRequestBean,pageInfo); 
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
		SysMenu entity = service.insert(menuRequestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editMenu(ModelMap model,Integer id) {
		 
		logger.info("编辑菜单");
		SysMenu entity = service.selectByPrimaryKey(id);
		model.addAttribute("sysMenu", entity);  
		 
		return "manager/menu/menu-edit";
	}
	
	@RequestMapping(value="/edit.json")
	@ResponseBody 
	public JsonBean editMenu(MenuRequestBean menuRequestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存菜单"); 
		service.updateByPrimaryKey(menuRequestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delMenu(Integer id) throws KunSoftwareException {
		 
		logger.info("删除菜单");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}
}
