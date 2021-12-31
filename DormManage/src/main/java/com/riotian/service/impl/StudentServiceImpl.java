package com.riotian.service.impl;

import com.riotian.dao.StudentDAO;
import com.riotian.dao.impl.StudentDAOImpl;
import com.riotian.pojo.Student;
import com.riotian.service.StudentService;

import java.io.Serializable;
import java.util.Map;

/**
 * Student模块的Service层（业务层）的具体实现类，对StudentService接口中定义的抽象方法作出具体的功能实现
 */
public class StudentServiceImpl implements StudentService {

    public void add(Student vo) {
        StudentDAO studentDAO = new StudentDAOImpl();
        studentDAO.add(vo);
    }


    public void delete(long id) {
        StudentDAO studentDAO = new StudentDAOImpl();
        studentDAO.delete(id);
    }


    public void update(Student vo) {
        StudentDAO studentDAO = new StudentDAOImpl();
        studentDAO.update(vo);
    }


    public Student get(Serializable id) {
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.get(id);
    }


    public Map<String, Object> list(Map<String, Object> params) {
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.list(params);
    }
}
