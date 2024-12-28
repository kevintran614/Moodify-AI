package com.example.Moodify_AI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.SecureRandom;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    @Value("${spotify.client.id")
    private String clientId;

    @Autowired
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 1) Request User Authorization
    public String generateRandomStrike(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder res = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            res.append(characters.charAt(random.nextInt(characters.length())));
        }

        return res.toString();
    }

    public ResponseEntity<String> requestUserAuth() {
        String redirectUrl = "http://localhost:8080/callback";
        String state = generateRandomStrike(16);
        String scope = "user-top-read";

        String authUrl = "https://accounts.spotify.com/authorize?" +
                "response_type=code" +
                "&client_id=" + clientId +
                "&scope=" + scope +
                "&redirect_uri=" + redirectUrl +
                "&state=" + state;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", authUrl);

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    // 2) Request an Access Token
}
