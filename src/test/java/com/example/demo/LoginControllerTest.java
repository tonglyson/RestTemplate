package com.example.demo;

import com.example.demo.controller.LoginController;
import com.example.demo.dto.Credentials;
import com.example.demo.dto.Login;
import com.example.demo.dto.Site;
import com.example.demo.util.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.xml.bind.JAXB;
import java.io.StringWriter;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RestClient restClient;
    @Test
    public void testLogin() throws Exception {
        Login login = new Login();
        Credentials credentials = new Credentials("toppan-ad","Aa@123456");
        Site site = new Site("sa");
        credentials.setSite(site);
        login.setCredentials(credentials);
        String url = "api/3.9/auth/signin";
        String rs = "{\n" +
                "    \"credentials\": {\n" +
                "        \"site\": {\n" +
                "            \"id\": \"507f2aa0-a887-4f27-9ef6-5a5be17e0517\",\n" +
                "            \"contentUrl\": \"sa\"\n" +
                "        },\n" +
                "        \"user\": {\n" +
                "            \"id\": \"7b39c880-044e-4073-94fc-1b977acec7bd\"\n" +
                "        },\n" +
                "        \"token\": \"Bw0Wvgi5TO6eYHi2PH5UHQ|kj8QN6pvLeZQL0BjeGA6kLWmMLTf1ISZ\"\n" +
                "    }\n" +
                "}";
        StringWriter stringWriter = new StringWriter();
        JAXB.marshal(login, stringWriter);
        String data =  stringWriter.toString();
//        Mockito.when(restClient.login(url,login)).thenReturn(ResponseEntity.ok().build());
        BDDMockito.given(restClient.login(url,login)).willReturn(new ResponseEntity<>(rs,HttpStatus.OK));
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(data))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.credentials.site.id", CoreMatchers.is("507f2aa0-a887-4f27-9ef6-5a5be17e0517")));
    }
}
