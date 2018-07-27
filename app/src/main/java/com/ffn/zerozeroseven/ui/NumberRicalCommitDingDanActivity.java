package com.ffn.zerozeroseven.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.ShouHuoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.AllAdrInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumberRicalCommitDingDanActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.tv_getname)
    TextView tv_getname;
    @Bind(R.id.tv_getadr)
    TextView tv_getadr;

    @Override
    protected int setLayout() {
        return R.layout.activity_numberical_commitdingdan;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("提交订单");
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

    @Override
    protected void doMain() {
        initAdr(0);
    }

    @Bind(R.id.tv_paymoney)
    TextView tv_paymoney;
    @Bind(R.id.rl_select_adr)
    RelativeLayout selectCar;
    @Bind(R.id.tv_add_adr)
    TextView addCar;
    @Bind(R.id.et_remark)
    EditText et_remark;

    private void initAdr(final int position) {
        AllAdrInfo allAdrInfo = new AllAdrInfo();
        allAdrInfo.setFunctionName("ListUserAddress");
        OkGoUtils okGoUtils = new OkGoUtils(this);
        okGoUtils.httpPostJSON(allAdrInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ShouHuoInfo shouHuoInfo = JSON.parseObject(response, ShouHuoInfo.class);
                if (shouHuoInfo.getCode() == 0) {
                    if (shouHuoInfo.getData().getAddresses().size() > 0) {
                        addCar.setVisibility(View.GONE);
                        String contactBuilding = "";
                        if (position != 0) {
                            tv_getadr.setText(shouHuoInfo.getData().getAddresses().get(position).getContactSchool() + shouHuoInfo.getData().getAddresses().get(position).getContactBuilding() + shouHuoInfo.getData().getAddresses().get(0).getContactDorm());
                            tv_getname.setText(shouHuoInfo.getData().getAddresses().get(position).getContactName() + "  " + shouHuoInfo.getData().getAddresses().get(position).getContactPhone());
                            contactBuilding = shouHuoInfo.getData().getAddresses().get(position).getContactBuilding();
                        } else {
                            tv_getadr.setText(shouHuoInfo.getData().getAddresses().get(0).getContactSchool() + shouHuoInfo.getData().getAddresses().get(0).getContactBuilding() + shouHuoInfo.getData().getAddresses().get(0).getContactDorm());
                            tv_getname.setText(shouHuoInfo.getData().getAddresses().get(0).getContactName() + "  " + shouHuoInfo.getData().getAddresses().get(0).getContactPhone());
                            contactBuilding = shouHuoInfo.getData().getAddresses().get(0).getContactBuilding();
                        }

                    } else {
                        addCar.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }


    @OnClick({R.id.bt_pay, R.id.rl_select_adr, R.id.tv_add_adr})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_pay:
                ZeroZeroSevenUtils.SwitchActivity(NumberRicalCommitDingDanActivity.this,PayMoneyNewActivity.class);
                break;
            case R.id.rl_select_adr:
                ZeroZeroSevenUtils.SwitchActivity(NumberRicalCommitDingDanActivity.this, SelectAdrMannGerActivity.class, null, 2);
                break;
            case R.id.tv_add_adr:
                ZeroZeroSevenUtils.SwitchActivity(NumberRicalCommitDingDanActivity.this, SelectAdrMannGerActivity.class, null, 2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2:
                try {
                    int position = Integer.parseInt(data.getStringExtra("position"));
                    initAdr(position);
                } catch (Exception e) {
                }

                break;
            default:
                break;

        }
    }
}
