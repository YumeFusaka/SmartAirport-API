package com.yumefusaka.SmartAirport.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumefusaka.SmartAirport.common.BaseContext;
import com.yumefusaka.SmartAirport.constant.Identity;
import com.yumefusaka.SmartAirport.mapper.*;
import com.yumefusaka.SmartAirport.pojo.DTO.UserLoginDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.UserRegisterDTO;
import com.yumefusaka.SmartAirport.pojo.Entity.*;
import com.yumefusaka.SmartAirport.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private PassengerMapper passengerMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private AirlineMapper airlineMapper;

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public void addUser(UserRegisterDTO userRegisterDTO) {
        BaseContext.removeCurrentInfo();
        String identity = userRegisterDTO.getIdentity();
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        BeanUtils.copyProperties(userRegisterDTO, userLoginDTO);
        int isExist = findUser(userLoginDTO);
        if (isExist != 0)
            throw new RuntimeException("用户已存在");
        if (Objects.equals(identity, Identity.PASSENGER)) {
            Passenger passenger = new Passenger();
            BeanUtils.copyProperties(userRegisterDTO, passenger);
            passenger.setMoney(10000L);
            passengerMapper.insert(passenger);
        } else if (Objects.equals(identity, Identity.MERCHANT)) {
            Merchant merchant = new Merchant();
            BeanUtils.copyProperties(userRegisterDTO, merchant);
            merchantMapper.insert(merchant);
        } else if (Objects.equals(identity, Identity.AIRLINE)) {
            Airline airline = new Airline();
            BeanUtils.copyProperties(userRegisterDTO, airline);
            airlineMapper.insert(airline);
        } else if (Objects.equals(identity, Identity.STAFF)) {
            Staff staff = new Staff();
            BeanUtils.copyProperties(userRegisterDTO, staff);
            staffMapper.insert(staff);
        }
    }

    @Override
    public int findUser(UserLoginDTO userLoginDTO) {
        BaseContext.removeCurrentInfo();
        String identity = userLoginDTO.getIdentity();
        if (Objects.equals(identity, Identity.PASSENGER)) {
            Passenger passenger = passengerMapper.selectOne(new QueryWrapper<Passenger>()
                    .eq("username", userLoginDTO.getUsername())
                    .eq("password", userLoginDTO.getPassword()));
            if (passenger != null)
                return passenger.getId();
        } else if (Objects.equals(identity, Identity.MERCHANT)) {
            Merchant merchant = merchantMapper.selectOne(new QueryWrapper<Merchant>()
                    .eq("username", userLoginDTO.getUsername())
                    .eq("password", userLoginDTO.getPassword()));
            if (merchant != null)
                return merchant.getId();
        } else if (Objects.equals(identity, Identity.AIRLINE)) {
            Airline airline = airlineMapper.selectOne(new QueryWrapper<Airline>()
                    .eq("username", userLoginDTO.getUsername())
                    .eq("password", userLoginDTO.getPassword()));
            if (airline != null)
                return airline.getId();
        } else if (Objects.equals(identity, Identity.STAFF)) {
            Staff staff = staffMapper.selectOne(new QueryWrapper<Staff>()
                    .eq("username", userLoginDTO.getUsername())
                    .eq("password", userLoginDTO.getPassword()));
            if (staff != null)
                return staff.getId();
        }
        return 0;
    }

    @Override
    public int login(UserLoginDTO userLoginDTO) {
        BaseContext.removeCurrentInfo();
        int id = findUser(userLoginDTO);
        if (id == 0)
            throw new RuntimeException("未找到此用户,请确保该账号已注册");
        return id;
    }
}