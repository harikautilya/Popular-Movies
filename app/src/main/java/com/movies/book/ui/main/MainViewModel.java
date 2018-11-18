package com.movies.book.ui.main;

import android.content.Context;
import android.os.Bundle;

import com.movies.book.Base.Classes.BaseViewModel;
import com.movies.book.Base.DataManager;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.api.RequestFactory;
import com.movies.book.api.ServerCallBack;
import com.movies.book.api.request.MovieListService;
import com.movies.book.api.response.MoviesResponse;
import com.movies.book.storage.BaseDataPackage;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private int pageTop, pagePop;

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        super(dataManager, schedulerProvider, baseDataPackage);
    }

    @Override
    public void init(Bundle savedInstanceState, Context context) {
        pageTop = 1;
        pagePop = 1;
    }


    public void getTopMovies(String apiKey, Context context) {
        MovieListService movieListService = RequestFactory.createRetroFitService(MovieListService.class);

        pagePop = 1;
        movieListService.getTopRated(apiKey, pageTop)
                .enqueue(new ServerCallBack<MoviesResponse>(context, true) {
                    @Override
                    public void onResponse(MoviesResponse response) {
                        getNavigator().populateList(response.getResults());
                        pageTop++;
                    }
                });
    }

    public void getPopularMovies(String apiKey, Context context) {
        MovieListService movieListService = RequestFactory.createRetroFitService(MovieListService.class);
        pageTop = 1;

        movieListService.getPopularMovies(apiKey, pagePop)
                .enqueue(new ServerCallBack<MoviesResponse>(context, true) {
                    @Override
                    public void onResponse(MoviesResponse response) {
                        getNavigator().populateList(response.getResults());
                        pagePop++;
                    }
                });
    }

    public void getFavMovies() {
        pageTop = 1;
        pagePop = 1;
        getNavigator().populateList(getBaseDataPackage().provideMovieRepo().getRemoteData().getMovieList());


    }
}
