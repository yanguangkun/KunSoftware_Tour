package com.kunsoftware.mapper;

import com.kunsoftware.entity.ProductPlanTpl;

public interface ProductPlanTplMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductPlanTpl record);

    int insertSelective(ProductPlanTpl record);

    ProductPlanTpl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductPlanTpl record);

    int updateByPrimaryKeyWithBLOBs(ProductPlanTpl record);

    int updateByPrimaryKey(ProductPlanTpl record);
}