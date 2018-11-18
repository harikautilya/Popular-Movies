package com.movies.book.ui.details;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.movies.book.Base.Classes.BaseViewModel;
import com.movies.book.Base.DataManager;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.api.RequestFactory;
import com.movies.book.api.ServerCallBack;
import com.movies.book.api.request.MovieListService;
import com.movies.book.api.response.MovieDetailResponse;
import com.movies.book.api.response.MovieReviewResponse;
import com.movies.book.api.response.MovieVideoResponse;
import com.movies.book.storage.BaseDataPackage;

import java.util.ArrayList;

public class DetailViewModel extends BaseViewModel<DetailNavigator> {

    public ObservableField<MovieDetailResponse> movieDetailResponse;
    private ReviewAdapter adapter;
    private VideoAdapter videoAdapter;

    public DetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        super(dataManager, schedulerProvider, baseDataPackage);
        movieDetailResponse = new ObservableField<>();

    }

    @Override
    public void init(Bundle savedInstanceState, Context context) {
        getNavigator().setReviews(adapter = new ReviewAdapter(new ArrayList<MovieReviewResponse.ResultsEntity>(), context, false));
        getNavigator().setTrailers(videoAdapter = new VideoAdapter(new ArrayList<MovieVideoResponse.ResultsEntity>(), context, false));

    }


    public void getData(String api, long movieID, Context context) {
        MovieListService movieListService = RequestFactory.createRetroFitService(MovieListService.class);

        movieListService.getMovieDetails(movieID, api)
                .enqueue(new ServerCallBack<MovieDetailResponse>(context, true) {

                    @Override
                    public void onResponse(MovieDetailResponse response) {
                        movieDetailResponse.set(response);
                        getNavigator().updateData(response);
                    }
                });

        movieListService.getMovieReviews(movieID, api)
                .enqueue(new ServerCallBack<MovieReviewResponse>(context, true) {

                    @Override
                    public void onResponse(MovieReviewResponse response) {
                        adapter.addMoreData(response.getResults());
                    }
                });

        movieListService.getMovieVideos(movieID, api)
                .enqueue(new ServerCallBack<MovieVideoResponse>(context, true) {

                    @Override
                    public void onResponse(MovieVideoResponse response) {
                        videoAdapter.addMoreData(response.getResults());
                    }
                });

    }
}
