package com.kunsoftware.mapper;

import com.kunsoftware.entity.FlightSegment;

public interface FlightSegmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlightSegment record);

    int insertSelective(FlightSegment record);

    FlightSegment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlightSegment record);

    int updateByPrimaryKey(FlightSegment record);
}