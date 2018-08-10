package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;
import com.ffn.zerozeroseven.ui.PayMoneyNewActivity;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import butterknife.Bind;
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

    @Bind(R.id.cb)
    CheckBox cb;

    @OnClick({R.id.bt_next})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                if (cb.isChecked()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("money", "99");
                    bundle.putString("pay", "renzheng");
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, PayMoneyNewActivity.class, bundle);
                }
                break;

        }
    }

    @Override
    protected void lazyLoad() {

    }
}
