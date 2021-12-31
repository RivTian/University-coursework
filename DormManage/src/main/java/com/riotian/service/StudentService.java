package com.riotian.service;

import com.riotian.pojo.Student;

import java.io.Serializable;
import java.util.Map;

/**
 * Student模块的Service层（业务层）接口，提供业务方法的抽象
 */
public interface StudentService {
    /**
     * 增加学生
     *
     * @param vo
     * @return
     */
    void add(Student vo);

    /**
     * 删除学生
     *
     * @param id
     * @return
     */
    void delete(long id);

    /**
     * 修改学生
     *
     * @param vo
     * @return
     */
    void update(Student vo);

    /**
     * 根据主键Id查询学生 详情
     *
     * @param id
     * @return
     */
    Student get(Serializable id);

    /**
     * 根据条件查询学生 的列表与数量
     *
     * @param params
     * @return
     */
    Map<String, Object> list(Map<String, Object> params);
}
