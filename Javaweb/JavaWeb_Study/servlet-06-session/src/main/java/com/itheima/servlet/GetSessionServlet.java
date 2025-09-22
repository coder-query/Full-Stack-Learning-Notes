package com.shuai.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetSessionServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("GetSessionServlet----已经进入doGet方法()");

    HttpSession session = req.getSession();
    resp.setContentType("text/html;charset=utf-8");
    req.setCharacterEncoding("utf-8");
    resp.setCharacterEncoding("utf-8");

    String username = (String) session.getAttribute("username");
    String sessionId = session.getId();

    resp.getWriter().write("<h1>Session中取出的数据是：" + username + "</h1>");
    resp.getWriter().write("<h1>Session的id是：" + sessionId + "</h1>");
  }
}
