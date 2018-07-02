package com.movies.book.ui.details;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.movies.book.BR;
import com.movies.book.Base.Classes.BaseActivity;
import com.movies.book.R;
import com.movies.book.api.response.MovieDetailResponse;
import com.movies.book.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

public class DetailActivity extends BaseActivity<ActivityDetailBinding, DetailViewModel, DetailNavigator> implements DetailNavigator {
    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public int getViewModelId() {
        return BR.detail_view;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

        getViewModel().getData(getString(R.string.movies_apis), getIntent().getIntExtra("movie_id", -1));

    }

    @Override
    public void updateData(MovieDetailResponse body) {
        getViewDataBinding().movieDetail.setText(body.getOverview());
        getViewDataBinding().movieName.setText(body.getTitle());

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/original" + body.getBackdrop_path())
                .into(getViewDataBinding().backdrop);


    }
}
