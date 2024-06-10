package com.yumefusaka.SmartAirport.controller;

import com.yumefusaka.SmartAirport.common.Result;
import com.yumefusaka.SmartAirport.pojo.DTO.AddFlightDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.DeleteFlightDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.PutFlightDTO;
import com.yumefusaka.SmartAirport.pojo.Entity.Flight;
import com.yumefusaka.SmartAirport.pojo.VO.FlightVO;
import com.yumefusaka.SmartAirport.service.AirlineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/airline")
@Tag(name = "航司")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @PostMapping("/flight/add")
    @Operation(summary = "添加航班")
    private Result<String> addFlight(@RequestBody AddFlightDTO addFlightDTO) {
        airlineService.addFlight(addFlightDTO);
        return Result.success("添加航班成功");
    }

    @DeleteMapping("/flight/delete")
    @Operation(summary = "删除航班")
    private Result<String> deleteFlight(@RequestBody DeleteFlightDTO deleteFlightDTO) {
        airlineService.deleteFlight(deleteFlightDTO);
        return Result.success("删除航班成功");
    }

    @PutMapping("/flight/update")
    @Operation(summary = "更新航班")
    private Result<String> updateFlight(@RequestBody PutFlightDTO putFlightDTO) {
        airlineService.updateFlight(putFlightDTO);
        return Result.success("更新航班成功");
    }

    @GetMapping("/flight/find")
    @Operation(summary = "查询航班")
    public Result<List<FlightVO>> findFlight(@RequestParam Long pageNo, @RequestParam Long pageSize) {
        List<FlightVO> flightVOList = airlineService.findFlight(pageNo, pageSize);
        return Result.success(flightVOList);
    }
}
