package com.movies.book.api.request;


import com.movies.book.api.response.MovieDetailResponse;
import com.movies.book.api.response.MovieReviewResponse;
import com.movies.book.api.response.MovieVideoResponse;
import com.movies.book.api.response.MoviesResponse;
import com.movies.book.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieListService {

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRated(@Query(Constants.QUERY_API_KEY) String key, @Query("page") long page);


    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query(Constants.QUERY_API_KEY) String key, @Query("page") long page);

    @GET("movie/{id}")
    Call<MovieDetailResponse> getMovieDetails(@Path("id") long id, @Query(Constants.QUERY_API_KEY) String api);

    @GET("movie/{id}/reviews")
    Call<MovieReviewResponse> getMovieReviews(@Path("id") long id, @Query(Constants.QUERY_API_KEY) String api);

    @GET("movie/{id}/videos")
    Call<MovieVideoResponse> getMovieVideos(@Path("id") long id, @Query(Constants.QUERY_API_KEY) String api);

}
