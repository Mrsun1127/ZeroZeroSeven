package com.ffn.zerozeroseven.ui;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.ErrandTuiKuan;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RunnerTuiKuanActivity extends BaseActivity {
    @Bind(R.id.tv_money)
    TextView tv_money;

    @Override
    protected int setLayout() {
        return R.layout.activity_runner_tuikuan;
    }

    @Override
    public void initView() {

        ButterKnife.bind(this);
        tv_money.setText(getIntent().getStringExtra("money"));
    }

    @OnClick({R.id.bt_sub})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_sub:
                final ConfirmDialog confirmDialog = new ConfirmDialog(RunnerTuiKuanActivity.this);
                confirmDialog.setTitles("提示");
                confirmDialog.setMessages("确定要退款？");
                confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        confirmDialog.dismiss();
                        tuiKuan();
                    }

                    @Override
                    public void doCancel() {
                        confirmDialog.dismiss();
                    }
                });
                break;

        }
    }

    private void tuiKuan() {
        final ErrandTuiKuan errandTuiKuan = new ErrandTuiKuan();
        errandTuiKuan.setFunctionName("ErrandUserCheckRefund");
        ErrandTuiKuan.ParametersBean parametersBean = new ErrandTuiKuan.ParametersBean();
        parametersBean.setUserId(userId);
        errandTuiKuan.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(RunnerTuiKuanActivity.this);
        okGoUtils.httpPostJSON(errandTuiKuan, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    ToastUtils.showShort("退款申请成功,后台工作人员正在审核");
                    RenzhengStatusActivity.mInstance.get().init();
                    finish();
                } else {
                    ToastUtils.showShort(errorCodeInfo.getMessage());
                }
            }
        });
    }

    @Override
    protected void doMain() {

    }
}
