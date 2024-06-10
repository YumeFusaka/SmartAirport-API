package com.yumefusaka.SmartAirport.pojo.DTO;

import lombok.Data;

@Data
public class UserLoginDTO {

    public String username;

    public String password;

    public String identity;
}