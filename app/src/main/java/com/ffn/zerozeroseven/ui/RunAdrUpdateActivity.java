package com.ffn.zerozeroseven.ui;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.RFaHuoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RAddRunAdrInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.view.ClearEditText;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RunAdrUpdateActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.ct_name)
    ClearEditText ct_name;
    @Bind(R.id.ct_phone)
    ClearEditText ct_phone;
    @Bind(R.id.ct_mainadr)
    ClearEditText ct_mainadr;
    @Bind(R.id.ct_number)
    ClearEditText ct_number;
    private String saveType;
    private RFaHuoInfo.DataBean.ListBean listBean;
    private String name;
    private String phone;
    private String mainAdr;
    private String jumpType;

    @Override
    protected int setLayout() {
        return R.layout.activity_run_adr_up;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        saveType = getIntent().getStringExtra("saveType");
        jumpType = getIntent().getStringExtra("jumpType");
        if ("update".equals(saveType)) {
            listBean = (RFaHuoInfo.DataBean.ListBean) getIntent().getSerializableExtra("info");
            ct_name.setText(listBean.getName());
            ct_phone.setText(listBean.getPhone());
            ct_mainadr.setText(listBean.getAddress());
        }
        topView.setTopText("编辑/新增地址");
        topView.setTvRightText("保存");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {
                name = ct_name.getText().toString().trim();
                phone = ct_phone.getText().toString().trim();
                mainAdr = ct_mainadr.getText().toString().trim();
                RAddRunAdrInfo rAddRunAdrInfo = new RAddRunAdrInfo();
                rAddRunAdrInfo.setFunctionName("AddOrUpdateErrandUserAddress");
                RAddRunAdrInfo.ParametersBean parametersBean = new RAddRunAdrInfo.ParametersBean();
                parametersBean.setUserId(userId);
                parametersBean.setPhone(phone);
                parametersBean.setName(name);
                parametersBean.setAddress(mainAdr);
                if ("update".equals(saveType)) {
                    parametersBean.setId(String.valueOf(listBean.getId()));
                }
                parametersBean.setType(getIntent().getStringExtra("jumpType").equals("s") ? "RECEIVE" : "SEND");
                rAddRunAdrInfo.setParameters(parametersBean);
                OkGoUtils okGoUtils = new OkGoUtils(RunAdrUpdateActivity.this);
                okGoUtils.httpPostJSON(rAddRunAdrInfo, true, true);
                okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
                    @Override
                    public void onSuccLoad(String response) {
                        ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                        if (errorCodeInfo.getCode() == 0) {
                            finish();
                        }
                    }
                });
            }

            @Override
            public void Back() {
                finish();
            }
        });

    }

    @Override
    protected void doMain() {

    }
}
