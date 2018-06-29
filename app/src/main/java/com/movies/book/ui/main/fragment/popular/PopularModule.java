package com.movies.book.ui.main.fragment.popular;

import com.movies.book.Base.DataManager;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

@Module
public class PopularModule {

    @Provides
    PopularViewModel provideTopViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new PopularViewModel(dataManager, schedulerProvider, baseDataPackage);
    }


    @Provides
    PopularNavigator provideTopNaigator(PopularFragment fragment) {
        return fragment;
    }
}
