package com.example.demo.util;

import com.example.demo.dto.Login;
import com.example.demo.dto.TsRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXB;
import java.io.StringWriter;

@Component
public class RestClient {
    final static Logger logger = Logger.getLogger(RestClient.class);
    @Value("${tableau.version}")
    private String version;

    @Value("${tableau.site.id}")
    private String siteId;

    @Value("${tableau.base.url}")
    private String baseUrl;

    @Value("${tableau.auth.header}")
    private String authHeader;

    @Value("${tableau.token.acess}")

    private String tokenAccess;

    private RestTemplate restTemplate;

    public RestClient(){
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<String> login(String url, Login login){
        String mainUrl = baseUrl+url;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Login> httpEntity = new HttpEntity<>(login,headers);
        return restTemplate.postForEntity(mainUrl,httpEntity,String.class,version,siteId);
    }

    public ResponseEntity<String> createGroup(String url, TsRequest tsRequest){
        String mainUrl = baseUrl + url;
        String xmlString = convertToXmlString(tsRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
        headers.add(authHeader,tokenAccess);
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> httpEntity = new HttpEntity<>(xmlString,headers);
        return restTemplate.postForEntity(mainUrl,httpEntity,String.class,version,siteId);
    }

    public ResponseEntity<String> getAllGroups(String url){
        String mainUrl = baseUrl + url;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
        headers.add(authHeader,tokenAccess);
        HttpEntity<String> httpEntity = new HttpEntity(headers);
        return restTemplate.exchange(mainUrl,HttpMethod.GET,httpEntity,String.class,version,siteId);
    }

    public ResponseEntity<String> getAllUser(String url){
        String mainUrl = baseUrl+url;
        HttpHeaders headers = new HttpHeaders();
        headers.add(authHeader,tokenAccess);
        headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return  restTemplate.exchange(mainUrl, HttpMethod.GET,entity,String.class,version,siteId);
    }

    public ResponseEntity<String> updateUser(String url, String id, TsRequest user){
        logger.info(id);
        String mainUrl = baseUrl + url+"/"+id;
        HttpHeaders headers= new HttpHeaders();
        headers.add(authHeader,tokenAccess);
        headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_XML);
        //convert to XML String
        String xmlString = convertToXmlString(user);
        HttpEntity<String> httpEntity = new HttpEntity<>(xmlString,headers);
         restTemplate.put(mainUrl,httpEntity,version,siteId);
         return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> addUserToGroup(String url,TsRequest tsRequest,String groupId){
        String mainUrl = baseUrl + url+"/"+groupId+"/users";
        HttpHeaders headers = new HttpHeaders();
        headers.add(authHeader,tokenAccess);
        headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_XML);
        String xmlString = convertToXmlString(tsRequest);
        HttpEntity<String> httpEntity = new HttpEntity<>(xmlString,headers);
        return restTemplate.postForEntity(mainUrl,httpEntity,String.class,version,siteId);
    }

    public ResponseEntity<String> deleteUserFromSite(String url,String userId){
        String mainUrl = baseUrl + url +"/" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.add(authHeader,tokenAccess);
        HttpEntity httpEntity = new HttpEntity(headers);
        return restTemplate.exchange(mainUrl,HttpMethod.DELETE,httpEntity,String.class,version,siteId);
    }

    public ResponseEntity<String> addUserToSite(String url,TsRequest tsRequest){
        String mainUrl =baseUrl + url;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
        headers.add(authHeader,tokenAccess);
        headers.setContentType(MediaType.APPLICATION_XML);
        String xmlString = convertToXmlString(tsRequest);
        HttpEntity<String> httpEntity = new HttpEntity<>(xmlString,headers);
        return restTemplate.postForEntity(mainUrl,httpEntity,String.class,version,siteId);
    }

    private String convertToXmlString(TsRequest tsRequest){
        StringWriter stringWriter = new StringWriter();
        JAXB.marshal(tsRequest, stringWriter);
        return stringWriter.toString();
    }
}
