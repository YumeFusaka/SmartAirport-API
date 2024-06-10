package com.yumefusaka.SmartAirport.pojo.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("flight")
public class Flight {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "airline_id")
    private Long airline_id;

    @TableField(value = "flight_number")
    private String flight_number;

    @TableField(value = "capacity")
    private int capacity;

    @TableField(value = "departure_city")
    private String departure_city;

    @TableField(value = "arrival_city")
    private String arrival_city;

    @TableField(value = "date_of_departure")
    private LocalDateTime date_of_departure;

    @TableField(value = "estimated_travel_time")
    private int estimated_travel_time;
}