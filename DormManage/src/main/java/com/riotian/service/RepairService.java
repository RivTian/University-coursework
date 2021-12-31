package com.riotian.service;

import com.riotian.pojo.Repair;

import java.io.Serializable;
import java.util.Map;

/**
 * Repair模块的Service层（业务层）接口，提供业务方法的抽象
 */
public interface RepairService {
    /**
     * 增加维修单
     *
     * @param vo
     * @return
     */
    void add(Repair vo);

    /**
     * 删除维修单
     *
     * @param id
     * @return
     */
    void delete(long id);

    /**
     * 修改维修单
     *
     * @param vo
     * @return
     */
    void update(Repair vo);


    /**
     * 根据主键Id查询维修单 详情
     *
     * @param id
     * @return
     */
    Repair get(Serializable id);

    /**
     * 根据条件查询维修单 的列表与数量
     *
     * @param params
     * @return
     */
    Map<String, Object> list(Map<String, Object> params);
}
