package com.example.Moodify_AI.controller;

import com.example.Moodify_AI.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {
        return authService.requestAuthCodeUri();
    }

    @GetMapping("/access-token")
    public String accessToken(@RequestParam("code") String code) {
        return authService.requestAccessToken(code);
    }
}
