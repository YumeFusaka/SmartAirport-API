package com.yumefusaka.SmartAirport.pojo.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class PutTicketDTO {
    long id;

    long price;

    private String seat_class;

    private int seat_number;
}
