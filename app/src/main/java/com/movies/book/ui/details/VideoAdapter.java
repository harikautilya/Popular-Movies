package com.movies.book.ui.details;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
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


    class VideoHoder extends BaseRecycleViewAdapter<MovieVideoResponse.ResultsEntity, VideoHoder>.BaseViewHolder<ItemVideoBinding> {

        @Override
        protected void bind(final MovieVideoResponse.ResultsEntity object) {
            super.bind(object);

            getViewDataBinding().youtubeThumbnail.initialize("AIzaSyAY3gp37oPQ6zXOlUDPGGoeC8MdujC9EwI", new YouTubeThumbnailView.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                    youTubeThumbnailLoader.setVideo((object.getKey()));
                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startYouTubeApp(context, object.getKey());
                }
            });
        }

        public VideoHoder(View itemView) {
            super(itemView);


        }

        public void startYouTubeApp(Context context, String id) {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
            try {
                context.startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                context.startActivity(webIntent);
            }
        }
    }
}
