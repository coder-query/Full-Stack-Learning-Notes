package com.shaui.spring_security.controller;

import com.shaui.spring_security.common.resp.Result;
import com.shaui.spring_security.model.dto.LoginUserDTO;
import com.shaui.spring_security.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/13 0013
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody LoginUserDTO loginUserDTO) {

        String token = userService.login(loginUserDTO);
        System.out.println("userController --> login");

        HashMap<String, String> mapToken = new HashMap<>();
        mapToken.put("token", token);

        return Result.success(mapToken);
    }

//    @GetMapping("/login")
//    public Result<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
//        LoginUserDTO loginUserDTO = new LoginUserDTO(username, password);
//        String token = userService.login(loginUserDTO);
//        System.out.println("userController");
//        return Result.success(token);
//    }


    @GetMapping("/logout")
    public Result<String> logout() {
        boolean logout = userService.logout();
        return logout ? Result.success() : Result.error();
    }

}
