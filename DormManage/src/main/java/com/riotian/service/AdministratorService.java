package com.riotian.service;

import com.riotian.pojo.Administrator;

import java.io.Serializable;
import java.util.Map;

/**
 * Administrator模块的Service层（业务层）接口，提供业务方法的抽象
 */
public interface AdministratorService {
    /**
     * 增加宿管员
     *
     * @param vo
     * @return
     */
    void add(Administrator vo);

    /**
     * 删除宿管员
     *
     * @param id
     * @return
     */
    void delete(long id);

    /**
     * 修改宿管员
     *
     * @param vo
     * @return
     */
    void update(Administrator vo);

    /**
     * 根据主键Id查询宿管员 详情
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
