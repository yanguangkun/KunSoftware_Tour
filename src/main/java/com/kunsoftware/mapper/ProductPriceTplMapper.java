package com.kunsoftware.mapper;

import com.kunsoftware.entity.ProductPriceTpl;

public interface ProductPriceTplMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductPriceTpl record);

    int insertSelective(ProductPriceTpl record);

    ProductPriceTpl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductPriceTpl record);

    int updateByPrimaryKeyWithBLOBs(ProductPriceTpl record);

    int updateByPrimaryKey(ProductPriceTpl record);
}