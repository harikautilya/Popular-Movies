package com.movies.book.ui.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;

import com.movies.book.BR;
import com.movies.book.Base.Classes.BaseActivity;
import com.movies.book.R;
import com.movies.book.api.response.MovieDetailResponse;
import com.movies.book.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity<ActivityDetailBinding, DetailViewModel, DetailNavigator> implements DetailNavigator {

    BottomSheetBehavior sheetBehavior;

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

        getViewModel().getData(getString(R.string.movies_apis), getIntent().getIntExtra("movie_id", -1), this);


        sheetBehavior = BottomSheetBehavior.from(getViewDataBinding().dataSheet.getRoot());


        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }

    @Override
    public void updateData(MovieDetailResponse body) {


    }

    @Override
    public void setTrailers(VideoAdapter videoAdapter) {
        getViewDataBinding().dataSheet.trailers.setAdapter(videoAdapter);
    }

    @Override
    public void setReviews(ReviewAdapter reviewAdapter) {
        getViewDataBinding().dataSheet.reviews.setAdapter(reviewAdapter);
    }
}
