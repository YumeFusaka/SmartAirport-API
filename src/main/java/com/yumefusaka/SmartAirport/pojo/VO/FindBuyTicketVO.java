package com.yumefusaka.SmartAirport.pojo.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FindBuyTicketVO {

    private Long id;

    private Long flight_id;

    private String seat_class;

    private String seat_number;

    private String status;

    private Long price;

    private LocalDateTime create_time;

    private LocalDateTime update_time;

    private FlightVO flightVO;
}
