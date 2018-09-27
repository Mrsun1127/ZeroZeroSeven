package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.DriverUserInfo;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ClearEditText;
import com.ffn.zerozeroseven.view.TopView;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverCommitActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.cle_card)
    ClearEditText cle_card;
    @Bind(R.id.cle_name)
    ClearEditText cle_name;
    @Bind(R.id.cb_default)
    CheckBox cb_default;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_money)
    TextView tv_money;
    public static WeakReference<DriverCommitActivity> mInstance;

    @Override
    protected int setLayout() {
        return R.layout.activity_driver_commit;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mInstance = new WeakReference<>(this);
        topView.setTopText(getIntent().getStringExtra("title"));
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
    }

    @OnClick({R.id.tv_sub})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.tv_sub:
                checkSub();
                break;

        }
    }

    private void checkSub() {
        String name = cle_name.getText().toString().trim();
        String idCard = cle_card.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            ToastUtils.showShort("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(idCard)) {
            ToastUtils.showShort("请输入身份证");
            return;
        }
        if (!cb_default.isChecked()) {
            ToastUtils.showShort("请同意协议");
            return;
        }
        DriverUserInfo driverUserInfo = new DriverUserInfo();
        driverUserInfo.idCard = idCard;
        driverUserInfo.name = name;
        driverUserInfo.phoneNumber = BaseAppApplication.getInstance().getLoginUser().getPhone();
        driverUserInfo.money = getIntent().getStringExtra("money");
        Bundle bundle = new Bundle();
        bundle.putSerializable("driverInfo", driverUserInfo);
        bundle.putString("money", getIntent().getStringExtra("money"));
        bundle.putString("pay", "driver");
        bundle.putString("classId", getIntent().getStringExtra("classId"));
        ZeroZeroSevenUtils.SwitchActivity(DriverCommitActivity.this, PayMoneyNewActivity.class, bundle);
    }

    @Override
    protected void doMain() {
        tv_phone.setText(BaseAppApplication.getInstance().getLoginUser().getPhone());
        tv_money.setText("费用总计："+getIntent().getStringExtra("money")+"元");
    }
}
