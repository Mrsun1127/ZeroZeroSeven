package com.ffn.zerozeroseven.ui;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.requsetbean.BecomeInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.pop.BeZZSPopWindow;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/22.
 */

public class ToBeAGoodPeople extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_all;
    private BeZZSPopWindow popWindow;

    @Override
    protected int setLayout() {
        return R.layout.activity_tobezzs;
    }

    @Override
    protected void doMain() {
        final ImageView iv_back = findViewById(R.id.iv_back);
        RelativeLayout rl_become = findViewById(R.id.rl_become);
        ll_all = findViewById(R.id.ll_all);
        iv_back.setOnClickListener(this);
        rl_become.setOnClickListener(this);
        findViewById(R.id.rl_call).setOnClickListener(this);
        popWindow = new BeZZSPopWindow(ToBeAGoodPeople.this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_become:
                showPop();
                break;
            case R.id.rl_call:
                ZeroZeroSevenUtils.MakingCalls(ToBeAGoodPeople.this, "18888888888");
                break;
        }
    }

    private void showPop() {
        popWindow.showAtLocation(ll_all, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        lightOff();
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.alpha = 1.0f;
                getWindow().setAttributes(layoutParams);
            }
        });
        popWindow.setMlistener(new BeZZSPopWindow.OnButonClikListener() {
            @Override
            public void OnBtSub(String name, String phoneNumber, String school) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        popWindow.dismiss();
                        showLoadProgress();
                    }
                });
                BecomeInfo becomeInfo = new BecomeInfo();
                becomeInfo.setFunctionName("AddStoreApplication");
                BecomeInfo.ParametersBean parametersBean = new BecomeInfo.ParametersBean();
                parametersBean.setApplicantName(name);
                parametersBean.setApplicantPhone(phoneNumber);
                parametersBean.setApplicantSchool(school);
                becomeInfo.setParameters(parametersBean);
                httpPostJSON(becomeInfo, true);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        BaseAppApplication.mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                disLoadProgress();
                                ZeroZeroSevenUtils.showCustonPop(ToBeAGoodPeople.this, "服务器异常，请稍后再试", ll_all);
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        BaseAppApplication.mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                disLoadProgress();
                            }
                        });
                        String code = JsonUtil.getFieldValue(response.body().string(), "code");
                        if ("0".equals(code)) {
                            BaseAppApplication.mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ZeroZeroSevenUtils.showCustonPop(ToBeAGoodPeople.this, "您的申请已在审核中", ll_all);
                                }
                            });
                        } else {
                            BaseAppApplication.mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ZeroZeroSevenUtils.showCustonPop(ToBeAGoodPeople.this, "申请失败", ll_all);
                                }
                            });
                        }
                    }
                });
            }
        });

    }

}
