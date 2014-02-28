package com.kunsoftware.util;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.kunsoftware.exception.KunSoftwareException;

public class FileUtil {

	public static String uploadDir = "E:/project/prj_tour/webapp/images/uploadDir";
	public static String separator = "/";
	
	public static String uploadFile(MultipartFile file) throws KunSoftwareException {
		
		CommonsMultipartFile cFile = (CommonsMultipartFile)file;
		String ext = FilenameUtils.getExtension(cFile.getFileItem().getName());
		
		String dir = uploadDir + separator + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		
		File dirFile = new File(dir);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String fileName =  getUUID() + "." + ext;
		
		File destFile = new File(dir + separator + fileName);
		try {
			file.transferTo(destFile);
		} catch(Exception e) {
			throw new KunSoftwareException(e);
		}
		
		String filePath = destFile.getAbsolutePath();
		filePath = StringUtils.replace(filePath, "\\",separator);
		return StringUtils.replace(filePath,uploadDir,"");
	}
	
	public static String getUUID() throws KunSoftwareException {
		
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}	
}
