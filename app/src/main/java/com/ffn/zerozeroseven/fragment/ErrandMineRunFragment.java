package com.ffn.zerozeroseven.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ErrandMineRunAdapter;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrandMineRunFragment extends BaseFragment {
    @Bind(R.id.recycleview)
    RecyclerView recycleview;

    public static ErrandMineRunFragment newInstance() {
        return new ErrandMineRunFragment();
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        recycleview.setLayoutManager(new FullyLinearLayoutManager(bfCxt));
        ErrandMineRunAdapter adapter = new ErrandMineRunAdapter(bfCxt);
        recycleview.setAdapter(adapter);
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        adapter.addAll(strings);
    }

    @Override
    protected int setLayout() {
        return R.layout.errand_fragment_minerun;
    }

    @OnClick({R.id.iv_audit})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.iv_audit:

                break;

        }
    }

    @Override
    protected void lazyLoad() {

    }
}
