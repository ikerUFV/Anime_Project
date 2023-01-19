package com.ufv.dis;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class ControllerTest {
    Controller controlador;

    @Before
    public void SetUp(){
        controlador = new Controller();
    }

    @Test
    public void getAnimeList() throws Exception {
        assertNotNull(controlador.getAnimeList());
    }

    @Test
    public void getAnime() throws Exception {
        assertNotNull(controlador.getAnime(0));
    }

    @Test
    public void setAnime() throws Exception {
        String json = "{\n" +
                "    \"studio\": \"Toei Animation\",\n" +
                "    \"genres\": [\n" +
                "        \"adventure\"\n" +
                "    ],\n" +
                "    \"hype\": 100000,\n" +
                "    \"description\": \"As the world is in the middle of an industrial revolution, a monster appears that cannot be defeated unless its heart, which is protected by a layer of iron, is pierced. By infecting humans with its bite, the monster can create aggressive and undead creatures known as Kabane. On the island Hinomoto, located in the far east, people have built stations to shelter themselves from these creatures. People access the station, as well as transport wares between them, with the help of a locomotive running on steam, called Hayajiro. Ikoma, a boy who lives in the Aragane station and helps to build Hayajiro, creates his own weapon called Tsuranukizutsu in order to defeat the creatures. One day, as he waits for an opportunity to use his weapon, he meets a girl named Mumei, who is excused from the mandatory Kabane inspection. During the night, Ikuma meets Mumei again as he sees Hayajiro going out of control. The staff on the locomotive has turned into the creatures. The station, now under attack by Kabane, is the opportunity Ikoma has been looking for. (Source: MAL News)\",\n" +
                "    \"title\": {\n" +
                "        \"link\": \"http://myanimelist.net/anime/28623/Koutetsujou_no_Kabaneri\",\n" +
                "        \"text\": \"One Piece\"\n" +
                "    },\n" +
                "    \"start_date\": \"2016-04-07T22:00:00.000+00:00\"\n" +
                "}";
        Anime anime = new Anime();

        anime = new Gson().fromJson(json, anime.getClass());
        assertEquals(new ResponseEntity(HttpStatus.OK), controlador.setAnime(0, anime));
        assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND), controlador.setAnime(1000000, anime));
    }

    @Test
    public void addAnime() throws Exception {
        String json = "{\n" +
                "    \"studio\": \"Toei Animation\",\n" +
                "    \"genres\": [\n" +
                "        \"adventure\"\n" +
                "    ],\n" +
                "    \"hype\": 100000,\n" +
                "    \"description\": \"As the world is in the middle of an industrial revolution, a monster appears that cannot be defeated unless its heart, which is protected by a layer of iron, is pierced. By infecting humans with its bite, the monster can create aggressive and undead creatures known as Kabane. On the island Hinomoto, located in the far east, people have built stations to shelter themselves from these creatures. People access the station, as well as transport wares between them, with the help of a locomotive running on steam, called Hayajiro. Ikoma, a boy who lives in the Aragane station and helps to build Hayajiro, creates his own weapon called Tsuranukizutsu in order to defeat the creatures. One day, as he waits for an opportunity to use his weapon, he meets a girl named Mumei, who is excused from the mandatory Kabane inspection. During the night, Ikuma meets Mumei again as he sees Hayajiro going out of control. The staff on the locomotive has turned into the creatures. The station, now under attack by Kabane, is the opportunity Ikoma has been looking for. (Source: MAL News)\",\n" +
                "    \"title\": {\n" +
                "        \"link\": \"http://myanimelist.net/anime/28623/Koutetsujou_no_Kabaneri\",\n" +
                "        \"text\": \"One Piece\"\n" +
                "    },\n" +
                "    \"start_date\": \"2016-04-07T22:00:00.000+00:00\"\n" +
                "}";
        Anime anime = new Anime();

        assertEquals(new ResponseEntity(HttpStatus.OK), controlador.addAnime(anime));
    }
}