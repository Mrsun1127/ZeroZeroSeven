package com.ffn.zerozeroseven.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.requsetbean.UpDateAdrInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TitleView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/12/2.
 */

public class UpDateAdrActivity extends BaseActivity {
    private EditText et_adr;
    private EditText et_name;
    private EditText et_phone;
    private TextView et_lou;
    private EditText et_men;
    private int upId;
    private int id;


    @Override
    protected int setLayout() {
        return R.layout.activity_addnewadr;
    }

    @Override
    protected void doMain() {
        et_name.setText(getIntent().getStringExtra("name"));
        et_adr.setText(getIntent().getStringExtra("adr"));
        et_phone.setText(getIntent().getStringExtra("phone"));
        et_lou.setText(getIntent().getStringExtra("dong"));
        et_men.setText(getIntent().getStringExtra("men"));
        upId = getIntent().getIntExtra("id",0);
    }

    @Override
    public void initView() {
        TitleView titleView=findViewById(R.id.titleview);
        titleView.setTopText("修改地址");
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
        et_adr = findViewById(R.id.et_adr);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_lou = findViewById(R.id.et_lou);
        et_men = findViewById(R.id.et_men);
        findViewById(R.id.ll_addLou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZeroZeroSevenUtils.SwitchActivity(UpDateAdrActivity.this, LouDongActivity.class, null, 0);
            }
        });
        findViewById(R.id.bt_sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ZeroZeroSevenUtils.isMobileNO(et_phone.getText().toString().trim())){
                    saveInfo();
                }else{
                    ToastUtils.showShort("请输入正确的手机号码");
                }
            }
        });

    }

    private void saveInfo() {
        showLoadProgress();
        UpDateAdrInfo upDateAdrInfo=new UpDateAdrInfo();
        upDateAdrInfo.setFunctionName("UpdateUserAddress");
        UpDateAdrInfo.ParametersBean parametersBean=new UpDateAdrInfo.ParametersBean();
        parametersBean.setId(upId);
        parametersBean.setContactSchool(et_adr.getText().toString().trim());
        parametersBean.setContactName(et_name.getText().toString().trim());
        parametersBean.setContactPhone(et_phone.getText().toString().trim());
//        parametersBean.setBuildingId(getIntent().getIntExtra("buildId",0));
        parametersBean.setContactBuilding(et_lou.getText().toString().trim());
        parametersBean.setContactDorm(et_men.getText().toString().trim());
        upDateAdrInfo.setParameters(parametersBean);
        httpPostJSON(upDateAdrInfo,true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                disLoadProgress();
                String code= JsonUtil.getFieldValue(response.body().string(),"code");
                if("0".equals(code)){
                    finish();
                }else{
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showShort("请将信息填写完整");
                        }
                    });
                }

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                String lou= data.getStringExtra("loudong");
                id = data.getIntExtra("id",0);
                et_lou.setText(lou);
                break;
            default:

                break;
        }
    }
}
