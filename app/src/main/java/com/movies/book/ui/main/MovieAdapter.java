package com.movies.book.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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

public class MovieAdapter extends BaseRecycleViewAdapter<MoviesResponse.MovieEntity, MovieAdapter.MovieHolder> {


    public MovieAdapter(List<MoviesResponse.MovieEntity> data, Context context, boolean filterable, RecyclerView recyclerView, OnLoadMoreListener onLoadMoreListener) {
        super(data, context, filterable, onLoadMoreListener, recyclerView);
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
        setLoading(false);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MovieHolder extends BaseRecycleViewAdapter<MoviesResponse.MovieEntity, MovieAdapter.MovieHolder>.BaseViewHolder<ItemMovieBinding> {

        public MovieHolder(View itemView) {
            super(itemView);
        }

        @SuppressLint("SetTextI18n")
        public void bind(MoviesResponse.MovieEntity movieEntity) {


            if (movieEntity.getAdult()) {
                getViewDataBinding().movieAdult.setBackground(context.getDrawable(R.drawable.ic_adult_rated));
            } else {
                getViewDataBinding().movieAdult.setBackground(context.getDrawable(R.drawable.ic_under_rated));
            }
            getViewDataBinding().movieName.setText(movieEntity.getTitle());
            getViewDataBinding().imageReleaseDate.setText(context.getString(R.string.release_date) + movieEntity.getReleaseDate());
            getViewDataBinding().imageRating.setRating((float) movieEntity.getVoteAverage() / 2);

            Picasso.with(context)
                    .load("http://image.tmdb.org/t/p/w185" + movieEntity.getPosterPath())
                    .into(getViewDataBinding().movieImage);

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
