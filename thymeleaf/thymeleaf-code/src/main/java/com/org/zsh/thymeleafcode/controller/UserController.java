package com.org.zsh.thymeleafcode.controller;

import com.google.common.collect.Lists;
import com.org.zsh.thymeleafcode.model.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    public  static  final String  prefix_html = "user/";

    @GetMapping("/list")
    public String list(Model model) {
        UserVO userVO1 = UserVO.builder()
                .id(666L)
                .age(18)
                .username("zsh1")
                .build();
        UserVO userVO2 = UserVO.builder()
                .id(644L)
                .age(113)
                .username("zsh2")
                .build();
        UserVO userVO3 = UserVO.builder()
                .id(655L)
                .age(118)
                .username("zsh3")
                .build();
        UserVO userVO4 = UserVO.builder()
                .id(677L)
                .age(118)
                .username("zsh4")
                .build();
        List<UserVO> userVOList = Lists.newArrayList(userVO1, userVO2, userVO3, userVO4);
        model.addAttribute("userList", userVOList);
        model.addAttribute("message","用户列表详情展示");
        model.addAttribute("title","用户详情title");
        return prefix_html + "list";
    }
}
