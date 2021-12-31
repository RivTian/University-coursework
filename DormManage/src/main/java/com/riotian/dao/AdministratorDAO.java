package com.riotian.dao;

import com.riotian.pojo.Administrator;

import java.io.Serializable;
import java.util.Map;

/**
 * Administrator模块的DAO层（数据层）接口，提供增删改查等数据库操作的方法抽象
 */
public interface AdministratorDAO {
    /**
     * 增加宿管员 表记录
     *
     * @param vo
     * @return
     */
    void add(Administrator vo);

    /**
     * 根据主键id，删除对应的宿管员 表记录
     *
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新宿管员 表记录
     *
     * @param vo
     * @return
     */
    void update(Administrator vo);

    /**
     * 根据主键id获取宿管员 表记录的详情
     *
     * @param id
     * @return
     */
    Administrator get(Serializable id);

    /**
     * 根据条件查询宿管员 的列表与数量
     *
     * @param params
     * @return
     */
    Map<String, Object> list(Map<String, Object> params);
}
