package com.yumefusaka.SmartAirport.pojo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class BuyTicketDTO {
    List<Long> ticketIds;
}
