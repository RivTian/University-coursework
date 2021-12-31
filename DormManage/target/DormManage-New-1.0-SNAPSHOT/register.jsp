<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>注册页</title>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <script type="text/javascript">
        var alert_msg = '${alert_msg}';
        if(alert_msg != null && alert_msg.trim() != ''){
            window.alert(alert_msg) ;
        }
    </script>
</head>
<body>
<h1 style="text-align: center;font-size: 40px;padding-top:1px;font-weight: 700;color:#000000;text-shadow: 2px 3px #FFFFFF;">学生宿舍管理系统</h1>
<form action="AuthServlet?action=register" method="post" onsubmit="return check()">
    <div class="login">
        <div class="login-top">
            <a href="login.jsp" style="color: black;text-decoration: none;padding-left: 63px;">登录</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href="#" style="color:dodgerblue ;text-decoration: none;">注册</a>
        </div>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="img/name.png"/></div>
            <div class="login-center-input">
                <input type="text" id="username" name="username" value="" placeholder="请输入用户名"
                       onfocus="this.placeholder=''" onblur="this.placeholder='请输入用户名'"/>
            </div>
        </div>
        <br>
        <br>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="img/password.png"/></div>
            <div class="login-center-input">
                <input type="password" id="password" name="password" value="" placeholder="请输入密码"
                       onfocus="this.placeholder=''" onblur="this.placeholder='请输入密码'"/>
            </div>
        </div>
        <br>
        <br>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="img/password.png"/></div>
            <div class="login-center-input">
                <input type="password" id="password2" name="password2" value="" placeholder="确认密码"
                       onfocus="this.placeholder=''" onblur="this.placeholder='请确认密码'"/>
            </div>
        </div>
        <br>
        <button type="submit" class="login-button">注册</button>
    </div>
</form>
</body>
<script type="text/javascript">
    //提交之前进行检查，如果return false，则不允许提交
    function check() {
        //根据ID获取值
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var password2 = document.getElementById("password2").value;
        if (username == "") {
            alert("用户名不能为空！");
            return false;
        }
        if (password == "") {
            alert("密码不能为空！");
            return false;
        }
        if (password2 != password) {
            alert("密码输入不一致！");
            return false;
        }
        return true;
    }
</script>
</html>
