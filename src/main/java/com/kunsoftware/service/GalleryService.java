package com.kunsoftware.service;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.GalleryRequestBean;
import com.kunsoftware.entity.Gallery;
import com.kunsoftware.entity.GalleryDetail;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.GalleryDetailMapper;
import com.kunsoftware.mapper.GalleryMapper;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.util.FileUtil;

@Service
public class GalleryService {

private static Logger logger = LoggerFactory.getLogger(GalleryService.class);	
	
	@Autowired
	private GalleryMapper mapper;	
	
	@Autowired
	private GalleryDetailMapper galleryDetailMapper;
	
	public List<Gallery> getGalleryListPage(String keyword,PageInfo page) {
		 
		logger.info("query");
		return mapper.getGalleryListPage(keyword,page);
	}
	 
	@Transactional
	public Gallery insert(GalleryRequestBean requestBean) throws KunSoftwareException {
		 
		Gallery record = new Gallery();
		BeanUtils.copyProperties(requestBean, record); 
		mapper.insert(record); 
		
		GalleryDetail detail = null;
		int j = 0;
		for(int i = 0;i < requestBean.getOrderValue().length;i++) {
			detail = new GalleryDetail();
			if("1".equals(requestBean.getIsFile()[i])) {
				String imagePath = FileUtil.uploadFile(requestBean.getImageFile().get(j++));
				detail.setImagePath(imagePath);
			}
			
			detail.setGalleryId(record.getId());
			detail.setOrderValue(NumberUtils.toInt(requestBean.getOrderValue()[i]));
			detail.setTitle(requestBean.getTitle()[i]);
			galleryDetailMapper.insert(detail);
		}
		 
		return record;
	}
	
	public Gallery selectByPrimaryKey(Integer id) {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(GalleryRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Gallery record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);
		
		/** 先删除后添加 */
		galleryDetailMapper.deleteByGalleryId(id);
		
		GalleryDetail detail = null;
		int j = 0;
		for(int i = 0;i < requestBean.getOrderValue().length;i++) {
			detail = new GalleryDetail();
			if("1".equals(requestBean.getIsFile()[i])) {
				String imagePath = FileUtil.uploadFile(requestBean.getImageFile().get(j++));
				detail.setImagePath(imagePath);
			} else {
				detail.setImagePath(requestBean.getImagePaths()[i]);
			}
			
			detail.setGalleryId(record.getId());
			detail.setOrderValue(NumberUtils.toInt(requestBean.getOrderValue()[i]));
			detail.setTitle(requestBean.getTitle()[i]);
			galleryDetailMapper.insert(detail);
		}
		
		return mapper.updateByPrimaryKeySelective(record);
	}
	
	@Transactional
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	@Transactional
	public void deleteByPrimaryKey(Integer[] id) throws KunSoftwareException {

		for(int i = 0;i < id.length;i++) {
			mapper.deleteByPrimaryKey(id[i]);
		} 
	}
	
	public List<GalleryDetail> getGalleryDetailListAll(Integer galleryId) {
		 
		return galleryDetailMapper.getGalleryDetailListAll(galleryId);
	}
}
