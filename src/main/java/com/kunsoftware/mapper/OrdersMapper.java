package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.bean.OrdersRequestBean;
import com.kunsoftware.entity.Orders;
import com.kunsoftware.page.PageInfo;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    
    List<Orders> getOrdersListPage(@Param("bean") OrdersRequestBean bean,@Param("page") PageInfo page);
}