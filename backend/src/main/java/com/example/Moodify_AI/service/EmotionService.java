package com.example.Moodify_AI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmotionService {

    private final RestTemplate restTemplate;

    @Autowired
    public EmotionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> generateEmotion(String text) {
        String getEmotionUrl = "http://127.0.0.1:5000/generate-emotion-from-text";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(text, headers);

        String emotion = restTemplate.postForObject(getEmotionUrl, entity, String.class);

        return ResponseEntity.ok(emotion);
    }
}
