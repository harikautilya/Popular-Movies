package com.movies.book.ui.main.fragment.top;

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

public class TopVIewModel extends BaseViewModel<TopNavigator> {
    long page;

    public TopVIewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        super(dataManager, schedulerProvider, baseDataPackage);
        page = 1;
    }

    @Override
    public void init(Bundle savedInstanceState, Context context) {

    }


    public void getData(String apiKey, Context context) {
        MovieListService movieListService = RequestFactory.createRetroFitService(MovieListService.class);


        movieListService.getTopRated(apiKey, page)
                .enqueue(new ServerCallBack<MoviesResponse>(context, true) {
                    @Override
                    public void onResponse(MoviesResponse response) {
                        getNavigator().populateList(response.getResults());
                        page++;
                    }
                });
    }

}
