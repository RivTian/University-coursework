/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-12-29 12:39:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.riotian.util.Util;
import java.net.URLDecoder;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

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

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <title>登录页</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/index.css\"/>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            let alert_msg = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${alert_msg}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\n");
      out.write("            if (alert_msg != null && alert_msg.trim() != '') {\n");
      out.write("                window.alert(alert_msg);\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    <h1 style=\"text-align: center;font-size: 40px;padding-top:1px;font-weight: 700;color:#000000;text-shadow: 2px 3px #FFFFFF;\">\n");
      out.write("        学生宿舍管理系统</h1>\n");
      out.write("    <form action=\"AuthServlet?action=login\" method=\"post\" onsubmit=\"return check()\">\n");
      out.write("        <input type=\"hidden\" name=\"forwardPage\" id=\"forwardPage\" value=\"NoticeServlet?action=list\"/>\n");
      out.write("        <div class=\"login\" style=\"opacity: 0.7\">\n");
      out.write("            <div class=\"login-top\">\n");
      out.write("                <a href=\"#\" style=\"color:dodgerblue ;text-decoration: none;padding-left: 63px;\">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a\n");
      out.write("                    style=\"color: black;text-decoration: none;\" href=\"register.jsp\">注册</a>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"login-center clearfix\">\n");
      out.write("                <div class=\"login-center-img\"><img src=\"img/name.png\"/></div>\n");
      out.write("                <div class=\"login-center-input\">\n");
      out.write("                    <input type=\"text\" id=\"username\" name=\"username\"\n");
      out.write("                           value=\"");
      out.print(USERNAME);
      out.write("\"/>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <br>\n");
      out.write("            <div class=\"login-center clearfix\">\n");
      out.write("                <div class=\"login-center-img\"><img src=\"img/password.png\"/></div>\n");
      out.write("                <div class=\"login-center-input\">\n");
      out.write("                    <input type=\"password\" id=\"password\" name=\"password\" value=\"");
      out.print(PASSWORD);
      out.write("\"/>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <br>\n");
      out.write("\n");
      out.write("            <div class=\"login-center clearfix\">\n");
      out.write("                <div class=\"login-center-img\"><img src=\"img/password.png\"/></div>\n");
      out.write("                <div class=\"login-center-input\">\n");
      out.write("                    <input type=\"text\" id=\"validationCode\" name=\"validationCode\"\n");
      out.write("                           style=\"font-size: 10px;width: 90px;margin-top: 0px;\" placeholder=\"请输入验证码\"/>\n");
      out.write("                    <img id=\"img_validation_code\" src=\"AuthServlet?action=validationCode\" onclick=\"refresh()\"\n");
      out.write("                         style=\"height: 30px;\"/>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <br>\n");
      out.write("\n");
      out.write("            <div class=\"login-center clearfix\">\n");
      out.write("                <div>\n");
      out.write("                    <label for=\"remember\"></label><input type=\"checkbox\" name=\"remember\" id=\"remember\" value=\"yes\">\n");
      out.write("                    <b> 记住我&nbsp;(默认记住三天) </b>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            ");
      out.write("\n");
      out.write("            <button type=\"submit\" class=\"login-button\" id=\"btn\">登陆</button>\n");
      out.write("        </div>\n");
      out.write("    </form>\n");
      out.write("    </body>\n");
      out.write("    <script src=\"js/jquery-3.3.1.js\"></script>\n");
      out.write("    <script type=\"text/javascript\">\n");
      out.write("        //提交之前进行检查，如果return false，则不允许提交\n");
      out.write("        function check() {\n");
      out.write("            //根据ID获取值\n");
      out.write("            var username = document.getElementById(\"username\").value;\n");
      out.write("            var password = document.getElementById(\"password\").value;\n");
      out.write("            if (username == \"\") {\n");
      out.write("                alert(\"用户名不能为空\");\n");
      out.write("                return false;\n");
      out.write("            }\n");
      out.write("            if (password == \"\") {\n");
      out.write("                alert(\"密码不能为空\");\n");
      out.write("                return false;\n");
      out.write("            }\n");
      out.write("            return true;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        function refresh() {\n");
      out.write("            var img = document.getElementById(\"img_validation_code\")\n");
      out.write("            img.src = \"AuthServlet?action=validationCode&r=\" + Math.random();\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
