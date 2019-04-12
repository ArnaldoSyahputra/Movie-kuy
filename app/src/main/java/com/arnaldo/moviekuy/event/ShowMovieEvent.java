package com.arnaldo.moviekuy.event;

import com.arnaldo.moviekuy.model.Movie;

public class ShowMovieEvent {
    public final Movie movie;

    public ShowMovieEvent(Movie movie) {

        this.movie = movie;
    }
}
