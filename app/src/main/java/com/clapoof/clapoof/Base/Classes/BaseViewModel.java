package com.clapoof.clapoof.Base.Classes;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.os.Bundle;

import com.clapoof.clapoof.Base.DataManager;
import com.clapoof.clapoof.Base.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by kautilya on 01/02/18.
 */

public abstract class BaseViewModel<N extends BaseNavigator> extends ViewModel {

    private N mNavigator;
    protected final String LOG_TAG =this.getClass().getName();
    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private CompositeDisposable mCompositeDisposable;


    public BaseViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();

    }

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }

    public N getNavigator() {
        return mNavigator;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }


    public abstract void init(Bundle savedInstanceState, Context context);

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

}
