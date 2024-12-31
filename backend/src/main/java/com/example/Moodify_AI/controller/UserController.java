package com.example.Moodify_AI.controller;


import com.example.Moodify_AI.service.AuthService;
import com.example.Moodify_AI.service.EmotionService;
import com.example.Moodify_AI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Artist;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-top-artists")
    public Artist[] healthCheck() {
        return userService.getUsersTopArtists();
    }
}
