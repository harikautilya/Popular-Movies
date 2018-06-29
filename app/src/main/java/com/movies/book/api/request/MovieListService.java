package com.movies.book.api.request;


import com.movies.book.api.response.MoviesResponse;
import com.movies.book.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieListService {

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRated(@Query(Constants.QUERY_API_KEY) String key, @Query("page")long page);


    @GET("movie/popular")
    Call<MoviesResponse> getPopulatMovies(@Query(Constants.QUERY_API_KEY) String key, @Query("page")long page);

}
