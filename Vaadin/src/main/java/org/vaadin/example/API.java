package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class API {
    private static final String urlPrefix = "http://localhost:8080/%s/%s";

    public String getAnime(int id) throws Exception{
        String url = String.format(urlPrefix, "animes", id);

        // Montamos la request
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();

        // La mandamos
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String getAnimeList() throws Exception{
        String url = String.format(urlPrefix, "animes", "");

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();

        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String postAnime(Anime anime) throws Exception {
        String url = String.format(urlPrefix, "animes", "");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JsonObject jsonObject = gson.toJsonTree(anime).getAsJsonObject();
        System.out.println(jsonObject.toString());
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString())).header("Content-type", "application/json").build();

        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String editAnime(int id, Anime anime) throws Exception {
        String url = String.format(urlPrefix, "animes", id);

        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();

        JsonObject jsonObject = gson.toJsonTree(anime).getAsJsonObject();

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString())).build();

        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
