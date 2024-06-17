package com.yumefusaka.SmartAirport.pojo.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("ticket")
public class Ticket {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "flight_id")
    private Long flight_id;

    @TableField(value = "passenger_id")
    private Long passenger_id;

    @TableField(value = "seat_class")
    private String seat_class;

    @TableField(value = "seat_number")
    private int seat_number;

    @TableField(value = "status")
    private String status;

    @TableField(value = "price")
    private Long price;

    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime create_time;

    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime update_time;


    @TableField(value = "flight_number")
    private String flight_number;

    @TableField(value = "departure_city")
    private String departure_city;

    @TableField(value = "arrival_city")
    private String arrival_city;

    @TableField(value = "date_of_departure")
    private LocalDateTime date_of_departure;

    @TableField(value = "estimated_travel_time")
    private int estimated_travel_time;
}