package com.kunsoftware.mapper;

import com.kunsoftware.entity.FlightChedulePlan;

public interface FlightChedulePlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlightChedulePlan record);

    int insertSelective(FlightChedulePlan record);

    FlightChedulePlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlightChedulePlan record);

    int updateByPrimaryKeyWithBLOBs(FlightChedulePlan record);

    int updateByPrimaryKey(FlightChedulePlan record);
}