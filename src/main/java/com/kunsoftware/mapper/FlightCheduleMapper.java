package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.page.PageInfo;

public interface FlightCheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlightChedule record);

    int insertSelective(FlightChedule record);

    FlightChedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlightChedule record);

    int updateByPrimaryKey(FlightChedule record);
    
    FlightChedule selectByStartDate(@Param("productResourceId")Integer productResourceId,@Param("startDate")String startDate);
    
    List<FlightChedule> getFlightCheduleListPage(@Param("productResourceId")Integer productResourceId,
    		@Param("valid") String valid,
    		@Param("audit") String audit,
    		@Param("status") String status,
    		@Param("startDate") String startDate,
    		@Param("page") PageInfo page);
    
    List<FlightChedule> selectAuditFlightChedule(@Param("productResourceId")Integer productResourceId);
    
    int selectPlanCount(@Param("flightCheduleId")Integer flightCheduleId);
    int selectPriceCount(@Param("flightCheduleId")Integer flightCheduleId);
    
    
    List getFlightCheduleListAll(@Param("productResourceId")Integer productResourceId,@Param("startMonth")String startMonth);
    
    FlightChedule selectByResource(@Param("productResourceId")Integer productResourceId,
    		@Param("startDate")String startDate);
}