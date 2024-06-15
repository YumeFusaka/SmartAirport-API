package com.yumefusaka.SmartAirport.pojo.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyGoodsVO {

    private Long id;

    private String name;

    private String description;

    private Long price;

    private Long stock;

    private String category;
}
