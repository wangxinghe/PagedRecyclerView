package com.mouxuejie.recyclerview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.mouxuejie.recyclerview.R;

/**
 * Created by wangxinghe1 on 2016/10/19.
 */

public class FooterView extends LinearLayout {
    private View mEndView;
    private View mErrorView;
    private View mLoadingView;

    public FooterView(Context context) {
        super(context);
        init();
    }

    public FooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void showEndView() {
        removeAllViews();
        addView(mEndView);
    }

    public void showErrorView() {
        removeAllViews();
        addView(mErrorView);
    }

    public void showLoadingView() {
        removeAllViews();
        addView(mLoadingView);
    }

    private void init() {
        mEndView = LayoutInflater.from(getContext()).inflate(R.layout.layout_footer_end_view, null);
        mErrorView = LayoutInflater.from(getContext()).inflate(R.layout.layout_footer_error_view, null);
        mLoadingView = LayoutInflater.from(getContext()).inflate(R.layout.layout_footer_load_more_view, null);
    }


}
