package com.riotian.service.impl;

import com.riotian.dao.RepairDAO;
import com.riotian.dao.impl.RepairDAOImpl;
import com.riotian.pojo.Repair;
import com.riotian.service.RepairService;

import java.io.Serializable;
import java.util.Map;

/**
 * Repair模块的Service层（业务层）的具体实现类，对RepairService接口中定义的抽象方法作出具体的功能实现
 */
public class RepairServiceImpl implements RepairService {
    public void add(Repair vo) {
        RepairDAO repairDAO = new RepairDAOImpl();
        repairDAO.add(vo);
    }

    public void delete(long id) {
        RepairDAO repairDAO = new RepairDAOImpl();
        repairDAO.delete(id);
    }

    public void update(Repair vo) {
        RepairDAO repairDAO = new RepairDAOImpl();
        repairDAO.update(vo);
    }

    public Repair get(Serializable id) {
        RepairDAO repairDAO = new RepairDAOImpl();
        return repairDAO.get(id);
    }

    public Map<String, Object> list(Map<String, Object> params) {
        RepairDAO repairDAO = new RepairDAOImpl();
        return repairDAO.list(params);
    }
}
