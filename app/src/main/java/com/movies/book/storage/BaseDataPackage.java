package com.movies.book.storage;

import com.movies.book.Base.DBHelper;
import com.movies.book.storage.movieList.MovieRepo;
import com.movies.book.storage.movieList.cloud.CloudMovieDataBase;
import com.movies.book.storage.movieList.local.LocalMovieDataBase;

import javax.inject.Inject;

public class BaseDataPackage implements BaseDataRepo {

    private MovieRepo matchRepo;
    private DBHelper dbHelper;

    @Inject
    public BaseDataPackage(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }


    @Override
    public MovieRepo provideMovieRepo() {
        if (matchRepo == null)
            return matchRepo = new MovieRepo(LocalMovieDataBase.get(dbHelper), CloudMovieDataBase.get());
        else
            return matchRepo;
    }
}
