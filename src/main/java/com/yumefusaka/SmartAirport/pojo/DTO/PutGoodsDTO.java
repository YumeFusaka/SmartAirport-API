package com.yumefusaka.SmartAirport.pojo.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PutGoodsDTO extends AddGoodsDTO {
    public Long id;
}
