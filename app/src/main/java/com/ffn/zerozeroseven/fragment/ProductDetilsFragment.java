package com.ffn.zerozeroseven.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ProductGoInAdapter;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.ui.InteralDetilsActivity;
import com.ffn.zerozeroseven.ui.ProductDetilsActivity;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetilsFragment extends BaseFragment {
    @Bind(R.id.rc_minegoin)
    RecyclerView rc_minegoin;
    @Bind(R.id.rc_allgoin)
    RecyclerView rc_allgoin;
    private ProductGoInAdapter goInAdapter;
    private ProductGoInAdapter goInAdapter1;

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void initDate() {
        rc_minegoin.setLayoutManager(new FullyLinearLayoutManager(bfCxt));
        rc_allgoin.setLayoutManager(new FullyLinearLayoutManager(bfCxt));
        goInAdapter = new ProductGoInAdapter(bfCxt);
        goInAdapter1 = new ProductGoInAdapter(bfCxt);
        rc_minegoin.setAdapter(goInAdapter);
        rc_allgoin.setAdapter(goInAdapter);
        List<String> s=new ArrayList<>();
        s.add("");
        s.add("");
        s.add("");
        s.add("");
        s.add("");
        s.add("");
        goInAdapter.addAll(s);
        goInAdapter1.addAll(s);

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_product_detils;
    }

    @Override
    protected void lazyLoad() {
    }

    @OnClick({R.id.bt_go})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_go:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, InteralDetilsActivity.class);
                break;

        }
    }
}
