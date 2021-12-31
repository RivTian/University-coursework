package com.riotian.service;

import com.riotian.pojo.Dormitory;

import java.io.Serializable;
import java.util.Map;

/**
 * Dormitory模块的Service层（业务层）接口，提供业务方法的抽象
 */
public interface DormitoryService {
    /**
     * 增加宿舍
     *
     * @param vo
     * @return
     */
    void add(Dormitory vo);

    /**
     * 删除宿舍
     *
     * @param id
     * @return
     */
    void delete(long id);

    /**
     * 修改宿舍
     *
     * @param vo
     * @return
     */
    void update(Dormitory vo);

    /**
     * 根据主键Id查询宿舍 详情
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
