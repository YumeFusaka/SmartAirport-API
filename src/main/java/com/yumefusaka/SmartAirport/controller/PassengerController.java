package com.yumefusaka.SmartAirport.controller;

import com.yumefusaka.SmartAirport.common.Result;
import com.yumefusaka.SmartAirport.pojo.VO.FindBuyTicketVO;
import com.yumefusaka.SmartAirport.service.PassengerService;
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
    public Result<List<FindBuyTicketVO>> findBuyTicket(@RequestParam Long pageNo, @RequestParam Long pageSize) {
        List<FindBuyTicketVO> findBuyTicketVOS = passengerService.findBuyTicket(pageNo, pageSize);
        return Result.success(findBuyTicketVOS);
    }

    @PostMapping("/ticket/buy")
    public Result<String> buyTicket(@RequestBody FindBuyTicketVO buyTicketDTO) {
        passengerService.buyTicket(buyTicketDTO);
        return Result.success();
    }
}
