package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RTalkInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.TopView;
import com.willy.ratingbar.ScaleRatingBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ErrandTalkReleaseActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    private String orderNo;
    private String name;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.rg_group)
    RadioGroup rg_group;
    String fuwupingjia = "";

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
        name = getIntent().getStringExtra("name");
        tv_name.setText(name);
        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_haihao:
                        fuwupingjia = "吐槽";
                        break;
                    case R.id.rb_manyi:
                        fuwupingjia = "满意";
                        break;
                    case R.id.rb_youxiu:
                        fuwupingjia = "超赞";
                        break;
                }
            }
        });
    }

    @Override
    protected void doMain() {
    }

    @Bind(R.id.sudu)
    ScaleRatingBar sudu;
    @Bind(R.id.taidu)
    ScaleRatingBar taidu;
    @Bind(R.id.wanzheng)
    ScaleRatingBar wanzheng;
    @Bind(R.id.et_wenzi)
    EditText et_wenzi;

    /**
     * 评价
     */
    public void Talk() {
        if (TextUtils.isEmpty(fuwupingjia)) {
            ToastUtils.showShort("请对本次跑腿做出服务评价");
            return;
        }
        if (sudu.getRating() == 0) {
            ToastUtils.showShort("请对本次跑腿做出服务评价");
            return;
        }
        if (taidu.getRating() == 0) {
            ToastUtils.showShort("请对本次跑腿做出服务评价");
            return;
        }
        if (wanzheng.getRating() == 0) {
            ToastUtils.showShort("请对本次跑腿做出服务评价");
            return;
        }
        RTalkInfo rTalkInfo = new RTalkInfo();
        rTalkInfo.setFunctionName("AddErrandOrderEvaluate");
        RTalkInfo.ParametersBean parametersBean = new RTalkInfo.ParametersBean();
        parametersBean.setUserId(userId);
        parametersBean.setUserPhone(userInfo.getPhone());
        parametersBean.setOrderNo(orderNo);
        parametersBean.setServiceEvaluation(fuwupingjia);//服务评价：吐槽，满意，超赞
        parametersBean.setSpeedStarCount(String.valueOf(sudu.getRating()));//跑腿速度星星数
        parametersBean.setServiceStarCount(String.valueOf(taidu.getRating()));//服务态度星星数
        parametersBean.setCompleteStarCount(String.valueOf(wanzheng.getRating()));//货物完整星星数
        if (!TextUtils.isEmpty(et_wenzi.getText().toString().trim())) {
            parametersBean.setRemark(et_wenzi.getText().toString().trim());//文字评价
        }
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
