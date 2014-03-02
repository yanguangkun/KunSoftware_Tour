package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.FlightSegment;

public interface FlightSegmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlightSegment record);

    int insertSelective(FlightSegment record);

    FlightSegment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlightSegment record);

    int updateByPrimaryKey(FlightSegment record);
    
    List<FlightSegment> getFlightSegmentListAll(@Param("flightId") Integer flightId);
}