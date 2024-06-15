package com.yumefusaka.SmartAirport.controller;

import com.yumefusaka.SmartAirport.common.JwtProperties;
import com.yumefusaka.SmartAirport.common.Result;
import com.yumefusaka.SmartAirport.pojo.DTO.UserLoginDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.UserRegisterDTO;
import com.yumefusaka.SmartAirport.pojo.VO.LoginVO;
import com.yumefusaka.SmartAirport.service.UserService;
import com.yumefusaka.SmartAirport.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "通用")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/register")
    @Operation(summary = "注册")
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("注册用户：{}", userRegisterDTO);
        userService.addUser(userRegisterDTO);
        return Result.success("注册成功");
    }

    @PostMapping("/login")
    @Operation(summary = "登录")
    public Result<LoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("登录用户：{}", userLoginDTO);
        LoginVO loginVO = new LoginVO();
        long id = userService.login(userLoginDTO);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("identity", userLoginDTO.getIdentity());
        String token = JwtUtils.createToken(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        loginVO.setToken(token);
        loginVO.setName(userService.getUserNameById(id, userLoginDTO.getIdentity()));
        return Result.success(loginVO);
    }
}