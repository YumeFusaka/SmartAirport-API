package com.yumefusaka.SmartAirport.pojo.DTO;


import lombok.Data;

@Data
public class UserRegisterDTO {

    public int id;

    public String username;

    public String password;

    private String email;

    private String name;

    private String phone;

    private String address;

    private String identity;
}
