package com.riotian.dao.impl;

import com.riotian.dao.RepairDAO;
import com.riotian.pojo.Repair;
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
 * Repair模块的DAO层（数据层）的具体实现类，对RepairDAO接口中定义的增删改查等抽象方法作出具体的功能实现
 */
public class RepairDAOImpl implements RepairDAO {
    public void add(Repair vo) {
        // todo: 补全 sql 语句
        String sql = "insert into `t_repair` (`repair_name`,`repair_dorm`,`repair_time`,`repair_text`,`repair_bool`) values(?,?,?,?,?)";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, vo.getRepairName());
            ps.setString(2, vo.getRepairdorm());
            ps.setString(3, vo.getCreateDate());
            ps.setString(4, vo.getRepairText());
            ps.setString(5, vo.getRepairbool());

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
            String sql = "delete from `t_repair` where id = " + id;
            s.execute(sql);
            s.close();
            s.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void update(Repair vo) {
        // 用户和管理员的更新操作
        // todo: 补全 sql 语句
        String sql = "update `t_repair` set `repair_name` = ? ,`repair_dorm` = ? ,`repair_time` = ? ,`repair_text` = ? ,`repair_bool` = ?  where `id` = ?";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, vo.getRepairName());
            ps.setString(2, vo.getRepairdorm());
            ps.setString(3, vo.getCreateDate());
            ps.setString(4, vo.getRepairText());
            ps.setString(5, vo.getRepairbool());
            ps.setLong(6, vo.getId());

            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Repair get(Serializable id) {
        Repair vo = null;
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();

            String sql = "select * from `t_repair` where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                vo = new Repair();
                //String sql = "insert into `t_repair` (`repair_name`,`repair_dorm`,`repair_time`,`repair_text`,`repair_bool`) values(?,?,?,?,?)";
                vo.setId(rs.getLong("id"));
                vo.setRepairName("repair_name");
                vo.setRepairdorm("repair_dorm");
                vo.setCreateDate("repair_time");
                vo.setRepairText("repair_text");
                vo.setRepairbool("repair_bool");
                ;
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    public Map<String, Object> list(Map<String, Object> params) {
        List<Repair> list = new ArrayList();
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
            sqlList = "select * from `t_repair` where 1=1 " + condition + " order by id asc " + limit + ";";
            ps = c.prepareStatement(sqlList);
            rs = ps.executeQuery();
            while (rs.next()) {
                Repair vo = new Repair();
                vo.setId(rs.getLong("id"));
                vo.setRepairName(rs.getString("repair_name"));
                vo.setRepairdorm(rs.getString("repair_dorm"));
                vo.setCreateDate(rs.getString("repair_time"));
                vo.setRepairText(rs.getString("repair_Text"));
                vo.setRepairbool(rs.getString("repair_bool"));
                list.add(vo);
            }
            String sqlCount = "select count(*) from `t_repair` where 1=1 " + condition;
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
