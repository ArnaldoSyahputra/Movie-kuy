package com.arnaldo.moviekuy.provider;

import android.net.Uri;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.ContentUri;

public class MovieContract implements ProviGenBaseContract {

    @ContentUri
    public static final Uri CONTENT_URI = Uri.parse("content://com.arnaldo.moviekuy/moviekuy");

    @Column(Column.Type.INTEGER)
    public static final String MOVIE_ID = "movieId";

    @Column(Column.Type.TEXT)
    public static final String TYPE = "type";

    @Column(Column.Type.TEXT)
    public static final String TITLE = "title";

    @Column(Column.Type.TEXT)
    public static final String OVERVIEW = "overview";

    @Column(Column.Type.TEXT)
    public static final String POSTER_URL = "posterUrl";

    @Column(Column.Type.TEXT)
    public static final String BACKDROP_URL = "backdropUrl";

    @Column(Column.Type.TEXT)
    public static final String TRAILER_URL = "trailerUrl";

    @Column(Column.Type.TEXT)
    public static final String RELEASE_DATE = "releaseDate";

    @Column(Column.Type.REAL)
    public static final String RATING = "rating";

    @Column(Column.Type.INTEGER)
    public static final String ADULT = "adult";

}
