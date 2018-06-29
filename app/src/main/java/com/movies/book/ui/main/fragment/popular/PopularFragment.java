package com.movies.book.ui.main.fragment.popular;

import android.os.Bundle;
import android.view.View;

import com.movies.book.Base.Classes.BaseFragment;
import com.movies.book.R;
import com.movies.book.databinding.FragmentPopularBinding;

public class PopularFragment extends BaseFragment<FragmentPopularBinding,PopularViewModel,PopularNavigator> implements PopularNavigator {
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

    }

    @Override
    public int getColor() {
        return 0;
    }
}
