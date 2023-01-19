package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class AnimeService implements Serializable {
    public List<Anime> leeAnime() throws Exception{
        API api = new API();
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
        List<Anime> lista = gson.fromJson(api.getAnimeList(), new TypeToken<List<Anime>>(){}.getType());

        return lista;
    }

    public Anime leeUnAnime(int id) throws Exception {
        API api = new API();
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();

        Anime anime = gson.fromJson(api.getAnime(id), Anime.class);

        return anime;
    }

    public String addAnime(Anime anime) throws Exception {
        API api = new API();

        String response = api.postAnime(anime);

        return response;
    }

    public String editAnime(int id, Anime anime) throws Exception {
        API api = new API();

        String response = api.editAnime(id, anime);

        return response;
    }
}
