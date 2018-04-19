package com.ffn.zerozeroseven.ui;

import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.CuriousInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RenZhenInfo;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/12/5.
 */

public class KuaiDiYuanRenZhengActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_phone;
    private EditText et_shenfen;

    @Override
    protected int setLayout() {
        return R.layout.activity_kuaidiyuanrenzheng;
    }

    @Override
    protected void doMain() {

    }

    @Override
    public void initView() {
        BaseAppApplication.getInstance().addActivity(this);
        findViewById(R.id.rl_back).setOnClickListener(this);
        findViewById(R.id.bt_sub).setOnClickListener(this);
        et_phone = findViewById(R.id.et_phone);
        et_shenfen = findViewById(R.id.et_shenfen);
        et_phone.setText(userInfo.getPhone());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.bt_sub:
                String phone = et_phone.getText().toString().trim();
                String shenfen = et_shenfen.getText().toString().trim();
                Yanzheng(phone, shenfen);
                break;
        }
    }

    private void Yanzheng(String phone, String shenfen) {
        showLoadProgress();
        RenZhenInfo renZhenInfo = new RenZhenInfo();
        renZhenInfo.setFunctionName("ValidateCourier");
        RenZhenInfo.ParametersBean parametersBean = new RenZhenInfo.ParametersBean();
        parametersBean.setCourierNo(shenfen);
        parametersBean.setPhone(phone);
        renZhenInfo.setParameters(parametersBean);
        httpPostJSON(renZhenInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                disLoadProgress();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                disLoadProgress();
                final CuriousInfo info = JSON.parseObject(response.body().string(), CuriousInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (info.getCode() == 0) {
                            MrsunAppCacheUtils.get(KuaiDiYuanRenZhengActivity.this).put("curInfo", JSON.toJSONString(info));
                            ZeroZeroSevenUtils.SwitchActivity(KuaiDiYuanRenZhengActivity.this, CourierActivity.class, null);
                            finish();
                        } else {
                            ZeroZeroSevenUtils.showCustonPop(KuaiDiYuanRenZhengActivity.this, info.getMessage(), et_phone);
                        }
                    }
                });

            }
        });
    }
}
