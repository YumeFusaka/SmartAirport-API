package com.yumefusaka.SmartAirport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyTicketDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.FindTicketDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.HistoryTicketDTO;
import com.yumefusaka.SmartAirport.pojo.Entity.Passenger;
import com.yumefusaka.SmartAirport.pojo.VO.BuyGoodsVO;
import com.yumefusaka.SmartAirport.pojo.VO.FindBuyTicketVO;
import com.yumefusaka.SmartAirport.pojo.VO.LuggageVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PassengerService extends IService<Passenger> {
    List<FindBuyTicketVO> findBuyTicket(@RequestBody FindTicketDTO findTicketDTO);

    void buyTicket(BuyTicketDTO buyTicketDTO);

    List<FindBuyTicketVO> findBuyTicketHistory(HistoryTicketDTO historyTicketDTO);

    List<BuyGoodsVO> findBuyGoods(Long pageNo, Long pageSize);

    void buyGoods(BuyGoodsDTO buyGoodsDTO);


    List<LuggageVO> findLuggageHistory(Long pageNo, Long pageSize);

    long countTicket(FindTicketDTO findTicketDTO);

    long countHistoryTicket(HistoryTicketDTO historyTicketDTO);

    long countLuggage();
}
