package com.shuai.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetSessionServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    resp.setCharacterEncoding("utf-8");
    resp.setContentType("text/html;charset=utf-8");
    System.out.println("SetSessionServlet----已经进入doGet方法()");

    // 得到Session对象
    HttpSession session = req.getSession();

    // 往Session中存东西
    session.setAttribute("username", "zhang");

    // 响应
    resp.getWriter().write("<h1>Session中刚刚已经存了数据</h1>");
  }
}
