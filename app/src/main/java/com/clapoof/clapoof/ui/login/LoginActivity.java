package com.clapoof.clapoof.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.clapoof.clapoof.BR;
import com.clapoof.clapoof.Base.Classes.BaseActivity;
import com.clapoof.clapoof.R;
import com.clapoof.clapoof.databinding.ActivityLoginBinding;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel, LoginNavigator> implements LoginNavigator {

    @Inject
    LoginViewModel loginViewModel;

    @Override
    public LoginViewModel getViewModel() {
        return loginViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public int getViewModelId() {
        return BR.login_view;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

    }
}
