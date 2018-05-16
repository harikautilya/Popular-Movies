package com.clapoof.clapoof.Base.Dependices;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

import com.clapoof.clapoof.ui.login.LoginActivity;
import com.clapoof.clapoof.ui.login.LoginModule;


/**
 * Created by kautilya on 01/02/18.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity provideLoginActivity();




}
