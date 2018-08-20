package com.ffn.zerozeroseven.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.NumberPayAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.NumberRicalInfo;
import com.ffn.zerozeroseven.bean.ShouHuoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.AllAdrInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;
import java.util.List;

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
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    private ShouHuoInfo shouHuoInfo;
    private int position = 0;
    private NumberRicalInfo.RicalInfo ricalInfo;
    private NumberPayAdapter numberPayAdapter;

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
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        numberPayAdapter = new NumberPayAdapter(this);
        recycleview.setAdapter(numberPayAdapter);
    }

    @Override
    protected void doMain() {
        ricalInfo = (NumberRicalInfo.RicalInfo) getIntent().getSerializableExtra("ricalInfo");
        if (!TextUtils.isEmpty(getIntent().getStringExtra("money"))) {
            tv_paymoney.setText(getIntent().getStringExtra("money"));
            List<NumberRicalInfo.RicalInfo> ricalInfos = new ArrayList<>();
            NumberRicalInfo numberRicalInfo = BaseAppApplication.getInstance().getNumberRicalInfo();
            for (int i = 0; i < numberRicalInfo.getNumberRicalListInfo().size(); i++) {
                if (numberRicalInfo.getNumberRicalListInfo().get(i).isChecked()) {
                    NumberRicalInfo.RicalInfo rical = new NumberRicalInfo.RicalInfo();
                    rical.setId(numberRicalInfo.getNumberRicalListInfo().get(i).getId());
                    rical.setSpecId(numberRicalInfo.getNumberRicalListInfo().get(i).getSpecId());
                    rical.setCount(numberRicalInfo.getNumberRicalListInfo().get(i).getCount());
                    rical.setNeedsMoney(numberRicalInfo.getNumberRicalListInfo().get(i).getNeedsMoney());
                    rical.setYuYueMoney(numberRicalInfo.getNumberRicalListInfo().get(i).getYuYueMoney());
                    rical.setName(numberRicalInfo.getNumberRicalListInfo().get(i).getName());
                    rical.setImgUrl(numberRicalInfo.getNumberRicalListInfo().get(i).getImgUrl());
                    rical.setConfiguration(numberRicalInfo.getNumberRicalListInfo().get(i).getConfiguration());
                    rical.setType(numberRicalInfo.getNumberRicalListInfo().get(i).getType());
                    ricalInfos.add(rical);
                }
            }
            numberPayAdapter.addAll(ricalInfos);
        } else {
            tv_paymoney.setText(String.valueOf(ricalInfo.getYuYueMoney() * ricalInfo.getCount()));
//            NumberRicalInfo numberRicalInfo = new NumberRicalInfo();
            NumberRicalInfo.RicalInfo rical = new NumberRicalInfo.RicalInfo();
            List<NumberRicalInfo.RicalInfo> ricalInfos = new ArrayList<>();
            rical.setId(ricalInfo.getId());
            rical.setSpecId(ricalInfo.getSpecId());
            rical.setCount(ricalInfo.getCount());
            rical.setNeedsMoney(ricalInfo.getNeedsMoney());
            rical.setName(ricalInfo.getName());
            rical.setImgUrl(ricalInfo.getImgUrl());
            rical.setYuYueMoney(ricalInfo.getYuYueMoney());
            rical.setConfiguration(ricalInfo.getConfiguration());
            rical.setType(ricalInfo.getType());
            ricalInfos.add(rical);
//            numberRicalInfo.setNumberRicalListInfo(ricalInfos);
            numberPayAdapter.addAll(ricalInfos);
        }

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
                shouHuoInfo = JSON.parseObject(response, ShouHuoInfo.class);
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
                Bundle bundle = new Bundle();
                bundle.putSerializable("adrInfo", shouHuoInfo.getData().getAddresses().get(position));
                bundle.putString("money", tv_paymoney.getText().toString());
                bundle.putString("pay", getIntent().getStringExtra("pay"));
                bundle.putSerializable("ricalInfo", ricalInfo);
                ZeroZeroSevenUtils.SwitchActivity(NumberRicalCommitDingDanActivity.this, PayMoneyNewActivity.class, bundle);
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
                    position = Integer.parseInt(data.getStringExtra("position"));
                    initAdr(position);
                } catch (Exception e) {
                }

                break;
            default:
                break;

        }
    }
}
