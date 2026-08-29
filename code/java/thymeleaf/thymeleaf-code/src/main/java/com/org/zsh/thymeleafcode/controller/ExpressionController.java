package com.org.zsh.thymeleafcode.controller;

import com.org.zsh.thymeleafcode.model.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/expression")
public class ExpressionController {

    public static final String prefix_html = "var/";

    @GetMapping("/list")
    public String list(Model model) {
        UserVO userVO = UserVO.builder()
                .id(666L)
                .age(18)
                .username("zsh")
                .build();
        model.addAttribute("userVo", userVO);
        model.addAttribute("message","用户列表详情展示");
        model.addAttribute("title","用户详情title");
        return prefix_html + "list";
    }
}
