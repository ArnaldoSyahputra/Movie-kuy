package com.arnaldo.moviekuy.callback;

import com.arnaldo.moviekuy.model.Movie;

import java.util.List;

public interface MoviesCallback {

    void success(List<Movie> movies);

    void error(Exception error);

}
