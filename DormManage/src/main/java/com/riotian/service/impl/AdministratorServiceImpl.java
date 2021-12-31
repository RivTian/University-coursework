package com.riotian.service.impl;

import com.riotian.dao.AdministratorDAO;
import com.riotian.dao.impl.AdministratorDAOImpl;
import com.riotian.pojo.Administrator;
import com.riotian.service.AdministratorService;

import java.io.Serializable;
import java.util.Map;

/**
 * Administrator模块的Service层（业务层）的具体实现类，对AdministratorService接口中定义的抽象方法作出具体的功能实现
 */
public class AdministratorServiceImpl implements AdministratorService {

    public void add(Administrator vo) {
        AdministratorDAO administratorDAO = new AdministratorDAOImpl();
        administratorDAO.add(vo);
    }


    public void delete(long id) {
        AdministratorDAO administratorDAO = new AdministratorDAOImpl();
        administratorDAO.delete(id);
    }


    public void update(Administrator vo) {
        AdministratorDAO administratorDAO = new AdministratorDAOImpl();
        administratorDAO.update(vo);
    }


    public Administrator get(Serializable id) {
        AdministratorDAO administratorDAO = new AdministratorDAOImpl();
        return administratorDAO.get(id);
    }


    public Map<String, Object> list(Map<String, Object> params) {
        AdministratorDAO administratorDAO = new AdministratorDAOImpl();
        return administratorDAO.list(params);
    }
}
