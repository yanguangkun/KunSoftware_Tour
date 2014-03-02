package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.Flight;
import com.kunsoftware.page.PageInfo;

public interface FlightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Flight record);

    int insertSelective(Flight record);

    Flight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Flight record);

    int updateByPrimaryKey(Flight record);
    
    List<Flight> getFlightListPage(@Param("arriveDestination") String arriveDestination,@Param("page") PageInfo page);
}