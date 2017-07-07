package com.example.rayan;

import com.example.rayan.entity.Doctor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

import static org.junit.Assert.assertThat;

/**
 * Created by Lycus 01 on 7/7/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginSectionTesting {



//    // login the user
//    @Test
//    private void getUserDetail() {
//        ClientHttpRequestFactory requestFactory = getClientHttpRequestFactory();
//        System.out.println("\nTesting Login User API----------");
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<Object> request = new HttpEntity<Object>(null, getHeaders());
//        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/account/login", request, Doctor.class);
//        System.out.println("Location : "+uri.toASCIIString());
//
//       // assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
//    }


}
