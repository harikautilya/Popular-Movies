package com.movies.book.storage.movieList;

public class MovieRepo {

    MovieData cloudData;
    MovieData remoteData;

    public MovieRepo(MovieData remoteData, MovieData cloudData) {
        this.cloudData = cloudData;
        this.remoteData = remoteData;
    }


    public MovieData getCloudData() {
        return cloudData;
    }

    public void setCloudData(MovieData cloudData) {
        this.cloudData = cloudData;
    }

    public MovieData getRemoteData() {
        return remoteData;
    }

    public void setRemoteData(MovieData remoteData) {
        this.remoteData = remoteData;
    }
}
