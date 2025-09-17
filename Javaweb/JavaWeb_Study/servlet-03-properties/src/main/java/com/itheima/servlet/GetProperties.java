package com.itheima.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        InputStream resourceAsStream = context.getResourceAsStream("/WEB-INF/classes/db.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("jdbc.driver-->"+properties.getProperty("jdbc.driver"));
        response.getWriter().write("<br>");
        response.getWriter().write("jdbc.url-->"+properties.getProperty("jdbc.url"));
        response.getWriter().write("<br>");
        response.getWriter().write("jdbc.username-->"+properties.getProperty("jdbc.username"));
        response.getWriter().write("<br>");
        response.getWriter().write("jdbc.password-->"+properties.getProperty("jdbc.password"));
    }
}
