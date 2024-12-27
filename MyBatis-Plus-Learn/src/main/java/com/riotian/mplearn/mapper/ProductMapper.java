package com.riotian.mplearn.mapper;
import org.apache.ibatis.annotations.Param;

import com.riotian.mplearn.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 64129
* @description 针对表【t_product】的数据库操作Mapper
* @createDate 2023-02-20 10:41:38
* @Entity com.riotian.mplearn.entity.Product
*/
public interface ProductMapper extends BaseMapper<Product> {

    int insertSelective(Product product);

    int deleteByIdAndName(@Param("id") Long id, @Param("name") String name);
}




