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
import com.kunsoftware.bean.SysUserRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.SysUser;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.UserService;

@Controller 
@RequestMapping("/manager/user")
public class UserController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);	
	
	@Autowired
	private UserService service;	 
	
	@RequestMapping("/list")
	public String listUser(ModelMap model) throws KunSoftwareException {
		 
		logger.info("系统用户列表");  
		 
		List<SysUser> list = service.getSysUserListAll(); 
		model.addAttribute("retList", list); 
		return "manager/user/user-list";
	}
	
	@RequestMapping("/add")
	public String addUser(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加系统用户");
		 
		return "manager/user/user-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addUser(SysUserRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存系统用户"); 

		SysUser entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editUser(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑系统用户");
		SysUser entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/user/user-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editUser(SysUserRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存系统用户"); 
		 
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delUser(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除系统用户");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
