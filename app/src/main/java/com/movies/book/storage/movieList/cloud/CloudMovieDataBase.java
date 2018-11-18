package com.movies.book.storage.movieList.cloud;

import com.movies.book.storage.movieList.MovieData;

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
}
