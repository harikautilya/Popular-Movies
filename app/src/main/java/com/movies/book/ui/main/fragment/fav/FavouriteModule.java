package com.movies.book.ui.main.fragment.fav;

import com.movies.book.Base.DataManager;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

@Module
public class FavouriteModule {


    @Provides
    FavouriteViewModel provideTopViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new FavouriteViewModel(dataManager, schedulerProvider, baseDataPackage);
    }


    @Provides
    FavouriteNavigator provideTopNaigator(FavouriteFragment fragment) {
        return fragment;
    }

}
