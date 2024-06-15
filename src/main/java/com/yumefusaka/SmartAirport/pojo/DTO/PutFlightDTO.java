package com.yumefusaka.SmartAirport.pojo.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PutFlightDTO extends AddFlightDTO {
    private Long id;
}
