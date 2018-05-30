package com.ffn.zerozeroseven.ui;

import android.support.v4.view.ViewPager;
import android.widget.TableLayout;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductDetilsActivity extends BaseActivity {
    @Bind(R.id.tab_level)
    TableLayout tableLayout;
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Override
    protected int setLayout() {
        return R.layout.activity_productdetils;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected void doMain() {

    }
}
