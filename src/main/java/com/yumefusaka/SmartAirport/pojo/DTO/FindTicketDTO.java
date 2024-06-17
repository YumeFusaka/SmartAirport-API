package com.yumefusaka.SmartAirport.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yumefusaka.SmartAirport.common.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class FindTicketDTO extends Page {
    public String flight_number;

    public String departure_city;

    public String arrival_city;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date_of_departure;

    public int estimated_travel_time;

    public int capacity;

    private long price;

    private String seat_class;

    private int seat_number;

}
