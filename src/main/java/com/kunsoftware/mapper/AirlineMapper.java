package com.kunsoftware.mapper;

import java.util.List;

import com.kunsoftware.entity.Airline;
import com.kunsoftware.entity.ValueSet;

public interface AirlineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Airline record);

    int insertSelective(Airline record);

    Airline selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Airline record);

    int updateByPrimaryKey(Airline record);
    
    List<Airline> getAirlineListAll();
    
    List<ValueSet> selectValueSetList();
}