package com.example.demo;

import com.example.demo.dto.Credentials;
import com.example.demo.dto.Login;
import com.example.demo.dto.Site;
import com.example.demo.util.RestClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {
    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private RestClient restClient;

    @Test
    public void testLogin(){
        Login login = new Login();
        Credentials credentials = new Credentials("toppan-ad","Aa@123456");
        Site site = new Site("sa");
        credentials.setSite(site);
        login.setCredentials(credentials);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Login> httpEntity = new HttpEntity<>(login,headers);
        Mockito.when(restTemplate.postForEntity("http://192.168.7.18/api/3.9/auth/signin",httpEntity,String.class))
        .thenReturn(ResponseEntity.ok().build());
        Assert.assertEquals(HttpStatus.OK,restClient.login("api/3.9/auth/signin",login).getStatusCode());
    }
}
