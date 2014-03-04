package com.kunsoftware.mapper;

import com.kunsoftware.entity.FlightChedule;

public interface FlightCheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlightChedule record);

    int insertSelective(FlightChedule record);

    FlightChedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlightChedule record);

    int updateByPrimaryKey(FlightChedule record);
}