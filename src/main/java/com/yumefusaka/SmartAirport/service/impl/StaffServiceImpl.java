package com.yumefusaka.SmartAirport.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumefusaka.SmartAirport.mapper.StaffMapper;
import com.yumefusaka.SmartAirport.pojo.Entity.Staff;
import com.yumefusaka.SmartAirport.service.StaffService;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {
}
