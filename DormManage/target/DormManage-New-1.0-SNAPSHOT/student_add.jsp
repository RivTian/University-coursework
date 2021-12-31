<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>添加学生 </title>
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
            <a class="info-detail">添加学生 </a>
            <br>
            <br>
        </div>
        <br>
        <form action="StudentServlet?action=add" method="post" onsubmit="return check()">
            <table class="index-content-table-add">
                <tr>
                    <td width="12%">姓名:</td>
                    <td><input class="index-content-table-td-add" type="text" id="studentName" name="studentName"
                               value=""/></td>
                </tr>
                <tr>
                    <td width="12%">性别:</td>
                    <td>
                        <input name="studentSex" type="radio" value="男" checked="checked"/>&nbsp;&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="studentSex" type="radio" value="女"/>&nbsp;&nbsp;&nbsp;女&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                <tr>
                    <td width="12%">学号:</td>
                    <td><input class="index-content-table-td-add" type="text" id="studentNo" name="studentNo" value=""/>
                    </td>
                </tr>
                <tr>
                    <td width="12%">楼栋:</td>
                    <td><input class="index-content-table-td-add" type="text" id="studentBuilding"
                               name="studentBuilding" value=""/>
                    </td>
                </tr>
                <tr>
                    <td width="12%">宿舍:</td>
                    <td><input class="index-content-table-td-add" type="text" id="studentDormitory"
                               name="studentDormitory" value=""/></td>
                </tr>
                <tr>
                    <td width="12%">电话:</td>
                    <td><input class="index-content-table-td-add" type="text" id="studentPhone" name="studentPhone"
                               value=""/></td>
                </tr>
                <tr>
                    <td width="12%">备注:</td>
                    <td><textarea id="studentText" name="studentText"
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
            if (document.getElementById("studentName").value.trim().length == 0) {
                alert("姓名不能为空!");
                return false;
            }
            if (document.getElementById("studentNo").value.trim().length == 0) {
                alert("学号不能为空!");
                return false;
            }
            if (document.getElementById("studentBuilding").value.trim().length == 0) {
                alert("楼栋不能为空!");
                return false;
            }
            if (document.getElementById("studentDormitory").value.trim().length == 0) {
                alert("宿舍不能为空!");
                return false;
            }
            if (document.getElementById("studentPhone").value.trim().length == 0) {
                alert("电话不能为空!");
                return false;
            }
            return true;
        }
    </script>
</html>
