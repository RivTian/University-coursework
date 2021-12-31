package com.riotian.service.impl;

import com.riotian.dao.UserDAO;
import com.riotian.dao.impl.UserDAOImpl;
import com.riotian.pojo.User;
import com.riotian.service.UserService;

import java.io.Serializable;
import java.util.Map;

/**
 * User模块的Service层（业务层）的具体实现类，对UserService接口中定义的抽象方法作出具体的功能实现
 */
public class UserServiceImpl implements UserService {

    public void add(User vo) {
        UserDAO userDAO = new UserDAOImpl();
        userDAO.add(vo);
    }


    public void delete(long id) {
        UserDAO userDAO = new UserDAOImpl();
        userDAO.delete(id);
    }


    public void update(User vo) {
        UserDAO userDAO = new UserDAOImpl();
        userDAO.update(vo);
    }


    public User get(Serializable id) {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.get(id);
    }


    public Map<String, Object> list(Map<String, Object> params) {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.list(params);
    }
}
