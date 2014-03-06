package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.ProductPlanTpl;

public interface ProductPlanTplMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductPlanTpl record);

    int insertSelective(ProductPlanTpl record);

    ProductPlanTpl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductPlanTpl record);

    int updateByPrimaryKeyWithBLOBs(ProductPlanTpl record);

    int updateByPrimaryKey(ProductPlanTpl record);
    
    List<ProductPlanTpl> getProductPlanTplListAll(@Param("productResourceId") Integer productResourceId);
}