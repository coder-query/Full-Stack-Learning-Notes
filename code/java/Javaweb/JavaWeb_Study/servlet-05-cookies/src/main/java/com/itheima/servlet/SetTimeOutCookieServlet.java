package com.shuai.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetTimeOutCookieServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setCharacterEncoding("GBK");
    new Cookie("username", "zhang").setMaxAge(0);
    new Cookie("password", "123").setMaxAge(0);
    resp.getWriter().write("<h1>cookie已失效</h1>");
    System.out.println("cookie已失效");
  }
}
