package com.movies.book.ui.details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movies.book.Base.Classes.BaseRecycleViewAdapter;
import com.movies.book.R;
import com.movies.book.api.response.MovieVideoResponse;
import com.movies.book.databinding.ItemVideoBinding;

import java.util.List;

public class VideoAdapter extends BaseRecycleViewAdapter<MovieVideoResponse.ResultsEntity, VideoAdapter.VideoHoder> {


    public VideoAdapter(List<MovieVideoResponse.ResultsEntity> data, Context context, boolean filterable) {
        super(data, context, filterable, null, null);
    }

    @NonNull
    @Override
    public VideoHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoHoder(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false));
    }


    class VideoHoder extends BaseRecycleViewAdapter<MovieVideoResponse, VideoHoder>.BaseViewHolder<ItemVideoBinding> {

        public VideoHoder(View itemView) {
            super(itemView);
        }
    }
}
