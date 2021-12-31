<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>公告 详情</title>
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
            <a class="info-detail">公告 详情</a>
            <br>
            <br>
        </div>
        <br>
        <form>
            <table class="index-content-table-add" style="font-size: 18px;">
                <tr>
                    <td>标题:<b>${vo.noticeName}</b></td>
                </tr>
                <tr>
                    <td>内容:<b>${vo.noticeText}</b></td>
                </tr>
                <tr>
                    <td>类型:<b>${vo.noticeType}</b></td>
                </tr>
                <tr>
                    <td>创建时间:<b>${vo.createDate}</b></td>
                </tr>
            </table>
            <br>
            <button type="button" class="btn btn-line btn-primary btn-sm" onclick="javascript:history.back(-1);">返回
            </button>
        </form>
    </div>
    </body>
</html>
