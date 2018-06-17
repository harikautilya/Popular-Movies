package com.clapoof.clapoof.Base.Dependices;

import com.clapoof.clapoof.Base.Classes.BaseActivity;
import com.clapoof.clapoof.Base.Classes.BaseNavigator;
import com.clapoof.clapoof.Base.DataManager;
import com.clapoof.clapoof.Base.rx.SchedulerProvider;
import com.clapoof.clapoof.storage.BaseDataPackage;

public interface BaseModule<T, A extends BaseActivity,L extends BaseNavigator> {


    T provideViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage);

    L provideActivity(A activity);

}
