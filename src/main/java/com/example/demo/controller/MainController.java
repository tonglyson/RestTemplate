package com.example.demo.controller;

import com.example.demo.dto.group.TsRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@RestController
public class MainController {
    private static final String URL = "https://prod-apnortheast-a.online.tableau.com/api/3.9/auth/signin";
    private static final String ALL_GROUPS="https://prod-apnortheast-a.online.tableau.com/api/3.9/" +
                          "sites/a244099e-52f1-460b-beed-95e37011e6b1/groups";
    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody JsonNode login) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JsonNode> requestBody = new HttpEntity<>(login,headers);
        return restTemplate.postForEntity(URL,requestBody,String.class);
    }
    @GetMapping("/groups")
    public ResponseEntity<String> getAllGroups(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
        headers.add("X-Tableau-Auth","w5-7VHKDRq-p7uShbHudcQ|i2WV09dHvsMcN2qNgVVkYEmhkv0zy2os");
        HttpEntity<String> entity = new HttpEntity<>(headers);
       return  restTemplate.exchange(ALL_GROUPS, HttpMethod.GET,entity,String.class);
    }
    @PostMapping(value = "groups")
    public ResponseEntity<String> addGroups(@RequestBody TsRequest tsRequest) throws JAXBException {
        RestTemplate restTemplate = new RestTemplate();
        //convert to XML String
        StringWriter stringWriter = new StringWriter();
        JAXB.marshal(tsRequest, stringWriter);
        String xmlString = stringWriter.toString();
        //config Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Tableau-Auth","b28Ex6FZTWi-nAPxrP0FqA|8mfktHTAZHJGSiOp31NTj3Ms9nDJHJi8");
        headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> httpEntity = new HttpEntity<>(xmlString,headers);
        return restTemplate.postForEntity(ALL_GROUPS,httpEntity,String.class);
    }
}
