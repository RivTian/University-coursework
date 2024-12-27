package com.riotian.mplearn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.riotian.mplearn.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据年龄查询用户列表，分页展示
     * @param page 分页对象，xml中可以从里面进行取值，传递参数 page，即自动分页，必须放在第一位
     * @param age 年龄
     * @return
     */
    IPage<User> selectPageVo(@Param("page")Page<User> page, @Param("age") Integer age);

}
