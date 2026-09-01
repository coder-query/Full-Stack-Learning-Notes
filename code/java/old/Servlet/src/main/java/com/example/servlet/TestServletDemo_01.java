package com.example.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/7 0007
 */
public class TestServletDemo_01 implements Servlet {

    /**
     * 执行init() 方法 , 只执行一次 在服务启动时执行
     *
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("初始化 inti() ~");

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 请求访问一次, 执行service() , 可以执行多次
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("Hello TestServletDemo_01 ~");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 容器正常关闭时, 调用一次
     */
    @Override
    public void destroy() {
        System.out.println("销毁 destroy() ~");
    }
}
