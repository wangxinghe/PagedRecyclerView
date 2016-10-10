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

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
    }

    public abstract RecyclerView.ViewHolder createItemViewHolder(View parent);

    public abstract void bindItem(RecyclerView.ViewHolder holder, int position);

    private boolean hasHeader() {
        return mHeaderView != null;
    }

    private boolean hasFooter() {
        return mFooterView != null;
    }

    private boolean isHeaderView(int position) {
        return hasHeader() && position == 0;
    }

    private boolean isFooterView(int position) {
        return hasFooter() && position == getItemCount() - 1;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mList != null) {
            count += mList.size();
        }
        if (hasHeader()) {
            count += 1;
        }
        if (hasFooter()) {
            count += 1;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return VIEW_TYPE_HEADER;
        } else if (isFooterView(position)) {
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

}
