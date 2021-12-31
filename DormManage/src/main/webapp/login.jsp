<%@ page import="com.riotian.util.Util" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Cookie[] c = request.getCookies();
    String USERNAME = " ";
    String PASSWORD = " ";
    if (c != null) {
        for (int i = 0; i < c.length; i += 1) {
            if (c[i].getName().equals("USERNAME")) {
                USERNAME = c[i].getValue();
                System.out.println("add username...");
            } else if (c[i].getName().equals("PASSWORD")) {
                PASSWORD = c[i].getValue();
                System.out.println("add password...");
            }
        }
    }
    if (USERNAME == " ") {
        USERNAME = "请输入您的账号";
    }
    if (PASSWORD == " ") {
        PASSWORD = "请输入您的密码";
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>登录页</title>
        <link rel="stylesheet" type="text/css" href="css/index.css"/>
        <script type="text/javascript">
            let alert_msg = '${alert_msg}';
            if (alert_msg != null && alert_msg.trim() != '') {
                window.alert(alert_msg);
            }
        </script>
    </head>
    <body>
    <h1 style="text-align: center;font-size: 40px;padding-top:1px;font-weight: 700;color:#000000;text-shadow: 2px 3px #FFFFFF;">
        学生宿舍管理系统</h1>
    <form action="AuthServlet?action=login" method="post" onsubmit="return check()">
        <input type="hidden" name="forwardPage" id="forwardPage" value="NoticeServlet?action=list"/>
        <div class="login" style="opacity: 0.7">
            <div class="login-top">
                <a href="#" style="color:dodgerblue ;text-decoration: none;padding-left: 63px;">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
                    style="color: black;text-decoration: none;" href="register.jsp">注册</a>
            </div>
            <div class="login-center clearfix">
                <div class="login-center-img"><img src="img/name.png"/></div>
                <div class="login-center-input">
                    <input type="text" id="username" name="username"
                           value="<%=USERNAME%>"/>
                </div>
            </div>
            <br>
            <div class="login-center clearfix">
                <div class="login-center-img"><img src="img/password.png"/></div>
                <div class="login-center-input">
                    <input type="password" id="password" name="password" value="<%=PASSWORD%>"/>
                </div>
            </div>
            <br>

            <div class="login-center clearfix">
                <div class="login-center-img"><img src="img/password.png"/></div>
                <div class="login-center-input">
                    <input type="text" id="validationCode" name="validationCode"
                           style="font-size: 10px;width: 90px;margin-top: 0px;" placeholder="请输入验证码"/>
                    <img id="img_validation_code" src="AuthServlet?action=validationCode" onclick="refresh()"
                         style="height: 30px;"/>
                </div>
            </div>
            <br>

            <div class="login-center clearfix">
                <div>
                    <label for="remember"></label><input type="checkbox" name="remember" id="remember" value="yes">
                    <b> 记住我&nbsp;(默认记住三天) </b>
                </div>
            </div>
            <%-- <input type="submit" value="登陆" id="btn" class="login-button">--%>
            <button type="submit" class="login-button" id="btn">登陆</button>
        </div>
    </form>
    </body>
    <script src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        //提交之前进行检查，如果return false，则不允许提交
        function check() {
            //根据ID获取值
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            if (username == "") {
                alert("用户名不能为空");
                return false;
            }
            if (password == "") {
                alert("密码不能为空");
                return false;
            }
            return true;
        }

        function refresh() {
            var img = document.getElementById("img_validation_code")
            img.src = "AuthServlet?action=validationCode&r=" + Math.random();
        }
    </script>
</html>
