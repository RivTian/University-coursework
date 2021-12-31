<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>修改用户 </title>
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
            <a class="info-detail">修改用户 </a>
            <br>
            <br>
        </div>
        <br>
        <form action="UserServlet?action=edit" method="post" onsubmit="return check()">
            <input type="hidden" id="id" name="id" value="${vo.id}"/>

            <table class="index-content-table-add">
                <tr>
                    <td width="12%">用户名:</td>
                    <td><input class="index-content-table-td-add" type="text" id="username" name="username"
                               value="${vo.username}"/></td>
                </tr>
                <tr>
                    <td width="12%">密码:</td>
                    <td><input class="index-content-table-td-add" type="text" id="password" name="password"
                               value="${vo.password}"/></td>
                </tr>
                <tr>
                    <td width="12%">姓名:</td>
                    <td><input class="index-content-table-td-add" type="text" id="realName" name="realName"
                               value="${vo.realName}"/></td>
                </tr>
                <tr>
                    <td width="12%">性别:</td>
                    <td>
                        <input name="userSex" type="radio" value="男" ${vo.userSex=='男'?'checked':''}/>&nbsp;&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="userSex" type="radio" value="女" ${vo.userSex=='女'?'checked':''}/>&nbsp;&nbsp;&nbsp;女&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                <tr>
                    <td width="12%">手机:</td>
                    <td><input class="index-content-table-td-add" type="text" id="userPhone" name="userPhone"
                               value="${vo.userPhone}"/></td>
                </tr>
                <tr>
                    <td width="12%">备注:</td>
                    <td><textarea id="userText" name="userText" style="width: 60%; height: 100px;padding: 0px 17px;"
                                  placeholder="请输入内容......">${vo.userText}</textarea></td>
                </tr>
                <tr>
                    <td width="12%">类型:</td>
                    <td>
                        <input
                                <c:if test="${loginUser.userType != '管理员'}">disabled="disabled" title="没有权限！！！"</c:if>
                                name="userType" type="radio" value="管理员" ${vo.userType=='管理员'?'checked':''}/>&nbsp;&nbsp;&nbsp;管理员&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="userType" type="radio" value="普通用户" ${vo.userType=='普通用户'?'checked':''}/>&nbsp;&nbsp;&nbsp;普通用户&nbsp;&nbsp;&nbsp;&nbsp;
                        <input <c:if test="${loginUser.userType != '管理员'}">disabled="disabled" title="没有权限！！！"</c:if>
                                name="userType" type="radio" value="维修员" ${vo.userType=='维修员'?'checked':''}/>&nbsp;&nbsp;&nbsp;维修员&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
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
            if (document.getElementById("username").value.trim().length == 0) {
                alert("用户名不能为空!");
                return false;
            }
            if (document.getElementById("password").value.trim().length == 0) {
                alert("密码不能为空!");
                return false;
            }
            if (document.getElementById("realName").value.trim().length == 0) {
                alert("姓名不能为空!");
                return false;
            }
            if (document.getElementById("userPhone").value.trim().length == 0) {
                alert("手机不能为空!");
                return false;
            }
            return true;
        }
    </script>
</html>
