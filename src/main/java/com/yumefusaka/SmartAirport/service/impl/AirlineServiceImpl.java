package com.yumefusaka.SmartAirport.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumefusaka.SmartAirport.common.BaseContext;
import com.yumefusaka.SmartAirport.common.Result;
import com.yumefusaka.SmartAirport.mapper.AirlineMapper;
import com.yumefusaka.SmartAirport.mapper.FlightMapper;
import com.yumefusaka.SmartAirport.mapper.TicketMapper;
import com.yumefusaka.SmartAirport.pojo.DTO.*;
import com.yumefusaka.SmartAirport.pojo.Entity.Airline;
import com.yumefusaka.SmartAirport.pojo.Entity.Flight;
import com.yumefusaka.SmartAirport.pojo.Entity.Ticket;
import com.yumefusaka.SmartAirport.pojo.VO.FindBuyTicketVO;
import com.yumefusaka.SmartAirport.pojo.VO.FlightVO;
import com.yumefusaka.SmartAirport.service.AirlineService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirlineServiceImpl extends ServiceImpl<AirlineMapper, Airline> implements AirlineService {

    @Autowired
    private AirlineMapper airlineMapper;

    @Autowired
    private FlightMapper flightMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public void addFlight(AddFlightDTO addFlightDTO) {
        int currentId = BaseContext.getCurrentInfo().getId();
        BaseContext.removeCurrentInfo();
        Flight flight = new Flight();
        flight.setAirline_id((long) currentId);
        BeanUtils.copyProperties(addFlightDTO, flight);
        flightMapper.insert(flight);
    }

    @Override
    public void deleteFlight(DeleteFlightDTO deleteFlightDTO) {
        BaseContext.removeCurrentInfo();
        List<Long> flightIds = deleteFlightDTO.getFlightIds();
        for (Long flightId : flightIds) {
            flightMapper.deleteById(flightId);
        }
    }

    @Override
    public void updateFlight(PutFlightDTO putFlightDTO) {
        BaseContext.removeCurrentInfo();
        Flight flight = new Flight();
        BeanUtils.copyProperties(putFlightDTO, flight);
        flightMapper.updateById(flight);
    }

    @Override
    public List<FlightVO> findFlight(Long pageNo, Long pageSize) {
        BaseContext.removeCurrentInfo();
        Page<Flight> page = new Page<>(pageNo, pageSize);
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(true);
        page.addOrder(orderItem);
        Page<Flight> p = flightMapper.selectPage(page, null);
        List<Flight> records = p.getRecords();
        List<FlightVO> flightVOS = new ArrayList<>();
        for (Flight flight : records) {
            FlightVO flightVO = new FlightVO();
            BeanUtils.copyProperties(flight, flightVO);
            flightVOS.add(flightVO);
        }
        return flightVOS;
    }

    @Override
    public void addTicket(AddTicketDTO addTicketDTO) {
        BaseContext.removeCurrentInfo();
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(addTicketDTO, ticket);
        ticketMapper.insert(ticket);
    }

    @Override
    public void deleteTicket(DeleteTicketDTO deleteTicketDTO) {
        BaseContext.removeCurrentInfo();
        List<Long> ticketIds = deleteTicketDTO.getTicketIds();
        for (Long ticketId : ticketIds) {
            ticketMapper.deleteById(ticketId);
        }
    }

    @Override
    public void updateTicket(PutTicketDTO putTicketDTO) {
        BaseContext.removeCurrentInfo();
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(putTicketDTO, ticket);
        ticketMapper.updateById(ticket);
    }

    @Override
    public List<FindBuyTicketVO> findTicket(Long pageNo, Long pageSize) {
        BaseContext.removeCurrentInfo();
        Page<Ticket> page = new Page<>(pageNo, pageSize);
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(true);
        page.addOrder(orderItem);
        Page<Ticket> p = ticketMapper.selectPage(page, null);
        List<Ticket> records = p.getRecords();
        List<FindBuyTicketVO> findBuyTicketVOS = new ArrayList<>();
        for (Ticket ticket : records) {
            FindBuyTicketVO findBuyTicketVO = new FindBuyTicketVO();
            BeanUtils.copyProperties(ticket, findBuyTicketVO);
            FlightVO flightVO = new FlightVO();
            Flight flight = flightMapper.selectById(ticket.getFlight_id());
            BeanUtils.copyProperties(flight, flightVO);
            findBuyTicketVO.setFlightVO(flightVO);
            findBuyTicketVOS.add(findBuyTicketVO);
        }
        return findBuyTicketVOS;
    }

}
