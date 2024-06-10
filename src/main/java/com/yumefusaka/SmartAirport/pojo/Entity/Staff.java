package com.yumefusaka.SmartAirport.pojo.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@TableName("passenger")
@Data
public class Staff extends User {
}
