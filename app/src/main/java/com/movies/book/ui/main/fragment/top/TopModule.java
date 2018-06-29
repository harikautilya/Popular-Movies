package com.movies.book.ui.main.fragment.top;

import com.movies.book.Base.DataManager;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

@Module
public class TopModule {

    @Provides
    TopVIewModel provideTopViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new TopVIewModel(dataManager, schedulerProvider, baseDataPackage);
    }


    @Provides
    TopNavigator provideTopNaigator(TopFragment fragment) {
        return fragment;
    }
}
