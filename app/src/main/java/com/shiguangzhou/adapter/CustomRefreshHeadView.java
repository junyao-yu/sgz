package com.shiguangzhou.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Auth：yujunyao
 * Since: 2016/10/27 16:24
 * Email：yujunyao@yonglibao.com
 */

public class CustomRefreshHeadView extends TextView implements SwipeRefreshTrigger, SwipeTrigger {
    public CustomRefreshHeadView(Context context) {
        super(context);
    }

    public CustomRefreshHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRefreshHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onRefresh() {
        setText("正在拼命加载数据...");
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onSwipe(int i, boolean b) {
        setText("释放刷新");

    }

    @Override
    public void onRelease() {


    }

    @Override
    public void complete() {
        setText("刷新成功");
    }

    @Override
    public void onReset() {

    }
}