package com.example.demo.dto;

import lombok.Data;

@Data
public class Credentials {
    private String name;
    private String password;
    private Site site;
}
