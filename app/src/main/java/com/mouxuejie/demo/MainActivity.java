package com.mouxuejie.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.mouxuejie.recyclerview.IScrollListener;
import com.mouxuejie.recyclerview.PagedRecyclerView;
import com.mouxuejie.recyclerview.PagedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PagedRecyclerView mPagedRecyclerView;
    private PagedRecyclerViewAdapter mPagedRecyclerViewAdapter;
    private InnerHandler mInnerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPagedRecyclerView = (PagedRecyclerView)findViewById(R.id.paged_recycler_view);
        mPagedRecyclerView.setHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_header_view, null));
        mPagedRecyclerView.setEmptyView(LayoutInflater.from(this).inflate(R.layout.layout_empty_view, null));
        mPagedRecyclerView.setIScrollListener(new IScrollListener() {
            int loadmoreCount = 0;
            @Override
            public void refresh() {
                loadmoreCount = 0;
                mInnerHandler.removeMessages(InnerHandler.REFRESH);
                mInnerHandler.sendEmptyMessageDelayed(InnerHandler.REFRESH, 500);
            }

            @Override
            public void loadMore() {
                if (loadmoreCount == 0) {
                    mInnerHandler.removeMessages(InnerHandler.LOAD_MORE);
                    mInnerHandler.sendEmptyMessageDelayed(InnerHandler.LOAD_MORE, 500);
                } else if (loadmoreCount == 1) {
                    mInnerHandler.removeMessages(InnerHandler.LOAD_FINISH);
                    mInnerHandler.sendEmptyMessageDelayed(InnerHandler.LOAD_FINISH, 500);
                }
                loadmoreCount ++;
            }
        });
        mPagedRecyclerViewAdapter = new MyPagedRecyclerViewAdapter(this);
        mPagedRecyclerView.setAdapter(mPagedRecyclerViewAdapter);
        mInnerHandler = new InnerHandler();
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

    private List<PersonBean> combineSecondPage() {
        List<PersonBean> list = new ArrayList<>();
        for (int i = 20; i < 40; i++) {
            PersonBean bean = new PersonBean();
            bean.name = "name " + i;
            bean.age = "age " + i;
            list.add(bean);
        }
        return list;
    }

    private List<PersonBean> combineFinishPage() {
        List<PersonBean> list = new ArrayList<>();
        for (int i = 40; i < 50; i++) {
            PersonBean bean = new PersonBean();
            bean.name = "name " + i;
            bean.age = "age " + i;
            list.add(bean);
        }
        return list;
    }

    private class InnerHandler extends Handler {
        public static final int REFRESH = 0;
        public static final int LOAD_MORE = 1;
        public static final int LOAD_FINISH = 2;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REFRESH:
                    mPagedRecyclerView.setRefreshing(false);
                    mPagedRecyclerViewAdapter.refresh(combineFirstPage());
                    break;
                case LOAD_MORE:
                    mPagedRecyclerViewAdapter.add(combineSecondPage());
                    break;
                case LOAD_FINISH:
                    mPagedRecyclerViewAdapter.add(combineFinishPage());
                    break;
                default:
                    break;
            }
        }
    }
}
