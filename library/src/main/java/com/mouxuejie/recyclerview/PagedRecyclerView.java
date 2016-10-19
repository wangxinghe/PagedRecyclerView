package com.mouxuejie.recyclerview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by wangxinghe1 on 2016/10/10.
 */

public class PagedRecyclerView extends FrameLayout {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout mHeaderEmptyContainerLL;
    private FrameLayout mHeaderFL;
    private FrameLayout mEmptyFL;
    private RecyclerView mRecyclerView;

    private IScrollListener mScrollListener;

    private int mScrollY;

    public PagedRecyclerView(Context context) {
        super(context);
        init();
    }

    public PagedRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PagedRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setIScrollListener(IScrollListener scrollListener) {
        mScrollListener = scrollListener;
    }

    public void setHeaderView(View headerView) {
        if (headerView == null) return;
        mHeaderEmptyContainerLL.removeView(headerView);
        mHeaderEmptyContainerLL.addView(headerView);
    }

    public void setEmptyView(View emptyView) {
        if (emptyView == null) return;
        mEmptyFL.removeView(emptyView);
        mEmptyFL.addView(emptyView);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    public void setRefreshing(boolean refreshing) {
        mSwipeRefreshLayout.setRefreshing(refreshing);
    }

    private void init() {
        inflate(getContext(), R.layout.layout_paged_recyclerview, this);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_rl);
        mHeaderEmptyContainerLL = (LinearLayout)findViewById(R.id.header_empty_container_ll);
        mHeaderFL = (FrameLayout)findViewById(R.id.header);
        mEmptyFL = (FrameLayout)findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mScrollListener != null) {
                    mScrollListener.refresh();
                }
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mScrollY += dy;
                mHeaderEmptyContainerLL.setTranslationY(mScrollY);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                if (lastCompletelyVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 2) {
                    if (mScrollListener != null) {
                        mScrollListener.loadMore();
                    }
                }
            }
        });
    }



}
