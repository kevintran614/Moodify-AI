package com.example.Moodify_AI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

        String spotifyAuthUrl = UriComponentsBuilder.fromHttpUrl("https://accounts.spotify.com/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", clientId)
                .queryParam("scope", scope)
                .queryParam("redirect_uri", redirectUrl)
                .queryParam("state", state)
                .build()
                .toUriString();


        return ResponseEntity.ok("emotion");
    }

    // 2) Request an Access Token
}
