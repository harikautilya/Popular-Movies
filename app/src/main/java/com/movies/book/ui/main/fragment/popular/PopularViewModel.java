package com.movies.book.ui.main.fragment.popular;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.movies.book.Base.Classes.BaseViewModel;
import com.movies.book.Base.DataManager;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.api.RequestFactory;
import com.movies.book.api.request.MovieListService;
import com.movies.book.api.response.MoviesResponse;
import com.movies.book.storage.BaseDataPackage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularViewModel extends BaseViewModel<PopularNavigator> {
    long page;

    public PopularViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        super(dataManager, schedulerProvider, baseDataPackage);
        page = 1;
    }

    @Override
    public void init(Bundle savedInstanceState, Context context) {

    }


    public void getData(String apiKey) {
        MovieListService movieListService = RequestFactory.createRetroFitService(MovieListService.class);


        movieListService.getPopularMovies(apiKey, page)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        getNavigator().populateList(response.body().getResults());
                        page++;
                    }

                    @Override
                    public void onFailure(@NonNull Call<MoviesResponse> call,@NonNull  Throwable t) {

                    }
                });
    }
}
