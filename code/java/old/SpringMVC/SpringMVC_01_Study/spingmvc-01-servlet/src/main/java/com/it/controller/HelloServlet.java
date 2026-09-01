package com.it.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取前端数据请求
        String method = req.getParameter("method");
        if ("add".equals(method)) {
            req.setAttribute("msg", "执行了add方法");
        } else if ("delete".equals(method)) {
            req.setAttribute("msg", "执行了delete方法");
        } else {
            req.setAttribute("msg", "执行了else方法");
        }

        //业务层

        //转发或重定向
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
