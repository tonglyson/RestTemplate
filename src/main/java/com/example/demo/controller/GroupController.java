package com.example.demo.controller;

import com.example.demo.dto.TsRequest;

import com.example.demo.util.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import java.io.StringWriter;

@RestController
public class GroupController {
    @Autowired
    private RestClient restClient;

    private static final String ALL_GROUPS="api/3.9/sites/507f2aa0-a887-4f27-9ef6-5a5be17e0517/groups";

    @GetMapping("/groups")
    public ResponseEntity<String> getAllGroups(){
      return restClient.getAllGroups(ALL_GROUPS);
    }
    @PostMapping(value = "groups",consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> addGroups(@RequestBody TsRequest tsRequest) {
        return restClient.createGroup(ALL_GROUPS,tsRequest);
    }
}
