<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>宿管员 详情</title>
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
            <a class="info-detail">宿管员 详情</a>
            <br>
            <br>
        </div>
        <br>
        <form>
            <table class="index-content-table-add" style="font-size: 18px;">
                <tr>
                    <td>姓名:<b>${vo.administratorName}</b></td>
                </tr>
                <tr>
                    <td>性别:
                        <b>${vo.administratorSex}</b>
                    </td>
                </tr>
                <tr>
                    <td>电话:<b>${vo.administratorPhone}</b></td>
                </tr>
                <tr>
                    <td>楼栋:<b>${vo.administratorBuilding}</b></td>
                </tr>
                <tr>
                    <td>备注:<b>${vo.administratorText}</b></td>
                </tr>
            </table>
            <br>
            <button type="button" class="btn btn-line btn-primary btn-sm" onclick="javascript:history.back(-1);">返回
            </button>
        </form>
    </div>
    </body>
</html>
