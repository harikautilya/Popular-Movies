package com.movies.book.storage.movieList;

public class MovieRepo {

    MovieData cloudData;
    MovieData remoteData;

    public MovieRepo(MovieData remoteData, MovieData cloudData) {
        this.cloudData = cloudData;
        this.remoteData = remoteData;
    }


    void createTable() {
        remoteData.getMovieList();
    }


}
