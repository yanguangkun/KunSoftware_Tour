package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.ProductIntroduce;

public interface ProductIntroduceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductIntroduce record);

    int insertSelective(ProductIntroduce record);

    ProductIntroduce selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductIntroduce record);

    int updateByPrimaryKeyWithBLOBs(ProductIntroduce record);

    int updateByPrimaryKey(ProductIntroduce record);
    
    List<ProductIntroduce> getProductIntroduceListAll(@Param("productId") Integer productId); 
}