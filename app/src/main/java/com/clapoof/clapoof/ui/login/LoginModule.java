package com.clapoof.clapoof.ui.login;

import com.clapoof.clapoof.Base.DataManager;
import com.clapoof.clapoof.Base.rx.SchedulerProvider;
import com.clapoof.clapoof.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kautilya on 10-05-2018.
 */
@Module
public class LoginModule {

    @Provides
    LoginViewModel provideLoginViewModel(DataManager dataManager,
                                         SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new LoginViewModel(dataManager, schedulerProvider,baseDataPackage);
    }

    @Provides
    LoginNavigator provideLoginNavigator(LoginActivity activity) {
        return activity;
    }
}

