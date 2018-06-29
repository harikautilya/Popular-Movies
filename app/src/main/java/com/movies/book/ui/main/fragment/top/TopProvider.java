package com.movies.book.ui.main.fragment.top;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TopProvider {

    @ContributesAndroidInjector(modules = TopModule.class)
    abstract TopFragment providePopularFragment();
}
