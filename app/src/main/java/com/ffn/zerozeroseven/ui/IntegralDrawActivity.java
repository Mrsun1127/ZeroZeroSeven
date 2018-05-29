package com.ffn.zerozeroseven.ui;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.InteralAdapter;
import com.ffn.zerozeroseven.base.BaseFullActivity;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.FullyGridLayoutManager;
import com.ffn.zerozeroseven.view.GridSpacingItemDecoration;
import com.ffn.zerozeroseven.view.mainscroll.SmoothRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IntegralDrawActivity extends BaseFullActivity {
    @Bind(R.id.refreshlayout)
    SmoothRefreshLayout refreshlayout;
    @Bind(R.id.recyclerView_with_recyclerView_in_coordinatorLayout)
    RecyclerView recycleview;
    private InteralAdapter adapter;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.colllayout)
    CollapsingToolbarLayout colllayout;

    @Override
    protected int setLayout() {
        return R.layout.activity_integraldraw;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bitis_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.more:
                        break;
                    case R.id.minesigh:
                        break;
                    case R.id.qiandao:
                        break;
                }
                return true;    //返回为true
            }
        });
        getSupportActionBar().setTitle("");
        recycleview.setLayoutManager(new FullyGridLayoutManager(this, 2));
        recycleview.addItemDecoration(new GridSpacingItemDecoration(2, 10, false));
        adapter = new InteralAdapter(this);
        recycleview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu); //解析menu布局文件到menu
        return true;
    }

    @Override
    protected void doMain() {
        List<String> s = new ArrayList<>();
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
