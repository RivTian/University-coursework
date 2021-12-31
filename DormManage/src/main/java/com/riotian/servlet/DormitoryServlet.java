package com.riotian.servlet;

import com.riotian.pojo.Dormitory;
import com.riotian.service.DormitoryService;
import com.riotian.service.impl.DormitoryServiceImpl;
import com.riotian.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dormitory模块的Servlet控制层，负责接收页面传过来的请求参数，根据action参数的值来确定页面要执行的具体操作<br>
 * 而后再调用DormitoryService业务层的方法来处理具体的业务，最后将处理完成的结果返回或跳转至相应页面
 */
//@WebServlet("/DormitoryServlet")
public class DormitoryServlet extends HttpServlet {

    /**
     * 处理Post请求
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //过滤编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = Util.decode(request, "action");
        if ("add".equals(action)) {//增加
            Dormitory vo = new Dormitory();
            //取出页面传进来的各个数据，并设置到Dormitory对象的属性里
            String dormitoryName = Util.decode(request, "dormitoryName");
            if (dormitoryName != null && !dormitoryName.equals("")) {
                vo.setDormitoryName(dormitoryName);
            }
            String dormitoryBuilding = Util.decode(request, "dormitoryBuilding");
            if (dormitoryBuilding != null && !dormitoryBuilding.equals("")) {
                vo.setDormitoryBuilding(dormitoryBuilding);
            }
            String dormitoryBedcount = Util.decode(request, "dormitoryBedcount");
            if (dormitoryBedcount != null && !dormitoryBedcount.equals("")) {
                vo.setDormitoryBedcount(dormitoryBedcount);
            }
            String dormitoryAdministrator = Util.decode(request, "dormitoryAdministrator");
            if (dormitoryAdministrator != null && !dormitoryAdministrator.equals("")) {
                vo.setDormitoryAdministrator(dormitoryAdministrator);
            }
            String dormitoryText = Util.decode(request, "dormitoryText");
            if (dormitoryText != null && !dormitoryText.equals("")) {
                vo.setDormitoryText(dormitoryText);
            }
            DormitoryService dormitoryService = new DormitoryServiceImpl();
            //调用Service层增加方法（add），增加记录
            dormitoryService.add(vo);
            this.redirectList(request, response);
        } else if ("delete".equals(action)) {//删除
            //取出表要删除的宿舍 记录的主键
            long id = Long.parseLong(Util.decode(request, "id"));
            DormitoryService dormitoryService = new DormitoryServiceImpl();
            //调用Service层删除方法（delete），将对应的记录删除
            dormitoryService.delete(id);
            this.redirectList(request, response);
        } else if ("edit".equals(action)) {//修改
            //取出页面传进来的各个数据，并设置到Dormitory对象的属性里
            Dormitory vo = new Dormitory();
            String id = Util.decode(request, "id");
            if (id != null && !id.equals("")) {
                vo.setId(Long.valueOf(id));
            }
            String dormitoryName = Util.decode(request, "dormitoryName");
            if (dormitoryName != null && !dormitoryName.equals("")) {
                vo.setDormitoryName(dormitoryName);
            }
            String dormitoryBuilding = Util.decode(request, "dormitoryBuilding");
            if (dormitoryBuilding != null && !dormitoryBuilding.equals("")) {
                vo.setDormitoryBuilding(dormitoryBuilding);
            }
            String dormitoryBedcount = Util.decode(request, "dormitoryBedcount");
            if (dormitoryBedcount != null && !dormitoryBedcount.equals("")) {
                vo.setDormitoryBedcount(dormitoryBedcount);
            }
            String dormitoryAdministrator = Util.decode(request, "dormitoryAdministrator");
            if (dormitoryAdministrator != null && !dormitoryAdministrator.equals("")) {
                vo.setDormitoryAdministrator(dormitoryAdministrator);
            }
            String dormitoryText = Util.decode(request, "dormitoryText");
            if (dormitoryText != null && !dormitoryText.equals("")) {
                vo.setDormitoryText(dormitoryText);
            }
            DormitoryService dormitoryService = new DormitoryServiceImpl();
            //调用Service层更新方法（update），更新记录
            dormitoryService.update(vo);
            this.redirectList(request, response);
        } else if ("get".equalsIgnoreCase(action) || "editPre".equalsIgnoreCase(action)) {//根据主键ID，查询详情信息并跳转到详情页面或编辑页面
            Serializable id = Util.decode(request, "id");//取出页面传入的主键，用于查询详情
            DormitoryService dormitoryService = new DormitoryServiceImpl();
            Dormitory vo = dormitoryService.get(id);
            request.getSession().setAttribute("vo", vo);
            String to = "get".equalsIgnoreCase(action) ? "info" : "edit";//判断是去详情显示页面还是编辑页面
            response.sendRedirect("dormitory_" + to + ".jsp");
        } else {//默认去列表页面
            this.redirectList(request, response);
        }
    }

    /**
     * 处理Get请求
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);//Get请求和Post请求的处理是一样的，所以把request、response转交给Post方法就好
    }

    /**
     * 根据参数，查询出条例条件的记录集合，最后将数据返回给调用处或者将数据集合设置到session域里，再跳转到对应的列表页面
     *
     * @param request
     * @param response
     */
    private void redirectList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //查询列和关键字
        String searchColumn = Util.decode(request, "searchColumn");
        String keyword = Util.decode(request, "keyword");
        Map<String, Object> params = new HashMap();//用来保存控制层传进来的参数(查询条件)
        params.put("searchColumn", searchColumn);//要查询的列
        params.put("keyword", keyword);//查询的关键字
        DormitoryService dormitoryService = new DormitoryServiceImpl();
        Map<String, Object> map = dormitoryService.list(params);
        request.getSession().setAttribute("list", map.get("list"));

        Integer totalRecord = (Integer) map.get("totalCount");//根据查询条件取出对应的总记录数，用于分页
        String pageNum = Util.decode(request, "pageNum");//封装分页参数
        com.riotian.util.PageBean<Object> pb = new com.riotian.util.PageBean(Integer.valueOf(pageNum != null ? pageNum : "1"), totalRecord);
        params.put("startIndex", pb.getStartIndex());
        params.put("pageSize", pb.getPageSize());
        List list = (List) dormitoryService.list(params).get("list");//根据分页参数startIndex、pageSize查询出来的最终结果list
        pb.setServlet("DormitoryServlet");
        pb.setSearchColumn(searchColumn);
        pb.setKeyword(keyword);
        pb.setList(list);
        request.getSession().setAttribute("pageBean", pb);
        request.getSession().setAttribute("list", pb.getList());

        response.sendRedirect("dormitory_list.jsp");
    }
}
