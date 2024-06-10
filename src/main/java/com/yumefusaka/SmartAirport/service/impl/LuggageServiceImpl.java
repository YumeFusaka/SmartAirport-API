package com.yumefusaka.SmartAirport.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumefusaka.SmartAirport.mapper.LuggageMapper;
import com.yumefusaka.SmartAirport.pojo.Entity.Luggage;
import com.yumefusaka.SmartAirport.service.LuggageService;
import org.springframework.stereotype.Service;

@Service
public class LuggageServiceImpl extends ServiceImpl<LuggageMapper, Luggage> implements LuggageService {
}
