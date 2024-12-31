package com.example.Moodify_AI.service;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.io.IOException;
import java.net.URI;
import java.security.SecureRandom;

@Service
public class AuthService {

    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    private SpotifyApi spotifyApi;

    private AuthorizationCodeUriRequest authorizationCodeUriRequest;

    private AuthorizationCodeRequest authorizationCodeRequest;

    private AuthorizationCodeCredentials authorizationCodeCredentials;

    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder res = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            res.append(characters.charAt(random.nextInt(characters.length())));
        }

        return res.toString();
    }

    public String requestAuthCodeUri() {
        URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/auth/access-token");

        this.spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectUri)
                .build();

        this.authorizationCodeUriRequest = this.spotifyApi.authorizationCodeUri()
                .state(generateRandomString(16))
                .scope("user-top-read")
                .show_dialog(true)
                .build();

        URI uri = this.authorizationCodeUriRequest.execute();

        return uri.toString();
    }

    public String requestAccessToken(String code) {
        this.authorizationCodeRequest = this.spotifyApi.authorizationCode(code)
                .build();

        try {
            this.authorizationCodeCredentials = this.authorizationCodeRequest.execute();

            this.spotifyApi.setAccessToken(this.authorizationCodeCredentials.getAccessToken());
            this.spotifyApi.setRefreshToken(this.authorizationCodeCredentials.getRefreshToken());
            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
        }
        catch (IOException | ParseException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return this.authorizationCodeCredentials.getAccessToken();
    }

    public SpotifyApi getSpotifyApi() {
        return this.spotifyApi;
    }
}