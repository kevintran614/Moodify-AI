package com.example.Moodify_AI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenerateEmotionService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> generateEmotion(String text) {
        String getEmotionUrl = "http://127.0.0.1:5000/generate-emotion-from-text";
        String emotion = restTemplate.getForObject(getEmotionUrl, String.class);
        return ResponseEntity.ok(emotion);
    }
}
