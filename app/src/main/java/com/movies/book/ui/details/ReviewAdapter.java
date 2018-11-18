package com.movies.book.ui.details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movies.book.Base.Classes.BaseRecycleViewAdapter;
import com.movies.book.R;
import com.movies.book.api.response.MovieReviewResponse;
import com.movies.book.databinding.ItemReviewBinding;

import java.util.List;


public class ReviewAdapter extends BaseRecycleViewAdapter<MovieReviewResponse.ResultsEntity, ReviewAdapter.ReviewHolder> {


    public ReviewAdapter(List<MovieReviewResponse.ResultsEntity> data, Context context, boolean filterable) {
        super(data, context, filterable, null, null);
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewHolder(LayoutInflater.from(context).inflate(R.layout.item_review, parent, false));
    }

    class ReviewHolder extends BaseRecycleViewAdapter<MovieReviewResponse.ResultsEntity, ReviewHolder>.BaseViewHolder<ItemReviewBinding> {

        public ReviewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(MovieReviewResponse.ResultsEntity object) {
            super.bind(object);
            getViewDataBinding().authorName.setText(object.getAuthor());
            getViewDataBinding().review.setText(object.getContent());
        }
    }
}
