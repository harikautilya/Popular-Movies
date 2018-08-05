package com.movies.book.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.movies.book.BR;
import com.movies.book.Base.Classes.BaseActivity;
import com.movies.book.Base.Classes.BaseFragment;
import com.movies.book.R;
import com.movies.book.databinding.ActivityMainBinding;
import com.movies.book.ui.main.fragment.popular.PopularFragment;
import com.movies.book.ui.main.fragment.top.TopFragment;

import java.util.Arrays;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel, MainNavigator> implements MainNavigator, HasSupportFragmentInjector {


    @Inject
    GenreAdapter genreAdapter;

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

        getViewDataBinding().mainViewpager.setAdapter(genreAdapter );
        getViewDataBinding().mainTabLayout.setUpViewPager(getViewDataBinding().mainViewpager);

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return androidInjector;
    }
}
