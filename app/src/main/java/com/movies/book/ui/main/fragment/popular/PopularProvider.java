package com.movies.book.ui.main.fragment.popular;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PopularProvider {

    @ContributesAndroidInjector(modules = PopularModule.class)
    abstract PopularFragment providePopularFragment();
}
