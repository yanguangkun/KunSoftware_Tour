package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.OrdersTravelList;

public interface OrdersTravelListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrdersTravelList record);

    int insertSelective(OrdersTravelList record);

    OrdersTravelList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrdersTravelList record);

    int updateByPrimaryKey(OrdersTravelList record);
    
    List<OrdersTravelList> getOrdersTravelListAll(@Param("ordersId") Integer ordersId);
}