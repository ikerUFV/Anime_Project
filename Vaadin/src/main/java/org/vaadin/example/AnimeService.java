package org.vaadin.example;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class AnimeService implements Serializable {
    public String leeAnime() throws Exception{
        API api = new API();
        return api.getAnimeList();
    }
}
