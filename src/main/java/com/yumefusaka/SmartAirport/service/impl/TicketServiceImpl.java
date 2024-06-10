package com.yumefusaka.SmartAirport.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumefusaka.SmartAirport.mapper.TicketMapper;
import com.yumefusaka.SmartAirport.pojo.Entity.Ticket;
import com.yumefusaka.SmartAirport.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {
}
