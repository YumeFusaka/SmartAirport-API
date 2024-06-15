package com.yumefusaka.SmartAirport.controller;

import com.yumefusaka.SmartAirport.service.StaffService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/passenger")
@Tag(name = "工作人员")
public class StaffController {

    @Autowired
    private StaffService staffService;

    
}
