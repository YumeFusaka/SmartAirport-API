package com.yumefusaka.SmartAirport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyTicketDTO;
import com.yumefusaka.SmartAirport.pojo.Entity.Passenger;
import com.yumefusaka.SmartAirport.pojo.VO.BuyGoodsVO;
import com.yumefusaka.SmartAirport.pojo.VO.FindBuyTicketVO;
import com.yumefusaka.SmartAirport.pojo.VO.LuggageVO;

import java.util.List;

public interface PassengerService extends IService<Passenger> {
    List<FindBuyTicketVO> findBuyTicket(Long pageNo, Long pageSize);

    void buyTicket(BuyTicketDTO buyTicketDTO);

    List<FindBuyTicketVO> findBuyTicketHistory(Long pageNo, Long pageSize);

    List<BuyGoodsVO> findBuyGoods(Long pageNo, Long pageSize);

    void buyGoods(BuyGoodsDTO buyGoodsDTO);


    List<LuggageVO> findLuggageHistory(Long pageNo, Long pageSize);
}
