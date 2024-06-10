package com.yumefusaka.SmartAirport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yumefusaka.SmartAirport.pojo.Entity.Passenger;
import com.yumefusaka.SmartAirport.pojo.VO.FindBuyTicketVO;

import java.util.List;

public interface PassengerService extends IService<Passenger> {
    List<FindBuyTicketVO> findBuyTicket(Long pageNo, Long pageSize);

    void buyTicket(FindBuyTicketVO findBuyTicketVO);
}
