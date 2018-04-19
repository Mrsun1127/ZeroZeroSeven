package com.ffn.zerozeroseven.ui;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.QiangDanOkInfo;
import com.ffn.zerozeroseven.bean.RunListRquestInfo;
import com.ffn.zerozeroseven.bean.requsetbean.QiangRunRequsetInfo;
import com.ffn.zerozeroseven.fragment.MainFragment;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TitleView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/22.
 */

public class RunDetilsActivity extends BaseActivity {
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_type)
    TextView tv_type;
    @Bind(R.id.tv_getadr)
    TextView tv_getadr;
    @Bind(R.id.tv_arradr)
    TextView tv_arradr;
    @Bind(R.id.tv_people)
    TextView tv_people;
    @Bind(R.id.tv_peoplephone)
    TextView tv_peoplephone;
    @Bind(R.id.tv_beizhu)
    TextView tv_beizhu;
    @Bind(R.id.tv_gongsi)
    TextView tv_gongsi;
    @Bind(R.id.tv_runphone)
    TextView tv_runphone;
    @Bind(R.id.tv_other)
    TextView tv_other;
    @Bind(R.id.tv_smallmoney)
    TextView tv_smallmoney;
    private String type;
    private int position;
    private RunListRquestInfo.DataBean.ListBean listBean;

    @Override
    protected int setLayout() {
        return R.layout.activity_run_detils;
    }

    @Override
    protected void doMain() {
        BaseAppApplication.getInstance().addActivity(this);
        position = getIntent().getIntExtra("posotion", 0);
        type = getIntent().getStringExtra("type");
        if (type.equals("main")) {
            showMain();
        } else {

        }
    }

    private void showMain() {
        listBean = MainFragment.mInstance.getRunlist(position);
        tv_phone.setText(listBean.getPublisherPhone());
        tv_time.setText(listBean.getCreateTime());
        tv_type.setText(listBean.getType());
        tv_getadr.setText(listBean.getPickupAddress());
        tv_arradr.setText(listBean.getDeliverAddress());
        tv_people.setText(listBean.getWeight());
        tv_peoplephone.setText(listBean.getPrice());
        tv_beizhu.setText(listBean.getPickupAddress());
        tv_runphone.setText(listBean.getPublisherPhone());
        tv_smallmoney.setText("2");


    }

    @OnClick({R.id.bt_sub})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_sub:
                qiangDan();
                break;

        }
    }

    private void qiangDan() {
        showLoadProgress();
        QiangRunRequsetInfo qiangRunRequsetInfo = new QiangRunRequsetInfo();
        qiangRunRequsetInfo.setFunctionName("GrabErrandOrder");
        QiangRunRequsetInfo.ParametersBean parametersBean = new QiangRunRequsetInfo.ParametersBean();
        parametersBean.setId(String.valueOf(listBean.getId()));
        qiangRunRequsetInfo.setParameters(parametersBean);
        httpPostJSON(qiangRunRequsetInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final QiangDanOkInfo qiangDanOkInfo = JSON.parseObject(response.body().string(), QiangDanOkInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        if (qiangDanOkInfo.getCode() == 0) {
                            ZeroZeroSevenUtils.showCustonPop(RunDetilsActivity.this, "抢单成功", tv_arradr);
                        } else {
                            ZeroZeroSevenUtils.showCustonPop(RunDetilsActivity.this, qiangDanOkInfo.getMessage(), tv_arradr);
                        }
                    }
                });

            }
        });
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        TitleView titleView = findViewById(R.id.titleview);
        titleView.setTopText("我来跑腿");
        titleView.setOnTitleListener(new TitleView.OnTitleClickListener() {
            @Override
            public void ivBack() {
                finish();
            }

            @Override
            public void ivDown() {

            }

            @Override
            public void ivMessAge() {

            }
        });
    }
}
