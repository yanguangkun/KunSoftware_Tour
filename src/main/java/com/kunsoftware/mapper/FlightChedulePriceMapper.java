package com.kunsoftware.mapper;

import com.kunsoftware.entity.FlightChedulePrice;

public interface FlightChedulePriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlightChedulePrice record);

    int insertSelective(FlightChedulePrice record);

    FlightChedulePrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlightChedulePrice record);

    int updateByPrimaryKeyWithBLOBs(FlightChedulePrice record);

    int updateByPrimaryKey(FlightChedulePrice record);
}