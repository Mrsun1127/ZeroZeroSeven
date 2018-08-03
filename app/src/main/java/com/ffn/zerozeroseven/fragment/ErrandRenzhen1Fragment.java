package com.ffn.zerozeroseven.fragment;

import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrandRenzhen1Fragment extends BaseFragment {
    public static ErrandRenzhen1Fragment newInstance() {
        return new ErrandRenzhen1Fragment();
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected int setLayout() {
        return R.layout.errand_renzheng1;
    }

    @OnClick({R.id.bt_next})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                ErrandAuitActivity.mInstance.get().goVp(1);
                break;

        }
    }

    @Override
    protected void lazyLoad() {

    }
}
