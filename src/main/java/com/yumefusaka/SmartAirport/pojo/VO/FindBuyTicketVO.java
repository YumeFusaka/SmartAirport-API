package com.yumefusaka.SmartAirport.pojo.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FindBuyTicketVO {

    private Long id;

    private Long flight_id;

    private String seat_class;

    private int seat_number;

    private String status;

    private Long price;

    private LocalDateTime create_time;

    private LocalDateTime update_time;

    private String flight_number;

    private String departure_city;

    private String arrival_city;

    private LocalDateTime date_of_departure;

    private int estimated_travel_time;
}
