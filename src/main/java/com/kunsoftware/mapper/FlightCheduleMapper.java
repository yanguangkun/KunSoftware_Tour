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
    
    List<FlightChedule> getFlightCheduleListPage(@Param("valid") String valid,
    		@Param("audit") String audit,
    		@Param("status") String status,
    		@Param("startDate") String startDate,
    		@Param("page") PageInfo page);
}