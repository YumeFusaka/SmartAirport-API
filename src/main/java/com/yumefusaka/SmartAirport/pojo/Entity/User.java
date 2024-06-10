package com.yumefusaka.SmartAirport.pojo.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    public Integer id;

    @TableField(value = "username")
    public String username;

    @TableField(value = "password")
    public String password;

    @TableField("email")
    private String email;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("address")
    private String address;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime create_time;

    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime update_time;
}