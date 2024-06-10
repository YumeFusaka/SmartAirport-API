package com.yumefusaka.SmartAirport.pojo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DeleteTicketDTO {
    List<Long> ticketIds;
}
