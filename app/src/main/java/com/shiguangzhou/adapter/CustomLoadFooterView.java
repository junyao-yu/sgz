package com.shiguangzhou.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.shiguangzhou.R;

/**
 * Auth：yujunyao
 * Since: 2016/10/27 16:24
 * Email：yujunyao@yonglibao.com
 */

public class CustomLoadFooterView extends LinearLayout implements SwipeRefreshTrigger, SwipeTrigger {
    public CustomLoadFooterView(Context context) {
        super(context);
        init(context);
    }

    public CustomLoadFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomLoadFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
//        View layout = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_vertical_down, this, true);
        LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_vertical_down, this, true);
    }

    @Override
    public void onRefresh() {
//        setText("正在拼命加载数据...");
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onSwipe(int i, boolean b) {
//        setText("释放刷新");

    }

    @Override
    public void onRelease() {


    }

    @Override
    public void complete() {
//        setText("刷新成功");
    }

    @Override
    public void onReset() {

    }
}