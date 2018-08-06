package com.ffn.zerozeroseven.ui;

import android.support.v7.widget.RecyclerView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ErrandLevelAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ErrandLevelActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.recycleview)
    RecyclerView recyclerView;

    @Override
    protected int setLayout() {
        return R.layout.activity_errand_level;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("跑腿等级");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        ErrandLevelAdapter adapter = new ErrandLevelAdapter(this);
        recyclerView.setAdapter(adapter);
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        adapter.addAll(strings);
    }

    @Override
    protected void doMain() {

    }
}
