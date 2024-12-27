package com.riotian.mplearn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riotian.mplearn.entity.Product;
import com.riotian.mplearn.service.ProductService;
import com.riotian.mplearn.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author 64129
* @description 针对表【t_product】的数据库操作Service实现
* @createDate 2023-02-20 10:41:38
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}




