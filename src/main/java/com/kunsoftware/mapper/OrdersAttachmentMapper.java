package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.OrdersAttachment;

public interface OrdersAttachmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrdersAttachment record);

    int insertSelective(OrdersAttachment record);

    OrdersAttachment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrdersAttachment record);

    int updateByPrimaryKey(OrdersAttachment record);
    
    List<OrdersAttachment> getOrdersAttachmentListAll(@Param("ordersId") Integer ordersId,@Param("frontDesk") String frontDesk);
}