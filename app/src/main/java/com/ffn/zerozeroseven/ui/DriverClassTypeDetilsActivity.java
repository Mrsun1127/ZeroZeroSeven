package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.DriverClassInfo;
import com.ffn.zerozeroseven.bean.RDriverClassInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverClassTypeDetilsActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;

    @Override
    protected int setLayout() {
        return R.layout.activity_class_detils;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("班型详情");
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
        requestDate(getIntent().getStringExtra("classId"));
    }

    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_price)
    TextView tv_price;
    @Bind(R.id.tv_type)
    TextView tv_type;
    @Bind(R.id.tv_car)
    TextView tv_car;
    @Bind(R.id.tv_up_car_time)
    TextView tv_up_car_time;
    @Bind(R.id.tv_projectto)
    TextView tv_projectto;
    @Bind(R.id.tv_projectthree)
    TextView tv_projectthree;
    @Bind(R.id.tv_finish_time)
    TextView tv_finish_time;
    @Bind(R.id.tv_xingshi)
    TextView tv_xingshi;
    @Bind(R.id.tv_test_money)
    TextView tv_test_money;
    @Bind(R.id.tv_card_money)
    TextView tv_card_money;
    @Bind(R.id.tv_baoxian)
    TextView tv_baoxian;
    @Bind(R.id.tv_photo_money)
    TextView tv_photo_money;
    @Bind(R.id.tv_gongben)
    TextView tv_gongben;
    @Bind(R.id.tv_teach_money)
    TextView tv_teach_money;
    @Bind(R.id.tv_other_money)
    TextView tv_other_money;
    @Bind(R.id.tv_talk)
    TextView tv_talk;

    private void requestDate(String classId) {
        RDriverClassInfo rDriverClassInfo = new RDriverClassInfo();
        rDriverClassInfo.setFunctionName("QueryDrivingClass");
        RDriverClassInfo.ParametersBean parametersBean = new RDriverClassInfo.ParametersBean();
        parametersBean.setClassId(classId);
        rDriverClassInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(this);
        okGoUtils.httpPostJSON(rDriverClassInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                DriverClassInfo driverClassInfo = JSON.parseObject(response, DriverClassInfo.class);
                if (driverClassInfo.getCode() == 0) {
                    //carBrand : String - 车辆品牌
                    //licenseType : String - 驾照类型
                    //totalPrice : Decimal - 总价格
                    //boardingTime : String - 上车时间
                    //takeTime : String - 拿本时间
                    //shuttleType : String - 接送类型
                    //classesIntro : String - 课时介绍
                    //examFee : Decimal - 考试费
                    //nominalFee : Decimal - 工本费
                    //cardFee : Decimal - 制卡费
                    //premium : Decimal - 保险费
                    //photoFee : Decimal - 照相费
                    //trainingFee : Decimal - 培训费
                    //exclusive : String - 不包含费用
                    //servicePromise : String - 服务承诺
                    tv_price.setText(String.valueOf(driverClassInfo.getData().getDrivingClass().getTotalPrice()));
                    tv_name.setText(driverClassInfo.getData().getDrivingClass().getName());
                    tv_type.setText(driverClassInfo.getData().getDrivingClass().getLicenseType());
                    tv_car.setText(driverClassInfo.getData().getDrivingClass().getCarBrand());
                    tv_up_car_time.setText(driverClassInfo.getData().getDrivingClass().getBoardingTime());
                    tv_projectto.setText(driverClassInfo.getData().getDrivingClass().getClassesIntro());
                    tv_projectthree.setText(driverClassInfo.getData().getDrivingClass().getClassesIntro());
                    tv_finish_time.setText(driverClassInfo.getData().getDrivingClass().getTakeTime());
                    tv_xingshi.setText(driverClassInfo.getData().getDrivingClass().getShuttleType());
                    tv_test_money.setText("考试费：" + driverClassInfo.getData().getDrivingClass().getExamFee());
                    tv_card_money.setText("制卡费：" + driverClassInfo.getData().getDrivingClass().getCardFee());
                    tv_baoxian.setText("保险费：" + driverClassInfo.getData().getDrivingClass().getPremium());
                    tv_photo_money.setText("照相费：" + driverClassInfo.getData().getDrivingClass().getPhotoFee());
                    tv_gongben.setText("工本费：" + driverClassInfo.getData().getDrivingClass().getNominalFee());
                    tv_teach_money.setText("培训费：" + driverClassInfo.getData().getDrivingClass().getTrainingFee());
                    tv_talk.setText(driverClassInfo.getData().getDrivingClass().getServicePromise());
                    tv_other_money.setText("不包含：" + driverClassInfo.getData().getDrivingClass().getExclusive());
                }
            }
        });
    }

    @OnClick({R.id.bt_left})
    void setOnClicks(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.bt_left:
                bundle.putString("title", "报名");
                ZeroZeroSevenUtils.SwitchActivity(DriverClassTypeDetilsActivity.this, DriverCommitActivity.class, bundle);
                break;

        }
    }
}
