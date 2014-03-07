package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.FlightChedulePrice;

public interface FlightChedulePriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlightChedulePrice record);

    int insertSelective(FlightChedulePrice record);

    FlightChedulePrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlightChedulePrice record);

    int updateByPrimaryKeyWithBLOBs(FlightChedulePrice record);

    int updateByPrimaryKey(FlightChedulePrice record);
    
    List<FlightChedulePrice> getFlightChedulePriceListAll(@Param("flightCheduleId") Integer flightCheduleId);
    
    FlightChedulePrice selectByFlightCheduleId(@Param("flightCheduleId") Integer flightCheduleId,@Param("productPriceTplId") Integer productPriceTplId);
}