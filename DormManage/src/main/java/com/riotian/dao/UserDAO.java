package com.riotian.dao;

import com.riotian.pojo.User;

import java.io.Serializable;
import java.util.Map;

/**
 * User模块的DAO层（数据层）接口，提供增删改查等数据库操作的方法抽象
 */
public interface UserDAO {
    /**
     * 增加用户 表记录
     *
     * @param vo
     * @return
     */
    void add(User vo);

    /**
     * 根据主键id，删除对应的用户 表记录
     *
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新用户 表记录
     *
     * @param vo
     * @return
     */
    void update(User vo);

    /**
     * 根据主键id获取用户 表记录的详情
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
