<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>添加维修申请单 </title>
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
            <a class="info-detail">添加维修申请单 </a>
            <br>
            <br>
        </div>
        <br>
        <form action="RepairServlet?action=add" method="post" onsubmit="return check()">
            <table class="index-content-table-add">
                <tr>
                    <td width="12%">申请人:</td>
                    <td><input class="index-content-table-td-add" type="text" id="repairname" name="repairname"
                              placeholder="请输入申请人姓名(如: 张三)" value=""/></td>
                </tr>
                <tr>
                    <td width="12%">宿舍:</td>
                    <td><input class="index-content-table-td-add" type="text" id="repairdorm" name="repairdorm"
                              placeholder="请输入宿舍号(如: 8717)" value=""/></td>
                </tr>
                <tr>
                    <td width="12%">申请时间:</td>
                    <td><input class="index-content-table-td-add" type="text" id="repairtime" name="repairtime" placeholder="年 / 月 / 日"
                               value=""/></td>
                </tr>
                <tr>
                    <td width="12%">维修备注:</td>
                    <td><textarea id="repairtext" name="repairtext" style="width: 60%; height: 100px;padding: 0px 17px;"
                                  placeholder="维修详细信息......"></textarea></td>

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
            if (document.getElementById("repairname").value.trim().length == 0) {
                alert("申请人不能为空!");
                return false;
            }
            if (document.getElementById("repairdorm").value.trim().length == 0) {
                alert("宿舍号不能为空!");
                return false;
            }
            if (document.getElementById("repairtime").value.trim().length == 0) {
                alert("申请时间不能为空!");
                return false;
            }
            if (document.getElementById("repairtext").value.trim().length == 0) {
                alert("维修备注不能为空!");
                return false;
            }
            return true;
        }
    </script>
</html>
