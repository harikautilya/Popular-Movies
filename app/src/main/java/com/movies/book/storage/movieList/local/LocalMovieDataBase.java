package com.movies.book.storage.movieList.local;

import com.movies.book.Base.DBHelper;
import com.movies.book.storage.match.local.LocalMatchBaseData;
import com.movies.book.storage.movieList.MovieData;

public class LocalMovieDataBase implements MovieData {

    private static LocalMovieDataBase INSTANCE;

    private final DBHelper dbHelper;

    public static LocalMovieDataBase get(DBHelper dbHelper) {
        if (INSTANCE == null) {
            synchronized (LocalMatchBaseData.class) {
                if (INSTANCE == null)
                    return INSTANCE = new LocalMovieDataBase(dbHelper);
            }
        }
        return INSTANCE;
    }

    public LocalMovieDataBase(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
}
