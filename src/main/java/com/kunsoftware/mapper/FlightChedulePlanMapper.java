package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.FlightChedulePlan;

public interface FlightChedulePlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlightChedulePlan record);

    int insertSelective(FlightChedulePlan record);

    FlightChedulePlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlightChedulePlan record);

    int updateByPrimaryKeyWithBLOBs(FlightChedulePlan record);

    int updateByPrimaryKey(FlightChedulePlan record);
    
    List<FlightChedulePlan> getFlightChedulePlanListAll(@Param("flightCheduleId") Integer flightCheduleId);
    
    FlightChedulePlan selectByFlightCheduleId(@Param("flightCheduleId") Integer flightCheduleId,@Param("productPlanTplId") Integer productPlanTplId);
}