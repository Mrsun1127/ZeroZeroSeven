package com.ffn.zerozeroseven.fragment;

import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrandRenzhen2Fragment extends BaseFragment {
    public static ErrandRenzhen2Fragment newInstance() {
        return new ErrandRenzhen2Fragment();
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected int setLayout() {
        return R.layout.errand_renzheng2;
    }

    @OnClick({R.id.bt_next})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                ErrandAuitActivity.mInstance.get().goVp(2);
                break;

        }
    }

    @Override
    protected void lazyLoad() {

    }
}
