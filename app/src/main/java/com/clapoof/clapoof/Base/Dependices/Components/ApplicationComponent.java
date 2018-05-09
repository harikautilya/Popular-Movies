package com.clapoof.clapoof.Base.Dependices.Components;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

import com.clapoof.clapoof.App;
import com.clapoof.clapoof.Base.DBHelper;
import com.clapoof.clapoof.Base.DataManager;
import com.clapoof.clapoof.Base.Dependices.ActivityBuilder;
import com.clapoof.clapoof.Base.Dependices.Modules.ApplicationModule;
import com.clapoof.clapoof.Base.SharedPrefs;
import com.clapoof.clapoof.Base.annotations.ApplicationContext;


/**
 * Created by kautilya on 31/01/18.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AndroidSupportInjectionModule.class, ActivityBuilder.class, ApplicationModule.class})
public interface ApplicationComponent {


    void inject(App demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPrefs getPreferenceHelper();

    DBHelper getDbHelper();
}
