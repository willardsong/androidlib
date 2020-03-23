package com.infrastructure.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.infrastructure.R;


/**
 * @author weijingsong
 * @date 2020/2/14
 */
public abstract class BaseDialogFragment extends DialogFragment {
    protected View mRootView;
    private Context mContext;
    protected boolean isDialog;
    private DialogInterface.OnDismissListener mOnDismissListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isDialog) {
            setStyle(STYLE_NO_TITLE, R.style.Dialog);
        } else {
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_Holo_Light);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutViewId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
        initEvent();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (null != mOnDismissListener) {
            mOnDismissListener.onDismiss(dialog);
        }
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

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
