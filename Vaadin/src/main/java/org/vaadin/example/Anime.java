package org.vaadin.example;

import java.util.Date;
import java.util.List;

public class Anime {
    String studio;
    List<String> genres;
    int hype;
    String description;
    Titulo title;
    Date start_date;

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getHype() {
        return hype;
    }

    public void setHype(int hype) {
        this.hype = hype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Titulo getTitle() {
        return title;
    }

    public void setTitle(Titulo title) {
        this.title = title;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }
}
