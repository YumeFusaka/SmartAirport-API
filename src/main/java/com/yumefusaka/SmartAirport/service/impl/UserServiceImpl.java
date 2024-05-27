package com.yumefusaka.SmartAirport.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumefusaka.SmartAirport.mapper.UserMapper;
import com.yumefusaka.SmartAirport.pojo.DTO.UserDTO;
import com.yumefusaka.SmartAirport.pojo.Entity.User;
import com.yumefusaka.SmartAirport.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userMapper.insert(user);
    }

    @Override
    public boolean login(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        List<User> return_user =  userMapper.selectList(
                new QueryWrapper<User>().eq("username", user.getUsername())
                        .eq("password", user.getPassword()));
        return !return_user.isEmpty();
    }
}