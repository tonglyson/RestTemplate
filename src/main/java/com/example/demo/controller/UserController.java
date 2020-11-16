package com.example.demo.controller;

import com.example.demo.dto.TsRequest;
import com.example.demo.util.RestClient;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private static final String USER="api/{version}/sites/{siteId}/users";
    private static final String GROUP="api/{version}/sites/{siteId}/groups";
    @Autowired
    private RestClient restClient;
    @GetMapping("users")
    public ResponseEntity<String> getALlUser(){
        return restClient.getAllUser(USER);
    }
    @PutMapping("users/{userId}")
    public ResponseEntity<String> updateUser(@RequestBody TsRequest user, @PathVariable String userId){
        return restClient.updateUser(USER,userId,user);
    }
    @PostMapping(path = "users/groups/{groupId}",consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> addUserToGroup(@RequestBody TsRequest user,
                                                 @PathVariable String groupId){
        return restClient.addUserToGroup(GROUP,user,groupId);
    }
    @PostMapping(path = "users",consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> addUserToSite(@RequestBody TsRequest user){
        return restClient.addUserToSite(USER,user);
    }
    @DeleteMapping("users/{userId}")
    public ResponseEntity<String> deleteUserFromSite(@PathVariable String userId){
        return restClient.deleteUserFromSite(USER,userId);
    }

}
