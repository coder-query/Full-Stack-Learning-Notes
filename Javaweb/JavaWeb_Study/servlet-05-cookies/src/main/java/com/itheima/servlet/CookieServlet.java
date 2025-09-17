package com.itheima.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("GBK");
        resp.setCharacterEncoding("GBk");
        System.out.println("CookieServlet----已经进入doGet方法()");
        Cookie[] cookies = req.getCookies();

        if (cookies.length > 0){
            //for循环遍历
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName()+"--"+cookie.getValue());
            }
        }else {
            resp.getWriter().write("<h1>您第一次来访问,当前cookie为空~~~~</h1>");
        }


        Cookie cookie1 = new Cookie("username", "zhang");
        Cookie cookie2 = new Cookie("password", "123");
        cookie1.setMaxAge(60*60*24*7);
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);


    }
}
