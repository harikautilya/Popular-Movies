package com.movies.book.storage.movieList;

import com.movies.book.api.response.MoviesResponse;

import java.util.List;

public interface MovieData {

    List<MoviesResponse.MovieEntity> getMovieList();

    long saveMovie(MoviesResponse.MovieEntity movieEntity);

    int deleteMovie(long id);


    void toggle(MoviesResponse.MovieEntity movieEntity);

    boolean checkMovie(int id);
}
