package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.OrdersDetail;

public interface OrdersDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrdersDetail record);

    int insertSelective(OrdersDetail record);

    OrdersDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrdersDetail record);

    int updateByPrimaryKey(OrdersDetail record);
    
    List<OrdersDetail> getOrdersDetailListAll(@Param("ordersId") Integer ordersId);
}