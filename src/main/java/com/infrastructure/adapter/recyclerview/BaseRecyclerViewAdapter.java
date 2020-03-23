package com.infrastructure.adapter.recyclerview;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by MK on 2016/12/19.
 */
public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ItemViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private OnItemSelectListener mOnItemSelectListener;
    private onItemFocusChangedListener mOnItemFocusChangedListener;
    private onItemKeyListener mOnItemKeyListener;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, holder.getLayoutPosition());
                    mOnItemClickListener.onItemLongClick(v, holder.getLayoutPosition());
                }
            });

        }
        if (mOnItemFocusChangedListener != null) {
            holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    mOnItemFocusChangedListener.onItemFocusChanged(v, hasFocus);
                }
            });
        }
        if (mOnItemKeyListener != null) {
            holder.itemView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    mOnItemKeyListener.onKey(v, holder.getLayoutPosition());
                    return false;
                }
            });
        }
        if (mOnItemSelectListener != null) {
            holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    mOnItemSelectListener.OnItemSelect(v, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setOnItemFocusChangedListener(onItemFocusChangedListener mOnItemFocusChangedListener) {
        this.mOnItemFocusChangedListener = mOnItemFocusChangedListener;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemSelectListener(OnItemSelectListener mOnItemSelectListener) {
        this.mOnItemSelectListener = mOnItemSelectListener;
    }

    public void setOnItemKeyListener(onItemKeyListener mOnItemKeyListener) {
        this.mOnItemKeyListener = mOnItemKeyListener;

    }

    public interface onItemKeyListener {
        void onKey(View view, int position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public interface OnItemSelectListener {
        void OnItemSelect(View view, int postion);
    }

    public interface onItemFocusChangedListener {
        void onItemFocusChanged(View v, boolean hasFocus);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTxt;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }

}
