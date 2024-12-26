package com.example.Moodify_AI.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class GenerateSongFromEmotionController {

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Spring Boot Health Check";
    }

    @GetMapping("/generate-emotion-from-text")
    public ResponseEntity<String> generateEmotionFromText(@RequestBody String text) {
        return ResponseEntity.ok(text);
    }
}
