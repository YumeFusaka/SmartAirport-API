package com.yumefusaka.SmartAirport.controller;

import com.yumefusaka.SmartAirport.common.Result;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyTicketDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.FindTicketDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.HistoryTicketDTO;
import com.yumefusaka.SmartAirport.pojo.VO.BuyGoodsVO;
import com.yumefusaka.SmartAirport.pojo.VO.FindBuyTicketVO;
import com.yumefusaka.SmartAirport.pojo.VO.LuggageVO;
import com.yumefusaka.SmartAirport.service.GoodsService;
import com.yumefusaka.SmartAirport.service.PassengerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/passenger")
@Tag(name = "旅客")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/ticket/find")
    @Operation(summary = "查询可购买机票")
    public Result<List<FindBuyTicketVO>> findBuyTicket(@RequestBody FindTicketDTO findTicketDTO) {
        List<FindBuyTicketVO> findBuyTicketVOS = passengerService.findBuyTicket(findTicketDTO);
        return Result.success(findBuyTicketVOS);
    }

    @PostMapping("/ticket/buy")
    @Operation(summary = "购买机票")
    public Result<String> buyTicket(@RequestBody BuyTicketDTO buyTicketDTO) {
        passengerService.buyTicket(buyTicketDTO);
        return Result.success("购票成功");
    }

    @PostMapping("/ticket/history")
    @Operation(summary = "查询购买机票历史")
    public Result<List<FindBuyTicketVO>> findBuyTicketHistory(@RequestBody HistoryTicketDTO historyTicketDTO) {
        List<FindBuyTicketVO> findBuyTicketVOS = passengerService.findBuyTicketHistory(historyTicketDTO);
        return Result.success(findBuyTicketVOS);
    }

    @PostMapping("/ticket/history/count")
    @Operation(summary = "查询历史机票数量")
    public Result<Long> countTicket(@RequestBody HistoryTicketDTO historyTicketDTO) {
        log.info("countTicket:{}", historyTicketDTO);
        long count = passengerService.countHistoryTicket(historyTicketDTO);
        return Result.success(count);
    }

    @PostMapping("/goods/find")
    @Operation(summary = "查询可购买商品")
    public Result<List<BuyGoodsVO>> findBuyGoods(@RequestParam Long pageNo, @RequestParam Long pageSize) {
        List<BuyGoodsVO> buyGoodsVOS = passengerService.findBuyGoods(pageNo, pageSize);
        return Result.success(buyGoodsVOS);
    }

    @PostMapping("/goods/buy")
    @Operation(summary = "购买商品")
    public Result<String> buyGoods(@RequestBody BuyGoodsDTO buyGoodsDTO) {
        passengerService.buyGoods(buyGoodsDTO);
        return Result.success("购买成功");
    }


    @PostMapping("/luggage/find")
    @Operation(summary = "查询行李")
    public Result<List<LuggageVO>> findLuggage(@RequestParam Long pageNo, @RequestParam Long pageSize) {
        List<LuggageVO> luggageVOS = passengerService.findLuggageHistory(pageNo, pageSize);
        return Result.success(luggageVOS);
    }

    @PostMapping("/ticket/count")
    @Operation(summary = "查询机票数量")
    public Result<Long> countTicket(@RequestBody FindTicketDTO findTicketDTO) {
        log.info("countTicket:{}", findTicketDTO);
        long count = passengerService.countTicket(findTicketDTO);
        return Result.success(count);
    }


}
