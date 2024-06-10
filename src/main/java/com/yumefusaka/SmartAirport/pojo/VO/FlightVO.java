package com.yumefusaka.SmartAirport.pojo.VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightVO {

    private Long airline_id;

    private String flight_number;

    private int capacity;

    private String departure_city;

    private String arrival_city;

    private LocalDateTime date_of_departure;

    private int estimated_travel_time;
}
