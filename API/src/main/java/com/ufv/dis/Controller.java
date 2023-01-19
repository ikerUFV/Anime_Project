package com.ufv.dis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    List<Anime> listaAnime = new ArrayList<>();

    public void setListaAnime() throws Exception {
        listaAnime = new LectorJSON().leerJson();   // Inicialización de la lista
    }

    @GetMapping("/animes")
    public List<Anime> getAnimeList() throws Exception {
        // Vemos si la lista está vacía
        if(listaAnime.size() == 0)
            setListaAnime();    // Si lo está, la inicializamos

        // Devolvemos la lista
        return listaAnime;
    }

    @GetMapping("/animes/{id}")
    public Anime getAnime(@PathVariable int id) throws Exception {
        // Vemos si la lista está vacía
        if(listaAnime.size() == 0)
            setListaAnime();    // Si lo está, la inicializamos

        // Devolvemos el id
        return listaAnime.get(id);
    }

    @PutMapping("/animes/{id}")
    public ResponseEntity setAnime(@PathVariable int id, @RequestBody Anime request) throws Exception {
        // Vemos si la lista está vacía
        if(listaAnime.size() == 0)
            setListaAnime();    // Si lo está, la inicializamos

        // Comprobamos si el id introducido es menor al tamaño de la lista
        if (listaAnime.size() - 1 < id)
            return new ResponseEntity(HttpStatus.NOT_FOUND);    // Si es mayor, devolvemos error: NOT_FOUND

        listaAnime.set(id, request);    // Cambiamos el objeto dentro de la lista

        return new ResponseEntity(HttpStatus.OK);   // DEvolvemos Código OK
    }

    @PostMapping("/animes")
    public ResponseEntity addAnime(@RequestBody Anime request) throws Exception {
        // Vemos si la lista está vacía
        if(listaAnime.size() == 0)
            setListaAnime();    // Si lo está, la inicializamos
        listaAnime.add(request);    // Añadimos el objeto
        return new ResponseEntity(HttpStatus.OK);   // Retornamos OK
    }
}
