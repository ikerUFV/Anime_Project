package com.ufv.dis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.List;

public class LectorJSON {
    private final String ruta = "src/main/resources/anime.json";

    public List<Anime> leerJson() throws Exception{
                                                  //"Apr 8, 2016, 00:55 (JST)"
        Gson gson = new GsonBuilder().setDateFormat("MMM d, yyyy").create();
        return gson.fromJson(new FileReader(ruta), new TypeToken<List<Anime>>(){}.getType());
    }
}
