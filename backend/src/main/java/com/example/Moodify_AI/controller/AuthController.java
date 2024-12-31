package com.example.Moodify_AI.controller;

import com.example.Moodify_AI.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
        String accessToken =  authService.requestAccessToken(code);

        return "access token generated: " + accessToken;
    }
}
