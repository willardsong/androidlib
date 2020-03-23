package com.infrastructure.adapter.recyclerview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import static android.view.View.ALPHA;

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    public static final String ANIM_TRANSLATION_Y = "translationY";
    public static final String ANIM_TRANSLATION_X = "translationX";
    public static final float ANIM_ALPHA_0 = 0.0f;
    public static final float ANIM_ALPHA_1 = 1.0f;
    protected Context mContext;
    private List<T> mDatas;

    private ItemViewDelegateManager mItemViewDelegateManager;
    private OnItemSelectListener mOnItemSelectListener;
    private boolean isSweetSheet = false;
    private OnItemClickListener mOnItemClickListener;
    private OnItemKeyListener mOnitemKeyListener;
    private int currentPos = -5;
    private ViewHolder viewHolder = null;
    private boolean isSetselection = false;
    // 分页
    private int iTotalSize = 0;
    private int iPageTotal = 0;
    private int iCurrentPage = 1;
    private int iPageSize;

    public MultiItemTypeAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
        mItemViewDelegateManager = new ItemViewDelegateManager();
        setHasStableIds(true);
    }

    public MultiItemTypeAdapter(Context context, List<T> datas, int pageSize) {
        mContext = context;
        mDatas = datas;
        iPageSize = pageSize;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    public int getiTotalSize() {
        return iTotalSize;
    }

    public void setiTotalSize(int iTotalSize) {
        this.iTotalSize = iTotalSize;
    }

    public int getiPageTotal() {
        return iPageTotal;
    }

    public void setiPageTotal(int iPageTotal) {
        this.iPageTotal = iPageTotal;
    }

    public int getiCurrentPage() {
        return iCurrentPage;
    }

    public void setiCurrentPage(int iCurrentPage) {
        this.iCurrentPage = iCurrentPage;
    }

    public int getiPageSize() {
        return iPageSize;
    }

    public void setiPageSize(int iPageSize) {
        this.iPageSize = iPageSize;
    }

    public void notifyDatas(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position + 10000;
    }

    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) {
            return super.getItemViewType(position);
        }
        return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        ViewHolder holder = ViewHolder.createViewHolder(mContext, parent, layoutId);
        onViewHolderCreated(holder, holder.getConvertView());
        setListener(parent, holder, viewType);
        viewHolder = holder;
        return holder;
    }

    public ViewHolder getViewHolder() {
        return viewHolder;
    }

    public void onViewHolderCreated(ViewHolder holder, View itemView) {

    }

    public void convert(ViewHolder holder, T t) {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) {
            return;
        }
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, viewHolder, position);
                }
            }
        });

        viewHolder.getConvertView().setFocusable(true);
        viewHolder.getConvertView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (mOnItemSelectListener != null) {
                        int position = viewHolder.getAdapterPosition();
                        if (position >= 0) {
                            mOnItemSelectListener.onItemSelect(v, viewHolder, position);
                        }

                    }
                } else {
                    if (mOnItemSelectListener != null) {
                        int position = viewHolder.getAdapterPosition();
                        if (position >= 0) {
                            mOnItemSelectListener.onItemNotSelect(v, viewHolder, position);
                        }
                    }
                }
            }
        });

        viewHolder.getConvertView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (mOnitemKeyListener != null) {
                    {
                        if (event.getAction() == KeyEvent.ACTION_DOWN) {
                            int position = viewHolder.getLayoutPosition();
                            if (position >= 0) {
                                return mOnitemKeyListener.onItemKey(v, viewHolder.getLayoutPosition(), keyCode, event);
                            } else {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        });

    }

    protected void setSweetSheet() {
        this.isSweetSheet = true;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        convert(viewHolder, mDatas.get(position));
        if (currentPos == position && isSetselection) {
            isSetselection = false;
            viewHolder.getConvertView().requestFocus();
            if (mOnItemSelectListener != null) {
                mOnItemSelectListener.onItemSelect(viewHolder.getConvertView(), viewHolder, currentPos);
            }
        }
        if (isSweetSheet) {
            viewHolder.itemView.setAlpha(0);
            viewHolder.itemView.setTranslationY(300);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(viewHolder.itemView, ANIM_TRANSLATION_Y, 500, 0);
            translationY.setDuration(300);
            translationY.setInterpolator(new AnticipateOvershootInterpolator());
            ObjectAnimator alphaIn = ObjectAnimator.ofFloat(viewHolder.itemView, ALPHA, 0, 1);
            alphaIn.setDuration(100);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(translationY, alphaIn);
            animatorSet.setStartDelay(viewHolder.getAdapterPosition());
            animatorSet.start();
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        } else {
            return mDatas.size();
        }
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemTypeAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public void setmOnItemSelectListener(OnItemSelectListener itemSelectListener) {
        mOnItemSelectListener = itemSelectListener;
    }

    public void setmOnItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public void setmOnitemKeyListener(OnItemKeyListener itemKeyListener) {
        mOnitemKeyListener = itemKeyListener;
    }

    public boolean hasNextPage() {
        return iCurrentPage >= 1 && iCurrentPage < iPageTotal;
    }

    public boolean hasPreviousPage() {
        return iCurrentPage > 1 && iCurrentPage <= iPageTotal;
    }

    public String getPageString() {
        return iCurrentPage + " / " + iPageTotal;
    }

    public boolean isWholePage() {
        return iTotalSize % iPageSize == 0;
    }

    public void getPageTotal() {
        if (isWholePage()) {
            iPageTotal = iTotalSize / iPageSize;
        } else {
            iPageTotal = (iTotalSize / iPageSize) + 1;
        }
    }

    public List<T> formatPageData(int start, int end, List<T> data, List<T> dataAll) {
        data.clear();
        for (int index = start; index < end; index++) {
            data.add(dataAll.get(index));
        }
        return data;
    }

    public int getSelection() {
        return currentPos;
    }

    public void setSelection(int pos) {
        currentPos = pos;
        isSetselection = true;
        notifyDataSetChanged();
    }

    public interface OnItemSelectListener {
        void onItemSelect(View view, ViewHolder holder, int position);

        void onItemNotSelect(View view, ViewHolder holder, int position);
    }

    public interface OnItemKeyListener {
        boolean onItemKey(View view, int position, int keyCode, KeyEvent event);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
    }
}
