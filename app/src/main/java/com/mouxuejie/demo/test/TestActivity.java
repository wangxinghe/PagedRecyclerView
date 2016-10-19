package com.mouxuejie.demo.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mouxuejie.demo.PersonBean;
import com.mouxuejie.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxinghe1 on 2016/10/19.
 */

public class TestActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new TestRecyclerViewAdapter(this, combineFirstPage()));
    }

    private List<PersonBean> combineFirstPage() {
        List<PersonBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            PersonBean bean = new PersonBean();
            bean.name = "name " + i;
            bean.age = "age " + i;
            list.add(bean);
        }
        return list;
    }
}
