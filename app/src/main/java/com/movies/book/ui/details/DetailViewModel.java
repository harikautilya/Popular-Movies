package com.movies.book.ui.details;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.movies.book.Base.Classes.BaseViewModel;
import com.movies.book.Base.DataManager;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.api.RequestFactory;
import com.movies.book.api.request.MovieListService;
import com.movies.book.api.response.MovieDetailResponse;
import com.movies.book.storage.BaseDataPackage;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends BaseViewModel<DetailNavigator> {

    public ObservableField<MovieDetailResponse> movieDetailResponse;
    public Observable<String> dasdas;

    public DetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        super(dataManager, schedulerProvider, baseDataPackage);
        movieDetailResponse = new ObservableField<>();
    }

    @Override
    public void init(Bundle savedInstanceState, Context context) {

    }


    public void getData(String api, long movieID) {
        MovieListService movieListService = RequestFactory.createRetroFitService(MovieListService.class);

        movieListService.getMovieDetails(movieID, api)
                .enqueue(new Callback<MovieDetailResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieDetailResponse> call, @NonNull Response<MovieDetailResponse> response) {
                        movieDetailResponse.set(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieDetailResponse> call, @NonNull Throwable t) {

                    }
                });

    }
}
