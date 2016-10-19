package com.mouxuejie.demo.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mouxuejie.demo.PersonBean;
import com.mouxuejie.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxinghe1 on 2016/10/19.
 */

public class TestRecyclerViewAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_ITEM = 0;
    private Context mContext;
    private List<PersonBean> mList = new ArrayList<>();

    public TestRecyclerViewAdapter(Context context, List<PersonBean> list) {
        super();
        mContext = context;
        mList = list;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        PersonBean bean = mList.get(position);
        myViewHolder.mNameTv.setText(bean.name);
        myViewHolder.mAgeTv.setText(bean.age);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_item_view, parent, false));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTv;
        private TextView mAgeTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.name_tv);
            mAgeTv = (TextView)itemView.findViewById(R.id.age_tv);
        }
    }
}
