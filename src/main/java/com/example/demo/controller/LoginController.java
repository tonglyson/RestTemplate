package com.example.demo.controller;

import com.example.demo.dto.Login;
import com.example.demo.util.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    @Autowired
    private RestClient restClient;
    private static final String URL = "api/3.9/auth/signin";

    @PostMapping(path = "/login",consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> login(@RequestBody Login login){
        return restClient.login(URL,login);
    }
}
