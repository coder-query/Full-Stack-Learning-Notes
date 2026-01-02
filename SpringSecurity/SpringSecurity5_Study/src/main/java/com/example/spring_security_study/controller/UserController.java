package com.example.spring_security_study.controller;

import com.example.spring_security_study.model.dto.UserDTO;
import com.example.spring_security_study.model.entity.User;
import com.example.spring_security_study.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    /**
     * 添加用户
     *
     * @param userDTO
     */
    @PreAuthorize("hasRole('管理员')")
    @PostMapping("/add")
    public void add(@RequestBody UserDTO userDTO) {
        userService.saveUserDetails(userDTO);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @PreAuthorize("hasRole('管理员')")
    @GetMapping("/list")
    public List<User> getList() {
        return userService.list();
    }
}