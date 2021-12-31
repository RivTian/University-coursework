package com.riotian.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 这是过滤器
 * 主要负责过滤编码为utf-8及登录拦截，禁止未登录就访问
 */
public class LoginFilter implements Filter {


    public void destroy() {
    }


    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        //过滤编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //移除错误提示
        session.removeAttribute("alert_msg");
        //登录拦截
        String uri = request.getRequestURI();
        String action = request.getParameter("action");
        // equalsIgnoreCase(): 将字符串与指定的对象比较，不考虑大小写
        // endsWith(): 方法用于测试字符串是否以指定的后缀结束。
        if (uri.endsWith("login.jsp") || uri.endsWith("register.jsp") || "register".equalsIgnoreCase(action) || "validationCode".equalsIgnoreCase(action) || "login".equalsIgnoreCase(action) || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        } else if (session.getAttribute("loginUser") == null) {
            session.setAttribute("alert_msg", "错误：请先登录！");
            response.sendRedirect("login.jsp");
            return;
        }
        chain.doFilter(request, response);
    }


    public void init(FilterConfig arg0) throws ServletException {
    }
}