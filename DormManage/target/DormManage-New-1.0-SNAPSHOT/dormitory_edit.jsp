<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>修改宿舍 </title>
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
            <a class="info-detail">修改宿舍 </a>
            <br>
            <br>
        </div>
        <br>
        <form action="DormitoryServlet?action=edit" method="post" onsubmit="return check()">
            <input type="hidden" id="id" name="id" value="${vo.id}"/>

            <table class="index-content-table-add">
                <tr>
                    <td width="12%">宿舍:</td>
                    <td><input class="index-content-table-td-add" type="text" id="dormitoryName" name="dormitoryName"
                               value="${vo.dormitoryName}"/></td>
                </tr>
                <tr>
                    <td width="12%">楼栋:</td>
                    <td><input class="index-content-table-td-add" type="text" id="dormitoryBuilding"
                               name="dormitoryBuilding" value="${vo.dormitoryBuilding}"/></td>
                </tr>
                <tr>
                    <td width="12%">床位数:</td>
                    <td><input class="index-content-table-td-add" type="text" id="dormitoryBedcount"
                               name="dormitoryBedcount" value="${vo.dormitoryBedcount}"/></td>
                </tr>
                <tr>
                    <td width="12%">宿管员:</td>
                    <td><input class="index-content-table-td-add" type="text" id="dormitoryAdministrator"
                               name="dormitoryAdministrator" value="${vo.dormitoryAdministrator}"/></td>
                </tr>
                <tr>
                    <td width="12%">备注:</td>
                    <td><textarea id="dormitoryText" name="dormitoryText"
                                  style="width: 60%; height: 100px;padding: 0px 17px;"
                                  placeholder="请输入内容......">${vo.dormitoryText}</textarea></td>
                </tr>
            </table>
            <br>
            <br>
            <br>
            &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-line btn-success btn-sm" onclick="javascript:history.back(-1);">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button
                type="button" class="btn btn-line btn-primary btn-sm" onclick="javascript:history.back(-1);">取消
        </button>
        </form>
    </div>

    </body>
    <script type="text/javascript">
        //提交之前进行检查，如果return false，则不允许提交
        function check() {
            //根据ID获取值
            if (document.getElementById("dormitoryName").value.trim().length == 0) {
                alert("宿舍不能为空!");
                return false;
            }
            if (document.getElementById("dormitoryBuilding").value.trim().length == 0) {
                alert("楼栋不能为空!");
                return false;
            }
            if (document.getElementById("dormitoryBedcount").value.trim().length == 0) {
                alert("床位数不能为空!");
                return false;
            }
            if (document.getElementById("dormitoryAdministrator").value.trim().length == 0) {
                alert("宿管员不能为空!");
                return false;
            }
            return true;
        }
    </script>
</html>
