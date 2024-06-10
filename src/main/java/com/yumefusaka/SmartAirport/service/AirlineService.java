package com.yumefusaka.SmartAirport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yumefusaka.SmartAirport.pojo.DTO.AddFlightDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.DeleteFlightDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.PutFlightDTO;
import com.yumefusaka.SmartAirport.pojo.Entity.Airline;
import com.yumefusaka.SmartAirport.pojo.VO.FlightVO;

import java.util.List;

public interface AirlineService extends IService<Airline> {
    void addFlight(AddFlightDTO addFlightDTO);

    void deleteFlight(DeleteFlightDTO deleteFlightDTO);

    void updateFlight(PutFlightDTO putFlightDTO);

    List<FlightVO> findFlight(Long pageNo, Long pageSize);
}
