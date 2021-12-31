package com.riotian.service.impl;

import com.riotian.dao.DormitoryDAO;
import com.riotian.dao.impl.DormitoryDAOImpl;
import com.riotian.pojo.Dormitory;
import com.riotian.service.DormitoryService;

import java.io.Serializable;
import java.util.Map;

/**
 * Dormitory模块的Service层（业务层）的具体实现类，对DormitoryService接口中定义的抽象方法作出具体的功能实现
 */
public class DormitoryServiceImpl implements DormitoryService {

    public void add(Dormitory vo) {
        DormitoryDAO dormitoryDAO = new DormitoryDAOImpl();
        dormitoryDAO.add(vo);
    }


    public void delete(long id) {
        DormitoryDAO dormitoryDAO = new DormitoryDAOImpl();
        dormitoryDAO.delete(id);
    }


    public void update(Dormitory vo) {
        DormitoryDAO dormitoryDAO = new DormitoryDAOImpl();
        dormitoryDAO.update(vo);
    }


    public Dormitory get(Serializable id) {
        DormitoryDAO dormitoryDAO = new DormitoryDAOImpl();
        return dormitoryDAO.get(id);
    }


    public Map<String, Object> list(Map<String, Object> params) {
        DormitoryDAO dormitoryDAO = new DormitoryDAOImpl();
        return dormitoryDAO.list(params);
    }
}
