package com.infrastructure.adapter.listviewAdapter;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;

import java.util.List;

public abstract class CommonAbListviewAdapter<T> extends MultiItemTypeAdapter<T> {
    public CommonAbListviewAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAbListviewAdapter.this.convert(holder, t, position);
            }
        });
    }

    @Override
    protected abstract void convert(ViewHolder viewHolder, T item, int position);

    /**
     * 局部更新数据，调用一次getView()方法；Google推荐的做法
     *
     * @param listView 要更新的listview
     * @param position 要更新的位置
     */
    public void notifyDataSetChanged(AbsListView listView, int position) {
        /*第一个可见的位置*/
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        /*最后一个可见的位置*/
        int lastVisiblePosition = listView.getLastVisiblePosition();

        /*在看见范围内才更新，不可见的滑动后自动会调用getView方法更新*/
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /*获取指定位置view对象*/
            View view = listView.getChildAt(position - firstVisiblePosition);
            getView(position, view, listView);
        }

    }

}
