package com.infrastructure.adapter.recyclerview;


import androidx.collection.SparseArrayCompat;

public class ItemViewDelegateManager<T> {
    private SparseArrayCompat<ItemViewDelegate<T>> mDelegates = new SparseArrayCompat();

    public int getItemViewDelegateCount() {
        return mDelegates.size();
    }

    public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate) {
        int viewType = mDelegates.size();
        if (delegate != null) {
            mDelegates.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    public ItemViewDelegateManager<T> addDelegate(int viewType, ItemViewDelegate<T> delegate) {
        if (mDelegates.get(viewType) != null) {
            throw new IllegalArgumentException("An ItemViewDelegate is already registered for the viewType = " + viewType + ". Already registered ItemViewDelegate is " + mDelegates.get(viewType));
        }
        mDelegates.put(viewType, delegate);
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("ItemViewDelegate is null");
        }
        int indexToRemove = mDelegates.indexOfValue(delegate);

        if (indexToRemove >= 0) {
            mDelegates.removeAt(indexToRemove);
        }
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(int itemType) {
        int indexToRemove = mDelegates.indexOfKey(itemType);

        if (indexToRemove >= 0) {
            mDelegates.removeAt(indexToRemove);
        }
        return this;
    }

    public int getItemViewType(T item, int position) {
        int delegatesCount = mDelegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            ItemViewDelegate<T> delegate = mDelegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                return mDelegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public void convert(ViewHolder holder, T item, int position) {
        int delegatesCount = mDelegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            ItemViewDelegate<T> delegate = mDelegates.valueAt(i);

            if (delegate.isForViewType(item, position)) {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }

    public ItemViewDelegate getItemViewDelegate(int viewType) {
        return mDelegates.get(viewType);
    }

    public int getItemViewLayoutId(int viewType) {
        return getItemViewDelegate(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(ItemViewDelegate itemViewDelegate) {
        return mDelegates.indexOfValue(itemViewDelegate);
    }

    public ItemViewDelegate getItemViewDelegate(T item, int position) {
        int delegatesCount = mDelegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            ItemViewDelegate<T> delegate = mDelegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                return delegate;
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public int getItemViewLayoutId(T item, int position) {
        return getItemViewDelegate(item, position).getItemViewLayoutId();
    }
}
