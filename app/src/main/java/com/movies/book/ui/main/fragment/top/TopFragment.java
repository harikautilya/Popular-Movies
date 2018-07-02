package com.movies.book.ui.main.fragment.top;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.movies.book.Base.Classes.BaseFragment;
import com.movies.book.R;
import com.movies.book.api.response.MoviesResponse;
import com.movies.book.databinding.FragmentTopBinding;
import com.movies.book.ui.main.MovieAdapter;

import java.util.List;

import javax.inject.Inject;

public class TopFragment extends BaseFragment<FragmentTopBinding, TopVIewModel, TopNavigator> implements TopNavigator {


    @Inject
    GridLayoutManager gridLayoutManager;
    private MovieAdapter adapter;


    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_top;
    }

    @Override
    public void init(View view, Bundle savedInstances) {

        getViewDataBinding().topGridView.setLayoutManager(gridLayoutManager);
        getViewModel().getData(getString(R.string.movies_apis));

    }

    @Override
    public int getColor() {
        return R.color.colorPrimary;
    }

    @Override
    public void populateList(List<MoviesResponse.MovieEntity> results) {
        if (adapter == null) {

            getViewDataBinding().topGridView.setAdapter(adapter = new MovieAdapter(getContext(), results, getViewDataBinding().topGridView, new MovieAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {

                    getViewModel().getData(getString(R.string.movies_apis));

                }
            }));

        } else {
            adapter.addData(results);
        }
    }
}
