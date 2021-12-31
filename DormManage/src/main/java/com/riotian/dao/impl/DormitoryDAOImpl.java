package com.riotian.dao.impl;

import com.riotian.dao.DormitoryDAO;
import com.riotian.pojo.Dormitory;
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
 * Dormitory模块的DAO层（数据层）的具体实现类，对DormitoryDAO接口中定义的增删改查等抽象方法作出具体的功能实现
 */
public class DormitoryDAOImpl implements DormitoryDAO {

    public void add(Dormitory vo) {
        String sql = "insert into `t_dormitory` (`dormitory_name`,`dormitory_building`,`dormitory_bedcount`,`dormitory_administrator`,`dormitory_text`) values(?,?,?,?,?)";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, vo.getDormitoryName());
            ps.setString(2, vo.getDormitoryBuilding());
            ps.setString(3, vo.getDormitoryBedcount());
            ps.setString(4, vo.getDormitoryAdministrator());
            ps.setString(5, vo.getDormitoryText());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Dormitory vo) {
        String sql = "update `t_dormitory` set `dormitory_name` = ? ,`dormitory_building` = ? ,`dormitory_bedcount` = ? ,`dormitory_administrator` = ? ,`dormitory_text` = ?  where `id` = ?";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, vo.getDormitoryName());
            ps.setString(2, vo.getDormitoryBuilding());
            ps.setString(3, vo.getDormitoryBedcount());
            ps.setString(4, vo.getDormitoryAdministrator());
            ps.setString(5, vo.getDormitoryText());
            ps.setLong(6, vo.getId());
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
            String sql = "delete from `t_dormitory` where id = " + id;
            s.execute(sql);
            s.close();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Dormitory get(Serializable id) {
        Dormitory vo = null;
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "select * from `t_dormitory` where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                vo = new Dormitory();
                vo.setId(rs.getLong("id"));
                vo.setDormitoryName(rs.getString("dormitory_name"));
                vo.setDormitoryBuilding(rs.getString("dormitory_building"));
                vo.setDormitoryBedcount(rs.getString("dormitory_bedcount"));
                vo.setDormitoryAdministrator(rs.getString("dormitory_administrator"));
                vo.setDormitoryText(rs.getString("dormitory_text"));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    public Map<String, Object> list(Map<String, Object> params) {
        List<Dormitory> list = new ArrayList();
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
            sqlList = "select * from `t_dormitory` where 1=1 " + condition + " order by id asc " + limit + ";";
            ps = c.prepareStatement(sqlList);
            rs = ps.executeQuery();
            while (rs.next()) {
                Dormitory vo = new Dormitory();
                vo.setId(rs.getLong("id"));
                vo.setDormitoryName(rs.getString("dormitory_name"));
                vo.setDormitoryBuilding(rs.getString("dormitory_building"));
                vo.setDormitoryBedcount(rs.getString("dormitory_bedcount"));
                vo.setDormitoryAdministrator(rs.getString("dormitory_administrator"));
                vo.setDormitoryText(rs.getString("dormitory_text"));
                list.add(vo);
            }
            String sqlCount = "select count(*) from `t_dormitory` where 1=1 " + condition;
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
