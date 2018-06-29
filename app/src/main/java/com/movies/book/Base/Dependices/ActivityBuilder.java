package com.movies.book.Base.Dependices;

import com.movies.book.ui.main.MainActivity;
import com.movies.book.ui.main.MainModule;
import com.movies.book.ui.main.fragment.popular.PopularProvider;
import com.movies.book.ui.main.fragment.top.TopProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * Created by kautilya on 01/02/18.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainModule.class, TopProvider.class, PopularProvider.class})
    abstract MainActivity provideMainActivity();


}
