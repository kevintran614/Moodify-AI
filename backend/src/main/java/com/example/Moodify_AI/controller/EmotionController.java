package com.example.Moodify_AI.controller;

import com.example.Moodify_AI.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/emotion")
public class EmotionController {

    private final EmotionService generateEmotionService;

    @Autowired
    public EmotionController(EmotionService generateEmotionService) {
        this.generateEmotionService = generateEmotionService;
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Spring Boot Health Check";
    }

    @PostMapping("/generate-emotion-from-text")
    public ResponseEntity<String> generateEmotionFromText(@RequestBody String text) {
        return generateEmotionService.generateEmotion(text);
    }
}