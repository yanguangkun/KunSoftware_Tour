package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.OrdersStatus;

public interface OrdersStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrdersStatus record);

    int insertSelective(OrdersStatus record);

    OrdersStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrdersStatus record);

    int updateByPrimaryKey(OrdersStatus record);
    
    List<OrdersStatus> getOrdersStatusListAll(@Param("ordersId") Integer ordersId);
}