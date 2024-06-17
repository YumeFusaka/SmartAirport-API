package com.yumefusaka.SmartAirport.pojo.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddFlightDTO {
    private Long airline_id;

    private String flight_number;

    private int capacity;

    private String departure_city;

    private String arrival_city;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date_of_departure;

    private int estimated_travel_time;
}
