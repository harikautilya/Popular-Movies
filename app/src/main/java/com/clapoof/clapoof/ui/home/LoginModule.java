package com.clapoof.clapoof.ui.home;

import com.clapoof.clapoof.Base.DataManager;
import com.clapoof.clapoof.Base.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kautilya on 10-05-2018.
 */
@Module
public class LoginModule {

    @Provides
    LoginViewModel provideLoginViewModel(DataManager dataManager,
                                         SchedulerProvider schedulerProvider) {
        return new LoginViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LoginNavigator provideLoginNavigator(LoginActivity activity) {
        return activity;
    }
}

