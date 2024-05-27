package com.yumefusaka.SmartAirport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yumefusaka.SmartAirport.pojo.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}