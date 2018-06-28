package com.movies.book.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.movies.book.BR;
import com.movies.book.Base.Classes.BaseActivity;
import com.movies.book.R;
import com.movies.book.databinding.ActivityLoginBinding;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel, LoginNavigator> implements LoginNavigator {





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

    @Override
    public void changeText(int i) {
        getViewDataBinding().countText.setText(i+"");

    }
}
