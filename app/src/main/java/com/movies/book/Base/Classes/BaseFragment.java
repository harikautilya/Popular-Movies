package com.movies.book.Base.Classes;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.movies.book.utils.Utils;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by kautilya on 01/02/18.
 */

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel, K extends BaseNavigator> extends Fragment implements BaseNavigator {

    protected final String LOG_TAG = this.getClass().getSimpleName();
    private BaseActivity mActivity;
    private ProgressDialog mProgressDialog;
    private T mViewDataBinding;

    private View mRootView;


    @Inject
    V mViewModel;

    @Inject
    K mNavigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
        setHasOptionsMenu(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.setNavigator(mNavigator);
        mViewDataBinding.setVariable(getBindingVariable(), mNavigator);
        mViewDataBinding.executePendingBindings();
        init(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }

    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public void openActivityOnTokenExpire() {
        if (mActivity != null) {
            mActivity.openActivityOnTokenExpire();
        }
    }

    public View getRootView() {
        return mRootView;
    }

    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }


    public K getNavigator() {
        return mNavigator;
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);

    }

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public V getViewModel() {
        return mViewModel;
    }

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract @LayoutRes
    int getLayoutId();

    public abstract void init(View view, Bundle savedInstances);


    public abstract int getColor();


    @Override
    public void showAlertDialog(String title, String message, String positiveText, DialogInterface.OnClickListener pClickListener, String negative, DialogInterface.OnClickListener nClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(title);
        builder.setMessage(message);
        if (pClickListener != null)
            builder.setPositiveButton(positiveText, pClickListener);

        if (nClickListener != null) {
            builder.setNegativeButton(negative, nClickListener);
        }
        builder.create().show();

    }

    @Override
    public void showLoading(String title, String message) {
        hideLoading();
        mProgressDialog = Utils.showLoadingDialog(mActivity, title, message);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    }
}
