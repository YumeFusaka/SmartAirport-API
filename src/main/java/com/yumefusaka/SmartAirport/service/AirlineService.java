package com.yumefusaka.SmartAirport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yumefusaka.SmartAirport.pojo.DTO.*;
import com.yumefusaka.SmartAirport.pojo.Entity.Airline;
import com.yumefusaka.SmartAirport.pojo.VO.FindBuyTicketVO;
import com.yumefusaka.SmartAirport.pojo.VO.FlightVO;

import java.util.List;

public interface AirlineService extends IService<Airline> {
    void addFlight(AddFlightDTO addFlightDTO);

    void deleteFlight(DeleteFlightDTO deleteFlightDTO);

    void updateFlight(PutFlightDTO putFlightDTO);

    List<FlightVO> findFlight(Long pageNo, Long pageSize);

    void addTicket(AddTicketDTO addTicketDTO);

    void deleteTicket(DeleteTicketDTO deleteTicketDTO);

    void updateTicket(PutTicketDTO putTicketDTO);

    List<FindBuyTicketVO> findTicket(Long pageNo, Long pageSize);
}
