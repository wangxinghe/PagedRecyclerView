package com.mouxuejie.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mouxuejie.recyclerview.PagedRecyclerViewAdapter;

import java.util.List;

/**
 * Created by wangxinghe1 on 2016/10/10.
 */

public class MyPagedRecyclerViewAdapter extends PagedRecyclerViewAdapter<PersonBean> {

    public MyPagedRecyclerViewAdapter(List<PersonBean> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder createItemViewHolder(View parent) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_view, null));
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
