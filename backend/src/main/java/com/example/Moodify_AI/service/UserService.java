package com.example.Moodify_AI.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;

import java.io.IOException;

@Service
public class UserService {

    private final AuthService authService;

    @Autowired
    public UserService(AuthService authService) {
        this.authService = authService;
    }

    public Artist[] getUsersTopArtists() {
        final SpotifyApi spotifyApi = authService.getSpotifyApi();

        final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
                .limit(10)
                .offset(0)
                .time_range("medium_term")
                .build();

        try {
            final Paging<Artist> artistPaging = getUsersTopArtistsRequest.execute();

            return artistPaging.getItems();
        } catch (IOException | SpotifyWebApiException | ParseException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return new Artist[0];
    }
}
