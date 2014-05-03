package com.kunsoftware.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.ProductResourceRequestBean;
import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.FlightCheduleMapper;
import com.kunsoftware.mapper.ProductResourceMapper;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.util.Constant;

@Service
public class ProductResourceService {

	private static Logger logger = LoggerFactory.getLogger(ProductResourceService.class);	
	
	@Autowired
	private ProductResourceMapper mapper;
	
	@Autowired
	private FlightCheduleMapper flightCheduleMapper;
	
	
	public List<ProductResource> getProductResourceListPage(ProductResourceRequestBean bean,PageInfo page) {
		 
		logger.info("query");
		return mapper.getProductResourceListPage(bean,page);
	}
	 
	@Transactional
	public ProductResource insert(ProductResourceRequestBean requestBean) throws KunSoftwareException {		 
		
		ProductResource record = new ProductResource();
		BeanUtils.copyProperties(requestBean, record);
		
		mapper.insert(record);
		return record;
	}
	
	public ProductResource selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(ProductResourceRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		ProductResource record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);
		if(record.getFlightId() == null) record.setFlightId(-1);
		 
		return mapper.updateByPrimaryKeySelective(record);
	}	 
	
	@Transactional
	public int deleteByPrimaryKey(Integer id) throws KunSoftwareException {
		return mapper.deleteByPrimaryKey(id);
	}
	
	@Transactional
	public void deleteByPrimaryKey(Integer[] id) throws KunSoftwareException {

		for(int i = 0;i < id.length;i++) {
			mapper.deleteByPrimaryKey(id[i]);
		} 
	}
	
	@Transactional
	public void createFlightChedule(Integer[] id) throws KunSoftwareException {

		ProductResource productResource = null;
		for(int i = 0;i < id.length;i++) {
			productResource = selectByPrimaryKey(id[i]);
			createFlightChedule(productResource);
		} 
	} 
	
	@Transactional
	public void createFlightChedule(ProductResource productResource) throws KunSoftwareException {

		Date flightCheduleStart = productResource.getFlightCheduleStart();
		Date flightCheduleEnd = productResource.getFlightCheduleEnd();
		 
		int count = subDate(flightCheduleStart,flightCheduleEnd);
		
		Date date = null;
		String dateStr = null;
		String week = null;
		FlightChedule flightChedule = null;
		for(int i = 0;i <= count;i++) {
			date = DateUtils.addDays(flightCheduleStart, i); 
			week = dayForWeek(date);
			if(!existsWeek(week,productResource.getWeek()))continue;
			
			dateStr = DateFormatUtils.format(date, "yyyy-MM-dd"); 
			flightChedule = flightCheduleMapper.selectByStartDate(productResource.getId(),dateStr);
			if(flightChedule == null) {
				flightChedule = new FlightChedule();
			}
			
			/** 有效或已审核的不更新 */
			if(Constant.FlightChedule_Valid.equals(flightChedule.getValid()) || Constant.FlightChedule_Audit.equals(flightChedule.getAudit())) {
				continue;
			}
			
			flightChedule.setProductResourceId(productResource.getId());
			flightChedule.setStartDate(date);			
			flightChedule.setLastReservationDate(DateUtils.addDays(date, -productResource.getEarlyDays()));
			
			if(flightChedule.getId() == null) {
				flightCheduleMapper.insert(flightChedule);
			} else {
				flightCheduleMapper.updateByPrimaryKeySelective(flightChedule);
			}
			
			logger.info("星期" + week);
			logger.info(dateStr);
		}
	}
	
	public static boolean existsWeek(String week,String resourceWeek) {
		
		if(StringUtils.isEmpty(week) || StringUtils.isEmpty(resourceWeek)) return false;
		
		if(StringUtils.indexOf(resourceWeek, week) != -1) return true;
		return false;
	}
	
    /**
     * 判断当前日期是星期几<br>
     * <br>
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
	public static String dayForWeek(Date date) throws KunSoftwareException {
 
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayForWeek = 0;
		if(c.get(Calendar.DAY_OF_WEEK) == 1){
			dayForWeek = 7;
		}else{
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek + "";
	}
	
	/**
	 * 使用结束日期减去开始日期,得到相差的天数
	 * @param date
	 * @return
	 */
	public static int subDate(Date starttime,Date endtime) {
		
	    if(starttime==null || endtime==null){
	        return 0;
	    }
	    
		long temp = endtime.getTime() - starttime.getTime();
		if (temp > 0) {
			return (int) (temp / (24 * 60 * 60 * 1000));
		} else {
			return (int) (temp / (24 * 60 * 60 * 1000)) - 1;
		}
	}
	
	@Transactional
	public int updateSomePraise(Integer id) throws KunSoftwareException {
	 
		return mapper.updateSomePraise(id);
	}
}
