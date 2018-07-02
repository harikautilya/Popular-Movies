package com.movies.book.ui.details;

import android.content.Context;
import android.os.Bundle;

import com.movies.book.Base.Classes.BaseViewModel;
import com.movies.book.Base.DataManager;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.api.RequestFactory;
import com.movies.book.api.request.MovieListService;
import com.movies.book.api.response.MovieDetailResponse;
import com.movies.book.storage.BaseDataPackage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends BaseViewModel<DetailNavigator> {

    public DetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        super(dataManager, schedulerProvider, baseDataPackage);
    }

    @Override
    public void init(Bundle savedInstanceState, Context context) {

    }


    public void getData(String api, long movieID) {
        MovieListService movieListService = RequestFactory.createRetroFitService(MovieListService.class);

        movieListService.getMovieDetails(movieID, api)
                .enqueue(new Callback<MovieDetailResponse>() {
                    @Override
                    public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                        getNavigator().updateData(response.body());
                    }

                    @Override
                    public void onFailure(Call<MovieDetailResponse> call, Throwable t) {

                    }
                });

    }
}
