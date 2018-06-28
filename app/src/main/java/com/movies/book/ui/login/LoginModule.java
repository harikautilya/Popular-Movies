package com.movies.book.ui.login;

import com.movies.book.Base.DataManager;
import com.movies.book.Base.Dependices.BaseModule;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kautilya on 10-05-2018.
 */
@Module
public class LoginModule implements BaseModule<LoginViewModel, LoginActivity, LoginNavigator> {


    @Override
    @Provides
    public LoginViewModel provideViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new LoginViewModel(dataManager, schedulerProvider, baseDataPackage);
    }

    @Override
    @Provides
    public LoginNavigator provideActivity(LoginActivity activity) {
        return activity;
    }
}

