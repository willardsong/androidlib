package com.infrastructure.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author weijingsong
 * @date 2020/2/14
 */
public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutViewId(), container, false);
        mContext = mRootView.getContext();
        initData();
        initView();
        initEvent();
        return mRootView;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    public void showFragment(FragmentActivity activity, BaseFragment fragment) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.show(fragment);
        transaction.commitAllowingStateLoss();
    }

    public void hideFragment(FragmentActivity activity, BaseFragment fragment) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.hide(fragment);
        transaction.commitAllowingStateLoss();
    }

    public void showFragment(FragmentActivity activity, BaseFragment fragment, int containerViewId, String fragmentTag) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        setCustomAnimations(transaction);
        Fragment fragment1 = activity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (null == fragment1) {
            transaction.add(containerViewId, fragment, fragmentTag);
        } else {
            transaction.show(fragment);
        }
        transaction.commitAllowingStateLoss();
    }


    public void hideFragment(FragmentActivity activity, BaseFragment fragment, String fragmentTag) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        setCustomAnimations(transaction);
        Fragment fragment1 = activity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (fragment1 != null) {
            transaction.hide(fragment1);
            transaction.commitAllowingStateLoss();
        }
    }

    public void removeFragment(FragmentActivity activity, BaseFragment fragment, String fragmentTag) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        Fragment fragment1 = activity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (fragment1 != null) {
            transaction.remove(fragment1);
            transaction.commitAllowingStateLoss();
        }

    }

    /**
     * set fragment animations
     * @param transaction
     */
    protected abstract void setCustomAnimations(FragmentTransaction transaction);

    /**
     * call back get layout id
     *
     * @return
     */
    protected abstract int getLayoutViewId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initEvent();
}
