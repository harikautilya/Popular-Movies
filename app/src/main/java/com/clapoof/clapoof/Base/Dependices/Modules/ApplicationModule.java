package com.clapoof.clapoof.Base.Dependices.Modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

import com.clapoof.clapoof.App;
import com.clapoof.clapoof.Base.annotations.ApplicationContext;
import com.clapoof.clapoof.Base.annotations.DatabaseInfo;
import com.clapoof.clapoof.Base.rx.AppSchedulerProvider;
import com.clapoof.clapoof.Base.rx.SchedulerProvider;

/**
 * Created by kautilya on 31/01/18.
 */
@Module
public class ApplicationModule {
    private App mApplication;
    private static final String spInstant = "AppPref";
    private static final String DATABASE_NAME = "AppData";

    public ApplicationModule(App application) {
        this.mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return DATABASE_NAME;
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 2;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences(spInstant, Context.MODE_PRIVATE);
    }

}
