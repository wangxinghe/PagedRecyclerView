package com.mouxuejie.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mouxuejie.recyclerview.PagedRecyclerViewAdapter;

/**
 * Created by wangxinghe1 on 2016/10/10.
 */

public class MyPagedRecyclerViewAdapter extends PagedRecyclerViewAdapter<PersonBean> {

    public MyPagedRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_view, parent, false));
    }

    @Override
    public void bindItem(RecyclerView.ViewHolder holder, int position) {
        PersonBean personBean  = getItem(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
        itemViewHolder.mNameTv.setText(personBean.name);
        itemViewHolder.mAgeTv.setText(personBean.age);
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTv;
        private TextView mAgeTv;

        ItemViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView)itemView.findViewById(R.id.name_tv);
            mAgeTv = (TextView)itemView.findViewById(R.id.age_tv);
        }

    }
}
