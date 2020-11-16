package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Credentials {
    private String name;
    private String password;
    private Site site;
    public Credentials(String name,String password){
        this.name = name;
        this.password = password;
    }
}
