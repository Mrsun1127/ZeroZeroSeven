package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseFragment;


import butterknife.Bind;
import butterknife.ButterKnife;

public class DriverDetilsFourFragment extends BaseFragment {
    @Bind(R.id.tv_content)
    TextView tv_content;

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    private String content;

    public static DriverDetilsFourFragment newInstance(String s) {
        Bundle args = new Bundle();
        args.putString("s", s);
        DriverDetilsFourFragment fragment = new DriverDetilsFourFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content = getArguments().getString("s");
    }

    @Override
    public void initDate() {
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_driver_four;
    }

    @Override
    protected void lazyLoad() {
        tv_content.setText(content);
    }
}
