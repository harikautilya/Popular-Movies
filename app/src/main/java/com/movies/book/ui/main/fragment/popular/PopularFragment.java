package com.movies.book.ui.main.fragment.popular;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.movies.book.Base.Classes.BaseFragment;
import com.movies.book.R;
import com.movies.book.api.response.MoviesResponse;
import com.movies.book.databinding.FragmentPopularBinding;
import com.movies.book.ui.main.MovieAdapter;

import java.util.List;

import javax.inject.Inject;
@Deprecated
public class PopularFragment extends BaseFragment<FragmentPopularBinding, PopularViewModel, PopularNavigator> implements PopularNavigator {

    @Inject
    GridLayoutManager gridLayoutManager;


    MovieAdapter adapter;

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_popular;
    }

    @Override
    public void init(View view, Bundle savedInstances) {

        getViewDataBinding().popularGridView.setLayoutManager(gridLayoutManager);
        getViewModel().getData(getString(R.string.movies_apis),getContext());
    }

    @Override
    public int getColor() {
        return R.color.colorAccent;
    }

    @Override
    public void populateList(List<MoviesResponse.MovieEntity> results) {
        if (adapter == null) {

            getViewDataBinding().popularGridView.setAdapter(adapter = new MovieAdapter(results, getContext(), false, getViewDataBinding().popularGridView, new MovieAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    getViewModel().getData(getString(R.string.movies_apis), getContext());
                }
            }, null));

        } else {
            adapter.addData(results);
        }
    }
}
