package com.clapoof.clapoof.ui.login;

import android.content.Context;
import android.os.Bundle;

import com.clapoof.clapoof.Base.Classes.BaseViewModel;
import com.clapoof.clapoof.Base.DataManager;
import com.clapoof.clapoof.Base.rx.SchedulerProvider;
import com.clapoof.clapoof.storage.BaseDataPackage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Kautilya on 10-05-2018.
 */
public class LoginViewModel extends BaseViewModel<LoginNavigator> {
    int count;


    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        super(dataManager, schedulerProvider, baseDataPackage);
    }

    @Override
    public void init(Bundle savedInstanceState, Context context) {
        count = 0;

        getSchedulerProvider().io().createWorker().schedule(new Runnable() {
            @Override
            public void run() {

                // Request data from database

            }
        });

        getSchedulerProvider().ui().createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                // make a ui change if necessary
            }
        });

        getSchedulerProvider().computation().createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                // make a really long computation
            }
        });


        getSchedulerProvider().io().createWorker().schedulePeriodically(new Runnable() {
            @Override
            public void run() {
                // can be used to sync data periodically without loading put the service intent
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    public void increaseCount() {
        getNavigator().changeText(count++);
    }
}
