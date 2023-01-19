package org.vaadin.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class API {
    private static final String urlPrefix = "http://localhost/8080";

    public String getAnime(int id) throws Exception{
        String url = String.format(urlPrefix, "/animes/", id);

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();

        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String getAnimeList() throws Exception{
        String url = String.format(urlPrefix, "/animes");

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();

        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
