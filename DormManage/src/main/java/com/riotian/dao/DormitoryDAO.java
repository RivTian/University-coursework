package com.riotian.dao;

import com.riotian.pojo.Dormitory;

import java.io.Serializable;
import java.util.Map;

/**
 * Dormitory模块的DAO层（数据层）接口，提供增删改查等数据库操作的方法抽象
 */
public interface DormitoryDAO {
    /**
     * 增加宿舍 表记录
     *
     * @param vo
     * @return
     */
    void add(Dormitory vo);

    /**
     * 根据主键id，删除对应的宿舍 表记录
     *
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新宿舍 表记录
     *
     * @param vo
     * @return
     */
    void update(Dormitory vo);

    /**
     * 根据主键id获取宿舍 表记录的详情
     *
     * @param id
     * @return
     */
    Dormitory get(Serializable id);

    /**
     * 根据条件查询宿舍 的列表与数量
     *
     * @param params
     * @return
     */
    Map<String, Object> list(Map<String, Object> params);
}
