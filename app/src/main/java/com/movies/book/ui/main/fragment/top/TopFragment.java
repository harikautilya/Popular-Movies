package com.movies.book.ui.main.fragment.top;

import android.os.Bundle;
import android.view.View;

import com.movies.book.Base.Classes.BaseFragment;
import com.movies.book.R;
import com.movies.book.databinding.FragmentTopBinding;

public class TopFragment extends BaseFragment<FragmentTopBinding, TopVIewModel, TopNavigator> implements TopNavigator {
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

    }

    @Override
    public int getColor() {
        return 0;
    }
}
