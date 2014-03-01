package com.kunsoftware.controller.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kunsoftware.bean.DialogBean;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.SysModalDialogService;

@Controller 
@RequestMapping("/manager/dialog")
public class ModalDialogController {
	
	private static Logger logger = LoggerFactory.getLogger(ModalDialogController.class);	
	
	@Autowired
	private SysModalDialogService service;
	
	@RequestMapping("/list")
	public String list(ModelMap model,String code,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("菜单列表");  
		 
		DialogBean dialogBean = service.getDialogInfo(code,pageInfo); 
		model.addAttribute("dialogBean", dialogBean);  
		PageUtil.dialogPageInfo(model, pageInfo);
		return "manager/dialog/dialog-list";
	}
}
