package com.mouxuejie.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.mouxuejie.recyclerview.IScrollListener;
import com.mouxuejie.recyclerview.PagedRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PagedRecyclerView mPagedRecyclerView;
    private List<PersonBean> mList = new ArrayList<>();
    private InnerHandler mInnerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPagedRecyclerView = (PagedRecyclerView)findViewById(R.id.paged_recycler_view);
        mPagedRecyclerView.setHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_header_view, null));
        mPagedRecyclerView.setEmptyView(LayoutInflater.from(this).inflate(R.layout.layout_empty_view, null));
        mPagedRecyclerView.setAdapter(new MyPagedRecyclerViewAdapter(mList));
        mPagedRecyclerView.setIScrollListener(new IScrollListener() {
            @Override
            public void refresh() {
                mInnerHandler.removeMessages(InnerHandler.REFRESH);
                mInnerHandler.sendEmptyMessageDelayed(InnerHandler.REFRESH, 2000);
            }

            @Override
            public void loadMore() {
                mInnerHandler.removeMessages(InnerHandler.LOAD_MORE);
                mInnerHandler.sendEmptyMessageDelayed(InnerHandler.LOAD_MORE, 2000);
            }
        });
        mInnerHandler = new InnerHandler();
    }

    private class InnerHandler extends Handler {
        public static final int REFRESH = 0;
        public static final int LOAD_MORE = 1;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REFRESH:

                    break;
                case LOAD_MORE:

                    break;
                default:
                    break;
            }
        }
    }
}
