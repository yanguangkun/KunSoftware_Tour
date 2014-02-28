package com.kunsoftware.mapper;

import com.kunsoftware.entity.Flight;

public interface FlightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Flight record);

    int insertSelective(Flight record);

    Flight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Flight record);

    int updateByPrimaryKey(Flight record);
}