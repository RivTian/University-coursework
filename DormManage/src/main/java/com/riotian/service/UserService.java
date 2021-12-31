package com.riotian.service;

import com.riotian.pojo.User;

import java.io.Serializable;
import java.util.Map;

/**
 * User模块的Service层（业务层）接口，提供业务方法的抽象
 */
public interface UserService {
    /**
     * 增加用户
     *
     * @param vo
     * @return
     */
    void add(User vo);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    void delete(long id);

    /**
     * 修改用户
     *
     * @param vo
     * @return
     */
    void update(User vo);

    /**
     * 根据主键Id查询用户 详情
     *
     * @param id
     * @return
     */
    User get(Serializable id);

    /**
     * 根据条件查询用户 的列表与数量
     *
     * @param params
     * @return
     */
    Map<String, Object> list(Map<String, Object> params);
}
