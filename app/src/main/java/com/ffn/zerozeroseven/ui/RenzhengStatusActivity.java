package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.QueserRunner;
import com.ffn.zerozeroseven.bean.RunnerInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RenzhengStatusActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;

    @Override
    protected int setLayout() {
        return R.layout.activity_status_renzheng;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("跑腿认证");
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

    @Bind(R.id.iv_status)
    ImageView iv_status;
    @Bind(R.id.tv_school)
    TextView tv_school;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_id)
    TextView tv_id;
    @Bind(R.id.tv_sex)
    TextView tv_sex;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_money)
    TextView tv_money;
    @Bind(R.id.bt_sub)
    Button bt_sub;

    @Override
    protected void doMain() {
        QueserRunner queserRunner = new QueserRunner();
        queserRunner.setFunctionName("QueryErrandUser");
        QueserRunner.ParametersBean parametersBean = new QueserRunner.ParametersBean();
        parametersBean.setUserPhone(userInfo.getPhone());
        queserRunner.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(this);
        okGoUtils.httpPostJSON(queserRunner, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                RunnerInfo runnerInfo = JSON.parseObject(response, RunnerInfo.class);
                if (runnerInfo.getCode() == 0) {
                    if (runnerInfo.getData() != null) {
                        tv_school.setText(runnerInfo.getData().getSchoolName());
                        tv_name.setText(runnerInfo.getData().getRealName());
                        tv_id.setText(runnerInfo.getData().getIdcard());
                        tv_phone.setText(runnerInfo.getData().getPhone());
//                        tv_money.setText(runnerInfo.getData().);
                        tv_sex.setText(runnerInfo.getData().getSex() == 0 ? "女" : "男");
                        //审核状态：-2=取消资格（无法退款）-1=审核未通过，0=未审核，1=已审核
                        switch (runnerInfo.getData().getCheckStatus()) {
                            case -2:
                                Glide.with(RenzhengStatusActivity.this).load(R.drawable.run_zhuxiao).into(iv_status);
                                bt_sub.setVisibility(View.GONE);
                                break;
                            case -1:
                                bt_sub.setText("申请退款");
                                break;
                            case 0:
                                bt_sub.setText("申请退款");
                                Glide.with(RenzhengStatusActivity.this).load(R.drawable.run_shenhe).into(iv_status);
                                break;
                            case 1:
                                bt_sub.setText("申请退款");
                                Glide.with(RenzhengStatusActivity.this).load(runnerInfo.getData().getAvatar()).into(iv_status);
                                break;
                        }
                        switch (runnerInfo.getData().getPayStatus()) {//支付状态：-2=已退款，-1=支付失败，0=未支付，1=支付成功
                            case -2:
                                Glide.with(RenzhengStatusActivity.this).load(R.drawable.run_tuikuan).into(iv_status);
                                break;
                        }
                    }
                } else {
                    ToastUtils.showShort(runnerInfo.getMessage());
                }
            }
        });
    }
    @OnClick({R.id.bt_sub})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_sub:

            break;

        }
    }
}
