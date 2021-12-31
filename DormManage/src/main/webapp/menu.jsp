<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>主页</title>
        <link rel="stylesheet" type="text/css" href="css/index.css"/>
    </head>
    <body>
    <div class="index-nav" style="opacity: 0.8">
        <div class="index-nav-frame clearfix">
            <div class="nav-line"></div>
            <div class="nav-small" tabindex="-1">
                <div class="nav-small-focus" tabindex="-1">
                </div>
                <img src="img/icon.png"/>
            </div>
            <div class="index-nav-frame-line" tabindex="-1">
                <a class="btn btn-line btn-success btn-sm" href="UserServlet">用户管理</a>
            </div>
            <div class="index-nav-frame-line" tabindex="-1">
                <a class="btn btn-line btn-success btn-sm" href="AdministratorServlet">宿管员管理</a>
            </div>
            <div class="index-nav-frame-line" tabindex="-1">
                <a class="btn btn-line btn-success btn-sm" href="DormitoryServlet">宿舍管理</a>
            </div>
            <div class="index-nav-frame-line" tabindex="-1">
                <a class="btn btn-line btn-success btn-sm" href="StudentServlet">学生管理</a>
            </div>

            <div class="index-nav-frame-line" tabindex="-1">
                <a class="btn btn-line btn-success btn-sm" href="NoticeServlet">公告管理</a>
            </div>
            <!-- TODO: 修改 href 导向的 Servlet -->
            <div class="index-nav-frame-line" tabindex="-1">
                <a class="btn btn-line btn-success btn-sm" href="RepairServlet">维修列表</a>
            </div>
            <div class="index-nav-frame-line" style="float: right;" tabindex="-1">
                <a href="AuthServlet?action=logout" class="btn btn-line btn-info btn-sm">退出</a>
            </div>
            <div class="index-nav-frame-line" style="float: right;color: #000000;width: 200px">
                欢迎：<a href="UserServlet?action=get&id=${loginUser.id}">${loginUser.username}</a>
            </div>
        </div>
    </div>
    </div>
    </div>
    </body>
</html>
