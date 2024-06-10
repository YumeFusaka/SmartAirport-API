package com.yumefusaka.SmartAirport.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumefusaka.SmartAirport.mapper.FlightMapper;
import com.yumefusaka.SmartAirport.pojo.Entity.Flight;
import com.yumefusaka.SmartAirport.service.FlightService;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl extends ServiceImpl<FlightMapper, Flight> implements FlightService {
}
