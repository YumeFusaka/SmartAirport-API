package com.yumefusaka.SmartAirport.pojo.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@TableName("airline")
@Data
public class Airline extends User{
}
