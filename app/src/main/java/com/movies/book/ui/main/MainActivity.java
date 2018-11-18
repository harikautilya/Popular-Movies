package com.movies.book.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.movies.book.BR;
import com.movies.book.Base.Classes.BaseActivity;
import com.movies.book.R;
import com.movies.book.api.response.MoviesResponse;
import com.movies.book.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel, MainNavigator> implements MainNavigator, HasSupportFragmentInjector {


    @Inject
    GridLayoutManager gridLayoutManager;
    private MovieAdapter adaper;


    enum SELECT_DATA_TYPE {
        POPULAR, FAV, TOP
    }

    SELECT_DATA_TYPE selectDataType;

    @Inject
    DispatchingAndroidInjector<Fragment> androidInjector;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getViewModelId() {
        return BR.main_view;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        getViewDataBinding().moviesList.setLayoutManager(gridLayoutManager);
        getViewDataBinding().moviesList.setAdapter(adaper = new MovieAdapter(new ArrayList<MoviesResponse.MovieEntity>(), this, false, getViewDataBinding().moviesList, new MovieAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                switch (selectDataType) {
                    case FAV:
                        getViewModel().getFavMovies();
                        break;
                    case TOP:
                        getViewModel().getTopMovies(getString(R.string.movies_apis), MainActivity.this);
                        break;
                    case POPULAR:
                        getViewModel().getPopularMovies(getString(R.string.movies_apis), MainActivity.this);
                        break;
                    default:
                        break;


                }
            }
        }));

        getViewDataBinding().fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectDataType != SELECT_DATA_TYPE.FAV) {
                    adaper.clear();
                }
                selectDataType = SELECT_DATA_TYPE.FAV;
                getViewModel().getFavMovies();
            }
        });

        getViewDataBinding().top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectDataType != SELECT_DATA_TYPE.TOP) {
                    adaper.clear();
                }
                selectDataType = SELECT_DATA_TYPE.TOP;
                getViewModel().getTopMovies(getString(R.string.movies_apis), MainActivity.this);
            }
        });
        getViewDataBinding().pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectDataType != SELECT_DATA_TYPE.POPULAR) {
                    adaper.clear();
                }
                selectDataType = SELECT_DATA_TYPE.POPULAR;
                getViewModel().getPopularMovies(getString(R.string.movies_apis), MainActivity.this);
            }
        });

    }

    @Override
    public void populateList(List<MoviesResponse.MovieEntity> results) {
        adaper.addData(results);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return androidInjector;
    }
}
