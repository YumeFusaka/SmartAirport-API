package com.yumefusaka.SmartAirport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yumefusaka.SmartAirport.pojo.DTO.UserLoginDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.UserRegisterDTO;
import com.yumefusaka.SmartAirport.pojo.Entity.User;

public interface UserService extends IService<User> {

    void addUser(UserRegisterDTO userRegisterDTO);

    long findUser(UserLoginDTO userLoginDTO);

    long login(UserLoginDTO userLoginDTO);

    String getUserNameById(long id, String identity);
}