package com.shyam.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shyam.dto.Result;

@Service
public class TestService {

    public Result translate(String text) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        Map<String, String> map = new HashMap<>();
        map.put("text", text);
        map.put("target_languages", "ta,te,fr,it,hi");

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<?> forEntity = restTemplate.postForEntity("http://localhost:5000/translate-multiple/", entity,Result.class);
        System.out.println("Result " + forEntity.getBody());

        // Result result = new Result();
        // result.setFr("french");
        // result.setHi("Hindi");
        // result.setIt("Italian");
        // result.setTa("Tamil");
        // result.setTe("Telugu");

        return (Result) forEntity.getBody();
    }
    
}

// {
//     "text": "working Good moring",
//     "target_languages": "ta,te,fr,it,hi"
//   }
