package com.example.hrms.service;
import com.example.hrms.dto.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LLMService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ChatResponse askQuestion(String message) {

        String url = "http://localhost:11434/api/generate";

        Map<String, Object> requestBody = Map.of(
                "model", "llama3",
                "prompt", message,
                "stream", false
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(
                        url,
                        entity,
                        Map.class);

        String answer =
                (String) response.getBody()
                        .get("response");

        return new ChatResponse(answer);
    }
}