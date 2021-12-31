package com.riotian.dao.impl;

import com.riotian.dao.StudentDAO;
import com.riotian.pojo.Student;
import com.riotian.util.Util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Student模块的DAO层（数据层）的具体实现类，对StudentDAO接口中定义的增删改查等抽象方法作出具体的功能实现
 */
public class StudentDAOImpl implements StudentDAO {


    public void add(Student vo) {
        String sql = "insert into `t_student` (`student_name`,`student_sex`,`student_no`,`student_building`,`student_dormitory`,`student_phone`,`student_text`) values(?,?,?,?,?,?,?)";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, vo.getStudentName());
            ps.setString(2, vo.getStudentSex());
            ps.setString(3, vo.getStudentNo());
            ps.setString(4, vo.getStudentBuilding());
            ps.setString(5, vo.getStudentDormitory());
            ps.setString(6, vo.getStudentPhone());
            ps.setString(7, vo.getStudentText());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(Student vo) {
        String sql = "update `t_student` set `student_name` = ? ,`student_sex` = ? ,`student_no` = ? ,`student_building` = ? ,`student_dormitory` = ? ,`student_phone` = ? ,`student_text` = ?  where `id` = ?";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, vo.getStudentName());
            ps.setString(2, vo.getStudentSex());
            ps.setString(3, vo.getStudentNo());
            ps.setString(4, vo.getStudentBuilding());
            ps.setString(5, vo.getStudentDormitory());
            ps.setString(6, vo.getStudentPhone());
            ps.setString(7, vo.getStudentText());
            ps.setLong(8, vo.getId());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean delete(long id) {
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "delete from `t_student` where id = " + id;
            s.execute(sql);
            s.close();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public Student get(Serializable id) {
        Student vo = null;
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "select * from `t_student` where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                vo = new Student();
                vo.setId(rs.getLong("id"));
                vo.setStudentName(rs.getString("student_name"));
                vo.setStudentSex(rs.getString("student_sex"));
                vo.setStudentNo(rs.getString("student_no"));
                vo.setStudentBuilding(rs.getString("student_building"));
                vo.setStudentDormitory(rs.getString("student_dormitory"));
                vo.setStudentPhone(rs.getString("student_phone"));
                vo.setStudentText(rs.getString("student_text"));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }


    public Map<String, Object> list(Map<String, Object> params) {
        List<Student> list = new ArrayList();
        int totalCount = 0;
        String condition = "";
        String sqlList;
        if (params.get("searchColumn") != null && !"".equals(params.get("searchColumn"))) {
            condition += " and `" + params.get("searchColumn") + "` like '%" + params.get("keyword") + "%'";
        }
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps;
            ResultSet rs;
            String limit = (params.get("startIndex") != null && params.get("pageSize") != null) ? " limit " + params.get("startIndex") + "," + params.get("pageSize") : "";
            sqlList = "select * from `t_student` where 1=1 " + condition + " order by id asc " + limit + ";";
            ps = c.prepareStatement(sqlList);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student vo = new Student();
                vo.setId(rs.getLong("id"));
                vo.setStudentName(rs.getString("student_name"));
                vo.setStudentSex(rs.getString("student_sex"));
                vo.setStudentNo(rs.getString("student_no"));
                vo.setStudentBuilding(rs.getString("student_building"));
                vo.setStudentDormitory(rs.getString("student_dormitory"));
                vo.setStudentPhone(rs.getString("student_phone"));
                vo.setStudentText(rs.getString("student_text"));
                list.add(vo);
            }
            String sqlCount = "select count(*) from `t_student` where 1=1 " + condition;
            ps = c.prepareStatement(sqlCount);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap();
        result.put("list", list);
        result.put("totalCount", totalCount);
        return result;
    }
}
