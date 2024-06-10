package com.yumefusaka.SmartAirport.controller;

import com.yumefusaka.SmartAirport.common.Result;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyTicketDTO;
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

    @GetMapping("/ticket/find")
    @Operation(summary = "查询可购买机票")
    public Result<List<FindBuyTicketVO>> findBuyTicket(@RequestParam Long pageNo, @RequestParam Long pageSize) {
        List<FindBuyTicketVO> findBuyTicketVOS = passengerService.findBuyTicket(pageNo, pageSize);
        return Result.success(findBuyTicketVOS);
    }

    @PostMapping("/ticket/buy")
    @Operation(summary = "购买机票")
    public Result<String> buyTicket(@RequestBody BuyTicketDTO buyTicketDTO) {
        passengerService.buyTicket(buyTicketDTO);
        return Result.success("购票成功");
    }

    @GetMapping("/ticket/history")
    @Operation(summary = "查询购买机票历史")
    public Result<List<FindBuyTicketVO>> findBuyTicketHistory(@RequestParam Long pageNo, @RequestParam Long pageSize) {
        List<FindBuyTicketVO> findBuyTicketVOS = passengerService.findBuyTicketHistory(pageNo, pageSize);
        return Result.success(findBuyTicketVOS);
    }

    @GetMapping("/goods/find")
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


    @GetMapping("/luggage/find")
    @Operation(summary = "查询行李")
    public Result<List<LuggageVO>> findLuggage(@RequestParam Long pageNo, @RequestParam Long pageSize) {
        List<LuggageVO> luggageVOS = passengerService.findLuggageHistory(pageNo, pageSize);
        return Result.success(luggageVOS);
    }

}
