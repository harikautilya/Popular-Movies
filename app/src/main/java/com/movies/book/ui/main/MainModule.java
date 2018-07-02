package com.movies.book.ui.main;

import android.support.v7.widget.GridLayoutManager;

import com.movies.book.Base.DataManager;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.storage.BaseDataPackage;

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


}
