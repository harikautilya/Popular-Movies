package com.movies.book.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movies.book.Base.Classes.BaseRecycleViewAdapter;
import com.movies.book.R;
import com.movies.book.api.response.MoviesResponse;
import com.movies.book.databinding.ItemMovieBinding;
import com.movies.book.ui.details.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends BaseRecycleViewAdapter<MovieAdapter.MovieHolder> {

    private final OnLoadMoreListener onLoadMoreListener;
    private final Context context;
    private final List<MoviesResponse.MovieEntity> data;
    private boolean isLoading;

    public MovieAdapter(Context context, List<MoviesResponse.MovieEntity> data, RecyclerView recyclerView, final OnLoadMoreListener onLoadMoreListener) {
        this.context = context;
        this.data = data;
        this.onLoadMoreListener = onLoadMoreListener;
        final GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                        if (onLoadMoreListener != null)
                            onLoadMoreListener.onLoadMore();
                    }
                }
            }
        });
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.bind(data.get(position));
    }

    public void addData(List<MoviesResponse.MovieEntity> moreData) {
        int start = data.size();
        data.addAll(moreData);
        notifyItemRangeInserted(start, data.size());
        isLoading = false;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    class MovieHolder extends BaseRecycleViewAdapter<MovieAdapter.MovieHolder>.BaseViewHolder<ItemMovieBinding> {

        public MovieHolder(View itemView) {
            super(itemView);
        }

        @SuppressLint("SetTextI18n")
        public void bind(MoviesResponse.MovieEntity movieEntity) {


            if (movieEntity.getAdult()) {
                getViewBinding().movieAdult.setBackground(context.getDrawable(R.drawable.ic_adult_rated));
            } else {
                getViewBinding().movieAdult.setBackground(context.getDrawable(R.drawable.ic_under_rated));
            }
            getViewBinding().movieName.setText(movieEntity.getTitle());
            getViewBinding().imageReleaseDate.setText(context.getString(R.string.release_date) + movieEntity.getReleaseDate());
            getViewBinding().imageRating.setRating((float) movieEntity.getVoteAverage() / 2);

            Picasso.with(context)
                    .load("http://image.tmdb.org/t/p/w185" + movieEntity.getPosterPath())
                    .into(getViewBinding().movieImage);

            final Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("movie_id", movieEntity.getId());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(intent);
                }
            });


        }
    }
}
