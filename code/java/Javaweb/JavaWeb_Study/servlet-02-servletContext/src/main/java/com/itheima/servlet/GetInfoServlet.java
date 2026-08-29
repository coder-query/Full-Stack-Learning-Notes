package com.shuai.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetInfoServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ServletContext context = this.getServletContext();
    response.setContentType("text/html");
    response.setCharacterEncoding("utf-8");
    response.getWriter().write("<h1>" + context.getAttribute("msg") + "</h1>");
  }
}
