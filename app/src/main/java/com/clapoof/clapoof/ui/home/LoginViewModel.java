package com.clapoof.clapoof.ui.home;

import android.content.Context;
import android.os.Bundle;

import com.clapoof.clapoof.Base.Classes.BaseViewModel;
import com.clapoof.clapoof.Base.DataManager;
import com.clapoof.clapoof.Base.rx.SchedulerProvider;

/**
 * Created by Kautilya on 10-05-2018.
 */
public class LoginViewModel extends BaseViewModel<LoginNavigator> {


    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void init(Bundle savedInstanceState, Context context) {

    }
}
