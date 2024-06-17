package com.yumefusaka.SmartAirport.pojo.DTO;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddTicketDTO {

    private long flight_id;

    private String seat_class;

    private int seat_number;

    private String status;

    private long price;
}
