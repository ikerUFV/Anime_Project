package com.ufv.dis;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    List<Anime> listaAnime = new ArrayList<>();

    public void setListaAnime() throws Exception {
        listaAnime = new LectorJSON().leerJson();
    }

    @GetMapping("/animes")
    public List<Anime> getAnimeList() throws Exception {
        if(listaAnime.size() == 0)
            setListaAnime();
        return listaAnime;
    }

    @GetMapping("/animes/{id}")
    public Anime getAnime(@PathVariable int id) throws Exception {
        if(listaAnime.size() == 0)
            setListaAnime();
        return listaAnime.get(id);
    }
}
