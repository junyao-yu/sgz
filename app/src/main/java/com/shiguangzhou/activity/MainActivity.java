package com.shiguangzhou.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shiguangzhou.R;
import com.shiguangzhou.adapter.InvestPlanAdapter;
import com.shiguangzhou.third.ItemDecorator;
import com.shiguangzhou.third.LayoutManager;
import com.shiguangzhou.third.LinearSLM;

/**
 * Auth：yujunyao
 * Since: 2016/10/25 15:14
 * Email：yujunyao@yonglibao.com
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private InvestPlanAdapter mAdapter;
    private int mHeaderDisplay = 9;
    private View line;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        line = findViewById(R.id.line);

        ItemDecorator decor = new ItemDecorator.Builder(this)
                .decorateSlm(LinearSLM.ID)
                .build();
        mRecyclerView.addItemDecoration(decor);

        mAdapter = new InvestPlanAdapter(this, mHeaderDisplay);
        LayoutManager layoutManager = new LayoutManager.Builder(this)
                .addAdapter(mAdapter)
                .build();
        mRecyclerView.setLayoutManager(layoutManager);
//        mAdapter.setHeaderDisplay(mHeaderDisplay);
        mRecyclerView.setAdapter(mAdapter);
//        setHeadersSticky(true);

//        line.setBackgroundResource(R.color.color_1191FC);
//        line.bringToFront();
    }

//    public void setHeadersSticky(boolean areHeadersSticky) {
//        mHeaderDisplay = areHeadersSticky ? mHeaderDisplay
//                | LayoutManager.LayoutParams.HEADER_STICKY
//                : mHeaderDisplay & ~LayoutManager.LayoutParams.HEADER_STICKY;
//        mAdapter.setHeaderDisplay(mHeaderDisplay);
//    }
}
