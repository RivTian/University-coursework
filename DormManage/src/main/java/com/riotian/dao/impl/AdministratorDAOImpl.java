package com.riotian.dao.impl;

import com.riotian.dao.AdministratorDAO;
import com.riotian.pojo.Administrator;
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
 * Administrator模块的DAO层（数据层）的具体实现类，对AdministratorDAO接口中定义的增删改查等抽象方法作出具体的功能实现
 */
public class AdministratorDAOImpl implements AdministratorDAO {

    public void add(Administrator vo) {
        String sql = "insert into `t_administrator` (`administrator_name`,`administrator_sex`,`administrator_phone`,`administrator_building`,`administrator_text`) values(?,?,?,?,?)";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, vo.getAdministratorName());
            ps.setString(2, vo.getAdministratorSex());
            ps.setString(3, vo.getAdministratorPhone());
            ps.setString(4, vo.getAdministratorBuilding());
            ps.setString(5, vo.getAdministratorText());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Administrator vo) {
        String sql = "update `t_administrator` set `administrator_name` = ? ,`administrator_sex` = ? ,`administrator_phone` = ? ,`administrator_building` = ? ,`administrator_text` = ?  where `id` = ?";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, vo.getAdministratorName());
            ps.setString(2, vo.getAdministratorSex());
            ps.setString(3, vo.getAdministratorPhone());
            ps.setString(4, vo.getAdministratorBuilding());
            ps.setString(5, vo.getAdministratorText());
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
            String sql = "delete from `t_administrator` where id = " + id;
            s.execute(sql);
            s.close();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Administrator get(Serializable id) {
        Administrator vo = null;
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "select * from `t_administrator` where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                vo = new Administrator();
                vo.setId(rs.getLong("id"));
                vo.setAdministratorName(rs.getString("administrator_name"));
                vo.setAdministratorSex(rs.getString("administrator_sex"));
                vo.setAdministratorPhone(rs.getString("administrator_phone"));
                vo.setAdministratorBuilding(rs.getString("administrator_building"));
                vo.setAdministratorText(rs.getString("administrator_text"));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    public Map<String, Object> list(Map<String, Object> params) {
        List<Administrator> list = new ArrayList();
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
            sqlList = "select * from `t_administrator` where 1=1 " + condition + " order by id asc " + limit + ";";
            ps = c.prepareStatement(sqlList);
            rs = ps.executeQuery();
            while (rs.next()) {
                Administrator vo = new Administrator();
                vo.setId(rs.getLong("id"));
                vo.setAdministratorName(rs.getString("administrator_name"));
                vo.setAdministratorSex(rs.getString("administrator_sex"));
                vo.setAdministratorPhone(rs.getString("administrator_phone"));
                vo.setAdministratorBuilding(rs.getString("administrator_building"));
                vo.setAdministratorText(rs.getString("administrator_text"));
                list.add(vo);
            }
            String sqlCount = "select count(*) from `t_administrator` where 1=1 " + condition;
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
