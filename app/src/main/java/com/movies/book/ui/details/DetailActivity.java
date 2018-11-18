package com.movies.book.ui.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.movies.book.BR;
import com.movies.book.Base.Classes.BaseActivity;
import com.movies.book.R;
import com.movies.book.api.response.MovieDetailResponse;
import com.movies.book.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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

        getViewDataBinding().dataSheet.trailers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        getViewDataBinding().dataSheet.reviews.setLayoutManager(new LinearLayoutManager(this));

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
        Picasso.with(this)
                .load(getString(R.string.huge_image) + body.getBackdrop_path())
                .into(getViewDataBinding().movieImage);
        getViewDataBinding().dataSheet.tags.removeAllViews();
        for (View view : generateViewForTags(body.getGenres())) {
            getViewDataBinding().dataSheet.tags.addView(view);
        }


    }

    @Override
    public void setTrailers(VideoAdapter videoAdapter) {
        getViewDataBinding().dataSheet.trailers.setAdapter(videoAdapter);
    }

    @Override
    public void setReviews(ReviewAdapter reviewAdapter) {
        getViewDataBinding().dataSheet.reviews.setAdapter(reviewAdapter);
    }


    List<View> generateViewForTags(List<MovieDetailResponse.GenresEntity> genres) {
        List<View> views = new ArrayList<>();
        for (MovieDetailResponse.GenresEntity genresEntity : genres) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_genre, null);
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ((TextView) view.findViewById(R.id.genre)).setText(genresEntity.getName());
            views.add(view);
        }

        return views;

    }
}
