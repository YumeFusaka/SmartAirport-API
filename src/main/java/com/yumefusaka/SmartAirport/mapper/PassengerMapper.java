package com.yumefusaka.SmartAirport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yumefusaka.SmartAirport.pojo.Entity.Passenger;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PassengerMapper extends BaseMapper<Passenger> {
}
