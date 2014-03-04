package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.bean.ProductResourceRequestBean;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.page.PageInfo;

public interface ProductResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductResource record);

    int insertSelective(ProductResource record);

    ProductResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductResource record);

    int updateByPrimaryKeyWithBLOBs(ProductResource record);

    int updateByPrimaryKey(ProductResource record);
    
    List<ProductResource> getProductResourceListPage(@Param("bean") ProductResourceRequestBean bean,@Param("page") PageInfo page);
}