package com.infrastructure.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @author weijingsong
 * @date 2020/2/14
 */
public abstract class BasePopupWindow extends PopupWindow {
    protected View mRootView;

    public BasePopupWindow(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = inflater.inflate(getLayoutViewId(), null);
        this.setBackgroundDrawable(null);
        this.setContentView(mRootView);
        this.setTouchable(true);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        initData();
        initView();
        initEvent();
    }


    /**
     * call back get layout id
     * @return
     */
    protected abstract int getLayoutViewId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initEvent();
}
