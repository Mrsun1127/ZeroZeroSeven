package com.ffn.zerozeroseven.fragment;

import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseFragment;

import butterknife.ButterKnife;

public class ErrandMineRunFragment extends BaseFragment {

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected int setLayout() {
        return R.layout.errand_fragment_minerun;
    }

    @Override
    protected void lazyLoad() {

    }
}
