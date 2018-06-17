package com.clapoof.clapoof.ui.login;

import com.clapoof.clapoof.Base.DataManager;
import com.clapoof.clapoof.Base.Dependices.BaseModule;
import com.clapoof.clapoof.Base.rx.SchedulerProvider;
import com.clapoof.clapoof.storage.BaseDataPackage;

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

