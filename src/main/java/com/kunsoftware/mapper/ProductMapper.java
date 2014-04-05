package com.kunsoftware.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.bean.ProductResourceRequestBean;
import com.kunsoftware.entity.Product;
import com.kunsoftware.entity.ValueSet;
import com.kunsoftware.page.PageInfo;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    List<Product> getProductListPage(@Param("arriveDestination") String arriveDestination,
								    		 @Param("type") String type,
								    		 @Param("page") PageInfo page);
    
    List<ValueSet> getValueSetListByType(@Param("type") String type);
    
    List<HashMap> getProductResourceListPage(@Param("bean") ProductResourceRequestBean bean,@Param("page") PageInfo page);
}