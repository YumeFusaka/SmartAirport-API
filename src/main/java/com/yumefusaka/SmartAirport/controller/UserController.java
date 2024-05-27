package com.yumefusaka.SmartAirport.controller;

import com.yumefusaka.SmartAirport.pojo.DTO.UserDTO;
import com.yumefusaka.SmartAirport.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "注册")
    public String register(@RequestBody UserDTO user){
        userService.add(user);
        return "注册成功";
    }

    @PostMapping("/login")
    @Operation(summary = "登录")
    public String login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO) ?"登录成功":"登录失败";

    }
}