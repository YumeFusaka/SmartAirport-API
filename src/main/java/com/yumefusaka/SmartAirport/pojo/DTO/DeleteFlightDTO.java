package com.yumefusaka.SmartAirport.pojo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DeleteFlightDTO {

    List<Long> flightIds;
}
