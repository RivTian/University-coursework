<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>维修申请 管理</title>
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
            <a class="info-detail">维修申请 管理</a>
            <br>
            <br>
        </div>
        <br>
        <div class="index-content-operation">
            <button class="btn btn-line btn-success btn-sm" onclick="window.location.href='repair_add.jsp'">
                <c:if test="${loginUser.userType == '管理员'}">添加</c:if>
                <c:if test="${loginUser.userType != '管理员'}">申请</c:if>
            </button>
            <div class="index-content-operation-search">
                <input id="search_keyword" placeholder="申请人" type="text" name="search_keyword"/>
                <input type="hidden" id="searchColumn" name="searchColumn" value="repair_name"/>
                <button class="btn btn-line btn-success btn-sm" onclick="searchList()">搜索</button>
            </div>
        </div>
        <br>
        <table class="table table-striped table-hover table-bordered">
            <thead>
            <tr class="index-content-table-th">
                <th>维修编号</th>
                <th>申请人</th>
                <th>宿舍</th>
                <th>申请时间</th>
                <th>维修备注</th>
                <th>操作</th> <!-- 如果已维修则不显示 -->
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="vo">
                <tr class="index-content-table-td">
                    <td>${vo.id}</td>
                    <td>${vo.repairName}</td>
                    <td>${vo.repairdorm}</td>
                    <td>${vo.createDate}</td>
                    <td>${vo.repairText}</td>
                    <td>
                        <!-- <button class="btn btn-line btn-primary btn-sm" style="padding: 0px 1px;"
                                    onclick="window.location.href='RepairServlet?action=get&id=${vo.id}'">详情
                            </button>&nbsp; -->
                        <button class="btn btn-line btn-primary btn-sm" style="padding: 0px 1px;"
                                <c:if test="${loginUser.userType != '管理员' and  loginUser.userType != '维修员'}">disabled="disabled"
                                title="操作失败！！！"</c:if>
                                onclick="window.location.href='RepairServlet?action=delete&id=${vo.id}'">处理
                                <!-- 测试语句: ${loginUser.userType} -->
                        </button>&nbsp;
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
            window.location.href = "RepairServlet?action=list&searchColumn=" + document.getElementById("searchColumn").value + "&keyword=" + document.getElementById("search_keyword").value;
        }
    </script>
</html>
