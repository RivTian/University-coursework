package com.riotian.dao;

import com.riotian.pojo.Repair;

import java.io.Serializable;
import java.util.Map;

/**
 * Repair模块的DAO层（数据层）接口，提供增删改查等数据库操作的方法抽象
 */
public interface RepairDAO {

    /**
     * 增加用户 表记录
     *
     * @param vo
     */
    void add(Repair vo);

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
     */
    void update(Repair vo);

    /**
     * 根据主键id获取用户 表记录的详情
     *
     * @param id
     * @return
     */
    Repair get(Serializable id);

    /**
     *
     * @param params
     * @return
     */
    Map<String, Object> list(Map<String, Object> params);
}
