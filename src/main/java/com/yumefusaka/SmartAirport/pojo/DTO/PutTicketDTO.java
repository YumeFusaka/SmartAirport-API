package com.yumefusaka.SmartAirport.pojo.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PutTicketDTO extends AddTicketDTO {
    long id;
}
