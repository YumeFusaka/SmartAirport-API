package com.yumefusaka.SmartAirport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yumefusaka.SmartAirport.pojo.DTO.UserDTO;
import com.yumefusaka.SmartAirport.pojo.Entity.User;

public interface UserService extends IService<User> {
    void add(UserDTO user);

    boolean login(UserDTO userDTO);
}