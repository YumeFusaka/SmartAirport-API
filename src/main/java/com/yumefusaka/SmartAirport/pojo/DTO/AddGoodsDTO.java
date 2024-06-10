package com.yumefusaka.SmartAirport.pojo.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddGoodsDTO {

    private String name;

    private String description;

    private BigDecimal price;

    private Long stock;

    private String category;
}
