package com.movies.book.storage;

import com.movies.book.storage.match.MatchRepo;
import com.movies.book.storage.movieList.MovieRepo;

public interface BaseDataRepo {

    MovieRepo provideMovieRepo();
}
