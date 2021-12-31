<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>宿舍 管理</title>
        <link rel="stylesheet" type="text/css" href="css/index.css"/>
    </head>
    <body>
    <c:if test="${loginUser.userType == '管理员'}">
        <jsp:include page="menu.jsp"/>
    </c:if>
    <c:if test="${loginUser.userType != '管理员'}">
        <jsp:include page="menu_stu.jsp"/>
    </c:if>
    <div class="index-content">
        <div class="index-content-operation">
            <a class="info-detail">宿舍 管理</a>
            <br>
            <br>
        </div>
        <br>
        <div class="index-content-operation">
            <button class="btn btn-line btn-success btn-sm"
                    <c:if test="${loginUser.userType != '管理员'}">disabled="disabled" title="没有权限！！！"</c:if>
                    onclick="window.location.href='dormitory_add.jsp'">添加
            </button>
            <div class="index-content-operation-search"><input id="search_keyword" placeholder="宿舍" type="text"
                                                               name="search_keyword"/><input type="hidden"
                                                                                             id="searchColumn"
                                                                                             name="searchColumn"
                                                                                             value="dormitory_name"/>
                <button class="btn btn-line btn-success btn-sm" onclick="searchList()">搜索</button>
            </div>
        </div>
        <br>
        <table class="table table-striped table-hover table-bordered">
            <thead>
            <tr class="index-content-table-th">
                <th>宿舍</th>
                <th>楼栋</th>
                <th>床位数</th>
                <th>宿管员</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="vo">
                <tr class="index-content-table-td">
                    <td>${vo.dormitoryName}</td>
                    <td>${vo.dormitoryBuilding}</td>
                    <td>${vo.dormitoryBedcount}</td>
                    <td>${vo.dormitoryAdministrator}</td>
                    <td>
                        <button class="btn btn-line btn-primary btn-sm" style="padding: 0px 1px;"
                                onclick="window.location.href='DormitoryServlet?action=get&id=${vo.id}'">详情
                        </button>&nbsp;
                        <button class="btn btn-line btn-primary btn-sm" style="padding: 0px 1px;"
                                <c:if test="${loginUser.userType != '管理员'}">disabled="disabled" title="没有权限！！！"</c:if>
                                onclick="window.location.href='DormitoryServlet?action=editPre&id=${vo.id}'">编辑
                        </button>&nbsp;
                        <button class="btn btn-line btn-success btn-sm" style="padding: 0px 1px;"
                                <c:if test="${loginUser.userType != '管理员'}">disabled="disabled" title="没有权限！！！"</c:if>
                                onclick="if(window.confirm('将要删除:${vo.dormitoryName}？'))window.location.href='DormitoryServlet?action=delete&id=${vo.id}'">
                            删除
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div style="float: right;padding-right: 10px;color: #515151;">
            <jsp:include page="split.jsp"/>
        </div>
    </div>
    </body>
    <script>
        function searchList() {
            window.location.href = "DormitoryServlet?action=list&searchColumn=" + document.getElementById("searchColumn").value + "&keyword=" + document.getElementById("search_keyword").value;
        }
    </script>
</html>
