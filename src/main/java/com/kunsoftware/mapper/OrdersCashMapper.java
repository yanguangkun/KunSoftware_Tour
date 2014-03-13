package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.OrdersCash;

public interface OrdersCashMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrdersCash record);

    int insertSelective(OrdersCash record);

    OrdersCash selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrdersCash record);

    int updateByPrimaryKey(OrdersCash record);
    
    List<OrdersCash> getOrdersCashListAll(@Param("ordersId") Integer ordersId);
}