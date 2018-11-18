package com.movies.book.storage.movieList.cloud;

import com.movies.book.storage.match.cloud.CloudMatchBaseData;
import com.movies.book.storage.movieList.MovieData;

public class CloudMovieDataBase implements MovieData {

    private static CloudMovieDataBase INSTANCE;

    public static CloudMovieDataBase get() {
        if (INSTANCE == null) {
            synchronized (CloudMatchBaseData.class) {
                if (INSTANCE == null)
                    return INSTANCE = new CloudMovieDataBase();
            }
        }
        return INSTANCE;
    }
}
