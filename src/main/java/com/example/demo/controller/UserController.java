package com.example.demo.controller;

import com.example.demo.dto.TsRequest;
import com.example.demo.util.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//log
//change somthing 
@RestController
public class UserController {
    private static final String USER="api/3.9/sites/507f2aa0-a887-4f27-9ef6-5a5be17e0517/users";
    private static final String GROUP="api/3.9/sites/507f2aa0-a887-4f27-9ef6-5a5be17e0517/groups";
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
