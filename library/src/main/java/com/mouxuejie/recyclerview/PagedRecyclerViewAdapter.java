package com.mouxuejie.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mouxuejie.recyclerview.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxinghe1 on 2016/10/10.
 */

public abstract class PagedRecyclerViewAdapter<T extends Bean> extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_FOOTER = 1;
    private static final int VIEW_TYPE_ITEM = 2;

    private List<T> mList = new ArrayList<>();
    private View mHeaderView;
    private View mFooterView;

    public PagedRecyclerViewAdapter(List<T> list) {
        mList = list;
    }

    public void refresh(List<T> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void add(List<T> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return mList.get(position);
    }

    private boolean hasHeader() {
        return mHeaderView != null;
    }

    private boolean hasFooter() {
        return mFooterView != null;
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() + 2 : 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else if (position == getItemCount() - 1) {
            return VIEW_TYPE_FOOTER;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                viewHolder = new RViewHolder(mHeaderView);
                break;
            case VIEW_TYPE_FOOTER:
                viewHolder = new RViewHolder(mFooterView);
                break;
            case VIEW_TYPE_ITEM:
                viewHolder = createItemViewHolder(parent);
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (VIEW_TYPE_ITEM == getItemViewType(position)) {
            bindItem(holder, position);
        }
    }

    private class RViewHolder extends RecyclerView.ViewHolder {

        RViewHolder(View itemView) {
            super(itemView);
        }
    }

    public abstract RecyclerView.ViewHolder createItemViewHolder(View parent);

    public abstract void bindItem(RecyclerView.ViewHolder holder, int position);
}
