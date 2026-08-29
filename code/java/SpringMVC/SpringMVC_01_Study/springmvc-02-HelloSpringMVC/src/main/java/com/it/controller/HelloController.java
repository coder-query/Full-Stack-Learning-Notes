package com.it.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//注意：这里我们先导入Controller接口
public class HelloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();


        //业务层
        String msg = "HelloSpringMVC";

        //模型层
        modelAndView.addObject("msg", msg);
        modelAndView.setViewName("hello");

        return modelAndView;
    }
}

