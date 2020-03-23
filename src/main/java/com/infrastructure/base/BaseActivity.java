package com.infrastructure.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.infrastructure.R;

/**
 * @author weijingsong
 * @date 2020/2/12
 */
public abstract class BaseActivity extends Activity {
    protected View mRootView;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(getLayoutViewId());
        mRootView = getContentView();
        initData();
        initView();
        initEvent();
    }

    @Override
    public void setContentView(int layoutResID) {
        if (-1 == layoutResID) {
            return;
        }
        super.setContentView(layoutResID);
    }

    private View getContentView() {
        return ((ViewGroup) this.findViewById(R.id.content)).getChildAt(0);
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * call back activity layout id
     *
     * @return
     */
    protected abstract int getLayoutViewId();

    /**
     * init data
     */
    protected abstract void initData();

    /**
     * init view
     */
    protected abstract void initView();

    /**
     * init event
     */
    protected abstract void initEvent();
}
