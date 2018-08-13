package com.ffn.zerozeroseven.ui;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RTalkInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ErrandTalkReleaseActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    private String orderNo;

    @Override
    protected int setLayout() {
        return R.layout.activity_errand_talkrelease;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("评价");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        orderNo = getIntent().getStringExtra("orderNo");
    }

    @Override
    protected void doMain() {

    }

    /**
     * 评价
     */
    public void Talk() {
        RTalkInfo rTalkInfo = new RTalkInfo();
        rTalkInfo.setFunctionName("AddErrandOrderEvaluate");
        RTalkInfo.ParametersBean parametersBean = new RTalkInfo.ParametersBean();
        parametersBean.setUserId(userId);
        parametersBean.setUserPhone(userInfo.getPhone());
        parametersBean.setOrderNo(orderNo);
        parametersBean.setServiceEvaluation("");//服务评价：吐槽，满意，超赞
        parametersBean.setSpeedStarCount("");//跑腿速度星星数
        parametersBean.setServiceStarCount("");//服务态度星星数
        parametersBean.setCompleteStarCount("");//货物完整星星数
        parametersBean.setRemark("");//文字评价
        rTalkInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(ErrandTalkReleaseActivity.this);
        okGoUtils.httpPostJSON(rTalkInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    ToastUtils.showShort("评价成功");
                    finish();
                }
            }
        });
    }
}
