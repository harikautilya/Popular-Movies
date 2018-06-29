package com.movies.book.ui.main;

import android.support.v7.widget.GridLayoutManager;

import com.movies.book.Base.Classes.BaseFragment;
import com.movies.book.Base.DataManager;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.storage.BaseDataPackage;
import com.movies.book.ui.main.fragment.popular.PopularFragment;
import com.movies.book.ui.main.fragment.top.TopFragment;

import java.util.Arrays;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new MainViewModel(dataManager, schedulerProvider, baseDataPackage);
    }


    @Provides
    MainNavigator provideMainNavigator(MainActivity mainActivity) {
        return mainActivity;
    }


    @Provides
    GridLayoutManager provideGridLayoutManager(MainActivity activity) {
        return new GridLayoutManager(activity, 2);
    }


    @Provides
    @Singleton
    MovieAdapter provideMovieAdapter(MainActivity activity, PopularFragment popularFragment, TopFragment topFragment) {
        return new MovieAdapter(activity.getSupportFragmentManager(),
                Arrays.asList(
                        ((BaseFragment) popularFragment),
                        ((BaseFragment) topFragment)
                )
                , Arrays.asList("Popular", "Top Rated"));
    }
}
