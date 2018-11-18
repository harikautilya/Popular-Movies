package com.movies.book.storage.movieList.cloud;

import com.movies.book.api.response.MoviesResponse;
import com.movies.book.storage.movieList.MovieData;

import java.util.List;

public class CloudMovieDataBase implements MovieData {

    private static CloudMovieDataBase INSTANCE;

    public static CloudMovieDataBase get() {
        if (INSTANCE == null) {
            synchronized (CloudMovieDataBase.class) {
                if (INSTANCE == null)
                    return INSTANCE = new CloudMovieDataBase();
            }
        }
        return INSTANCE;
    }

    @Override
    public List<MoviesResponse.MovieEntity> getMovieList() {
        throw new UnsupportedOperationException("Cloud data for this repo is not available");
    }
}
