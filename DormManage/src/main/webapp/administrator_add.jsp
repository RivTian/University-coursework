<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>添加宿管员 </title>
        <link rel="stylesheet" type="text/css" href="css/index.css"/>
    </head>
    <body>
    <%--<jsp:include page="menu.jsp"/>--%>
    <c:if test="${loginUser.userType == '管理员'}">
        <jsp:include page="menu.jsp"/>
    </c:if>
    <c:if test="${loginUser.userType != '管理员'}">
        <jsp:include page="menu_stu.jsp"/>
    </c:if>
    <div class="index-content">
        <div class="index-content-operation">
            <a class="info-detail">添加宿管员 </a>
            <br>
            <br>
        </div>
        <br>
        <form action="AdministratorServlet?action=add" method="post" onsubmit="return check()">
            <table class="index-content-table-add">
                <tr>
                    <td width="12%">姓名:</td>
                    <td><input class="index-content-table-td-add" type="text" id="administratorName"
                               name="administratorName" value=""/></td>
                </tr>
                <tr>
                    <td width="12%">性别:</td>
                    <td>
                        <input name="administratorSex" type="radio" value="男" checked="checked"/>&nbsp;&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="administratorSex" type="radio" value="女"/>&nbsp;&nbsp;&nbsp;女&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                <tr>
                    <td width="12%">电话:</td>
                    <td><input class="index-content-table-td-add" type="text" id="administratorPhone"
                               name="administratorPhone" value=""/></td>
                </tr>
                <tr>
                    <td width="12%">楼栋:</td>
                    <td><input class="index-content-table-td-add" type="text" id="administratorBuilding"
                               name="administratorBuilding" value=""/></td>
                </tr>
                <tr>
                    <td width="12%">备注:</td>
                    <td><textarea id="administratorText" name="administratorText"
                                  style="width: 60%; height: 100px;padding: 0px 17px;"
                                  placeholder="请输入内容......"></textarea></td>
                </tr>
            </table>
            <br>
            <br>
            <br>
            &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-line btn-success btn-sm">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button
                type="button" class="btn btn-line btn-primary btn-sm" onclick="javascript:history.back(-1);">取消
        </button>
        </form>
    </div>

    </body>
    <script type="text/javascript">
        //提交之前进行检查，如果return false，则不允许提交
        function check() {
            //根据ID获取值
            if (document.getElementById("administratorName").value.trim().length == 0) {
                alert("姓名不能为空!");
                return false;
            }
            if (document.getElementById("administratorPhone").value.trim().length == 0) {
                alert("电话不能为空!");
                return false;
            }
            if (document.getElementById("administratorBuilding").value.trim().length == 0) {
                alert("楼栋不能为空!");
                return false;
            }
            return true;
        }
    </script>
</html>
