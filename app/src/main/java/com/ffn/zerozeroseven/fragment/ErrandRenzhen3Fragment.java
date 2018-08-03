package com.ffn.zerozeroseven.fragment;

import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrandRenzhen3Fragment extends BaseFragment {
    public static ErrandRenzhen3Fragment newInstance() {
        return new ErrandRenzhen3Fragment();
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected int setLayout() {
        return R.layout.errand_renzheng3;
    }



    @Override
    protected void lazyLoad() {

    }
}
