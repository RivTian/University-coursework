package com.riotian.dao;

import com.riotian.pojo.Notice;

import java.io.Serializable;
import java.util.Map;

/**
 * Notice模块的DAO层（数据层）接口，提供增删改查等数据库操作的方法抽象
 */
public interface NoticeDAO {
    /**
     * 增加公告 表记录
     *
     * @param vo
     * @return
     */
    void add(Notice vo);

    /**
     * 根据主键id，删除对应的公告 表记录
     *
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新公告 表记录
     *
     * @param vo
     * @return
     */
    void update(Notice vo);

    /**
     * 根据主键id获取公告 表记录的详情
     *
     * @param id
     * @return
     */
    Notice get(Serializable id);

    /**
     * 根据条件查询公告 的列表与数量
     *
     * @param params
     * @return
     */
    Map<String, Object> list(Map<String, Object> params);
}
