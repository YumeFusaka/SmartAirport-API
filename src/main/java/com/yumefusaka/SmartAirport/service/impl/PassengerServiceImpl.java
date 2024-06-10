package com.yumefusaka.SmartAirport.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumefusaka.SmartAirport.mapper.FlightMapper;
import com.yumefusaka.SmartAirport.mapper.PassengerMapper;
import com.yumefusaka.SmartAirport.mapper.TicketMapper;
import com.yumefusaka.SmartAirport.pojo.Entity.Flight;
import com.yumefusaka.SmartAirport.pojo.Entity.Passenger;
import com.yumefusaka.SmartAirport.pojo.Entity.Ticket;
import com.yumefusaka.SmartAirport.pojo.VO.FindBuyTicketVO;
import com.yumefusaka.SmartAirport.pojo.VO.FlightVO;
import com.yumefusaka.SmartAirport.service.PassengerService;
import com.yumefusaka.SmartAirport.service.TicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger> implements PassengerService {

    @Autowired
    private PassengerMapper passengerMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private FlightMapper flightMapper;

    @Override
    public List<FindBuyTicketVO> findBuyTicket(Long pageNo, Long pageSize) {
        Page<Ticket> page = Page.of(pageNo, pageSize);
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(true);
        page.addOrder(orderItem);
        Page<Ticket> p = ticketService.page(page);
        List<Ticket> records = p.getRecords();
        List<FindBuyTicketVO> findBuyTicketVOS = new ArrayList<FindBuyTicketVO>();
        for (Ticket ticket : records) {
            Flight flight = flightMapper.selectById(ticket.getFlight_id());
            FlightVO flightVO = new FlightVO();
            BeanUtils.copyProperties(flight, flightVO);
            FindBuyTicketVO findBuyTicketVO = new FindBuyTicketVO();
            BeanUtils.copyProperties(ticket, findBuyTicketVO);
            findBuyTicketVO.setFlightVO(flightVO);
            findBuyTicketVOS.add(findBuyTicketVO);
        }
        return findBuyTicketVOS;
    }

    @Override
    public void buyTicket(FindBuyTicketVO findBuyTicketVO) {

    }
}
