package com.ffn.zerozeroseven.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.InteralAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.view.FullyGridLayoutManager;
import com.ffn.zerozeroseven.view.GridSpacingItemDecoration;
import com.ffn.zerozeroseven.view.TopView;
import com.ffn.zerozeroseven.view.mainscroll.SmoothRefreshLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IntegralDrawActivity extends BaseActivity {
    @Bind(R.id.refreshlayout)
    SmoothRefreshLayout refreshlayout;
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.recycleview)
    RecyclerView recycleview;

    private InteralAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_integraldraw;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("积分抽奖");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        recycleview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        recycleview.setLayoutManager(new FullyGridLayoutManager(this, 2));
        recycleview.addItemDecoration(new GridSpacingItemDecoration(2, 10, false));
        adapter = new InteralAdapter(this);
        recycleview.setAdapter(adapter);
    }

    @Override
    protected void doMain() {
        List<String> s=new ArrayList<>();
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        adapter.addAll(s);
    }
}
