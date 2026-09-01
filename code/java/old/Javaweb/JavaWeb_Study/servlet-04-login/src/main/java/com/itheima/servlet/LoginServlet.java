package com.shuai.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet implements Filter {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    System.out.println("username = " + username);
    System.out.println("password = " + password);
    request.getRequestDispatcher("/success.jsp").forward(request, response);
    System.out.println("request.getContextPath() === >>>" + request.getContextPath());
    // response.sendRedirect(request.getContextPath()+"/success.jsp");

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {}
}
