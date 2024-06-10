package com.yumefusaka.SmartAirport.pojo.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("ticket")
public class Ticket {

    @TableId(value = "id")
    private Long id;

    @TableField(value = "flight_id")
    private Long flight_id;

    @TableField(value = "passenger_id")
    private Long passenger_id;

    @TableField(value = "seat_class")
    private String seat_class;

    @TableField(value = "seat_number")
    private String seat_number;

    @TableField(value = "status")
    private String status;

    @TableField(value = "price")
    private BigDecimal price;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime created_time;

    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private LocalDateTime updated_time;
}