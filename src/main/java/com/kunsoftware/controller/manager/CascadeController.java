package com.kunsoftware.controller.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.CascadeService;

@Controller 
@RequestMapping("/manager/cascade")
public class CascadeController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(CascadeController.class);	
	
	@Autowired
	private CascadeService service; 

	@RequestMapping(value="/list.json")
	@ResponseBody 
	public JsonBean listCascade(ModelMap model,String cascadeId,String cascadeCode,String cascadeValue,String defaultValue) throws KunSoftwareException {
		 
		logger.info("级联!");  
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("cascadeId", cascadeId);
		jsonBean.put("cascadeCode", cascadeCode);
		jsonBean.put("result", service.getSelectResult(cascadeCode,cascadeValue, defaultValue));
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
}
