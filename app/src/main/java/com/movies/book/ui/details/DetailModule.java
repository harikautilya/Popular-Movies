package com.movies.book.ui.details;

import com.movies.book.Base.DataManager;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailModule {

    @Provides
    DetailViewModel getDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new DetailViewModel(dataManager, schedulerProvider, baseDataPackage);
    }

    @Provides
    DetailNavigator provideDetalNavigator(DetailActivity detailActivity) {
        return detailActivity;
    }
}
