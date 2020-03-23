package com.infrastructure.adapter.recyclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.infrastructure.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private List<Integer> mImgDatas;
    private List<Drawable> mIconDatas;
    private List<String> mTxtDatas;
    private List<Integer> mHeights;
    private int mCurrentPosition;
    private ScaleAnimationEffect mScaleAnimationEffect;
    private Context mContext;
    private OnItemClickListener mListener;
    private onItemKeyListener mOnItemKeyListener;
    private OnItemSelectListener mSelectListener;
    private boolean mIsItemFocusable = true;
    private int mTag = 0;

    public RecyclerViewAdapter(Context context, List<Integer> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mImgDatas = datas;
        mScaleAnimationEffect = new ScaleAnimationEffect();
    }

    public RecyclerViewAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mScaleAnimationEffect = new ScaleAnimationEffect();

    }

    public void setTag(int tag) {
        mTag = tag;
    }

    public void setDatas(List<Integer> datas, List<Drawable> iconDatas, List<String> txtData) {
        mIconDatas = iconDatas;
        mTxtDatas = txtData;
        mImgDatas = datas;
        notifyDataSetChanged();
    }

    public void setItemFocusable(boolean focusable) {
        mIsItemFocusable = focusable;
        notifyDataSetChanged();
    }

    public void setOnItemKeyListener(onItemKeyListener onItemKeyListener) {
        mOnItemKeyListener = onItemKeyListener;
    }

    public void setOnItemSelectListener(OnItemSelectListener listener) {
        mSelectListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_rv, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.mImg = (ImageView) view.findViewById(R.id.recylerView_item);
        holder.mTxt = (TextView) view.findViewById(R.id.txt_id);
        holder.mIcon = (ImageView) view.findViewById(R.id.recylerView_icon);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        getRandomHeight(mImgDatas.size());
        params.height = mHeights.get(position % mImgDatas.size());
        holder.itemView.setLayoutParams(params);
        holder.mImg.setImageResource(mImgDatas.get(position % mImgDatas.size()));
        holder.mTxt.setText(mTxtDatas.get(position % mTxtDatas.size()));
        holder.mIcon.setBackground(mIconDatas.get(position % mIconDatas.size()));
        holder.itemView.setFocusable(true);
        holder.itemView.setTag(position);
        holder.itemView.setTag(R.id.tag, mTag);
        holder.itemView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (mOnItemKeyListener != null) {
                    return mOnItemKeyListener.onItemKey(v, keyCode, event);
                }
                return false;
            }
        });
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mCurrentPosition = (int) holder.itemView.getTag();
                    //if(holder.mTxt.getWidth() > 200)
                    {
                        holder.mTxt.setFocusable(true);
                        holder.mTxt.setMarqueeRepeatLimit(-1);
//                        holder.mTxt.startOrStopMarquee(true);
                    }
                    mSelectListener.onItemSelect(holder.itemView, mCurrentPosition);
                    showOnFocusAnimation(v, 1.2f);
                } else {
//                    holder.mTxt.startOrStopMarquee(false);
                    showLoseFocusAnimation(v, 1.2f);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTag(R.id.tag, mTag);
                Log.e(TAG, "----------position---" + holder.getLayoutPosition());
                System.out.println(mListener);
                if (mListener != null) {
                    mListener.onItemClick(v, position % mTxtDatas.size());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    private void getRandomHeight(int size) {
        mHeights = new ArrayList<>();
        for (int i = 0; i < size; i++) {
//            mHeights.add((int) DensityUtils.dp2px(150));
            mHeights.add(150);
        }
    }

    private void showOnFocusAnimation(View v, float scale) {
        mScaleAnimationEffect.setAttributs(1.0f, 1.3f, 1.0f, scale, 100);
        Animation anim = mScaleAnimationEffect.createAnimation();
        v.startAnimation(anim);
    }

    private void showLoseFocusAnimation(View v, float scale) {

        mScaleAnimationEffect.setAttributs(1.3f, 1.0f, scale, 1.0f, 100);
        Animation anim = mScaleAnimationEffect.createAnimation();
        v.startAnimation(anim);
    }

    public interface onItemKeyListener {
        boolean onItemKey(View v, int keyCode, KeyEvent event);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemSelectListener {
        void onItemSelect(View view, int position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        ImageView mIcon;
        TextView mTxt;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
