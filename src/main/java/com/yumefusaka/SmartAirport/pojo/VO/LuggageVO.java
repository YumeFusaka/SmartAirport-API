package com.yumefusaka.SmartAirport.pojo.VO;

import lombok.Data;

@Data
public class LuggageVO {
    private Long id;

    private String name;

    private String description;

    private long passenger_id;

    private int number;

    private String category;
}
