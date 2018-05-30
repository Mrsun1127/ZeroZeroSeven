package com.ffn.zerozeroseven.fragment;

import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.ui.InteralDetilsActivity;
import com.ffn.zerozeroseven.ui.ProductDetilsActivity;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetilsFragment extends BaseFragment {
    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);
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
