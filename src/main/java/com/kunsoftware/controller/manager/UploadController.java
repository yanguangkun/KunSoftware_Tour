package com.kunsoftware.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kunsoftware.controller.BaseController;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.util.FileUtil;

@Controller 
@RequestMapping("/manager")
public class UploadController extends BaseController {

	@RequestMapping(value = "/imageUpload") 
	public String imageUpload(ModelMap model,@RequestParam(value = "upload", required = false) MultipartFile file,String CKEditorFuncNum) throws KunSoftwareException {
		
		String imagePath = "";
		if(file != null) {
			imagePath = FileUtil.uploadFile(file);			
		}
		model.addAttribute("imagePath", imagePath);
		model.addAttribute("CKEditorFuncNum", CKEditorFuncNum);
		return "manager/imageUpload";
	}
	
}
