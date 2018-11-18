package com.movies.book.ui.main.fragment.fav;

import android.os.Bundle;
import android.view.View;

import com.movies.book.Base.Classes.BaseFragment;
import com.movies.book.R;
import com.movies.book.databinding.FragmentFavBinding;

@Deprecated
public class FavouriteFragment extends BaseFragment<FragmentFavBinding, FavouriteViewModel, FavouriteNavigator> implements FavouriteNavigator {
    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fav;
    }

    @Override
    public void init(View view, Bundle savedInstances) {

    }

    @Override
    public int getColor() {
        return 0;
    }
}
