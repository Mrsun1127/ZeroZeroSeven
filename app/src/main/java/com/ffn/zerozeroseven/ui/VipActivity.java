package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.ChongZhiInfo;
import com.ffn.zerozeroseven.bean.CommitDingDanInfo;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.ReFreShUserInfo;
import com.ffn.zerozeroseven.bean.requsetbean.CallNewDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ChanpinInfo;
import com.ffn.zerozeroseven.bean.requsetbean.HuoChanpinInfo;
import com.ffn.zerozeroseven.bean.requsetbean.TixianInfo;
import com.ffn.zerozeroseven.bean.requsetbean.VipUserInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZFBPayUtil;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2018/2/2.
 */

public class VipActivity extends BaseActivity {
    @Bind(R.id.iv_icon)
    CircleImageView iv_icon;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_money)
    TextView tv_money;
    @Bind(R.id.tv_paymoney)
    TextView tv_paymoney;
    @Bind(R.id.tv_getmoney)
    TextView tv_getmoney;
    @Bind(R.id.rl_chongzhi)
    RelativeLayout rl_chongzhi;
    @Bind(R.id.rl_tixian)
    RelativeLayout rl_tixian;
    @Bind(R.id.rl_pop)
    RelativeLayout rl_pop;
    @Bind(R.id.et_money)
    EditText et_money;
    @Bind(R.id.line1)
    View line1;
    ZFBPayUtil mZFbutils;
    private ChanpinInfo chanpinInfo;
    @Bind(R.id.iv_vip)
    ImageView iv_vip;
    @Bind(R.id.tv_tixian)
    TextView tv_tixian;
    private OkGoUtils okGoUtils;

    @Override
    protected int setLayout() {
        return R.layout.activity_vip;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void bindData() {
//        Glide.with(this)
//                .load(userInfo.getData().getAvatar())
//                .into(iv_icon);
//        tv_phone.setText(userInfo.getData().getPhone());
//        tv_money.setText("余额：" + userInfo.getData().getBalance());
//        tv_paymoney.setText(userInfo.getData().getBalance() + "元");
//        tv_getmoney.setText(userInfo.getData().getIncome() + "元");
//        if (userInfo.getData().getIsMember() == 1) {
//            iv_vip.setVisibility(View.VISIBLE);
//        }
        refreshUserInfo();
    }

    @Override
    protected void doMain() {
        api = WXAPIFactory.createWXAPI(this, "wx189141e4085fa0d1", false);
        api.registerApp("wx189141e4085fa0d1");

        isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        mZFbutils = new ZFBPayUtil(this);
        requestChanpin();
    }

    @Bind(R.id.rl_one)
    RelativeLayout rl_one;
    @Bind(R.id.rl_two)
    RelativeLayout rl_two;

    @Bind(R.id.tv_level2)
    TextView tv_level2;
    @Bind(R.id.tv_level1)
    TextView tv_level1;
    @Bind(R.id.tv_remark2)
    TextView tv_remark2;
    @Bind(R.id.tv_remark1)
    TextView tv_remark1;
    @Bind(R.id.tv_name1)
    TextView tv_name1;
    @Bind(R.id.tv_name2)
    TextView tv_name2;

    private void requestChanpin() {
        HuoChanpinInfo huoChanpinInfo = new HuoChanpinInfo();
        huoChanpinInfo.setFunctionName("ListUserProduct");
        HuoChanpinInfo.ParametersBean parametersBean = new HuoChanpinInfo.ParametersBean();
        parametersBean.setProductType("VIP");
        huoChanpinInfo.setParameters(parametersBean);
        okGoUtils = new OkGoUtils(VipActivity.this);
        okGoUtils.httpPostJSON(huoChanpinInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                chanpinInfo = JSON.parseObject(response, ChanpinInfo.class);
                tv_name2.post(new Runnable() {
                    @Override
                    public void run() {
                        if (chanpinInfo.getCode() == 0) {
                            tv_level1.setText(chanpinInfo.getData().getProducts().get(0).getPrice() + "元");
                            tv_level2.setText(chanpinInfo.getData().getProducts().get(1).getPrice() + "元");
                            tv_remark1.setText(chanpinInfo.getData().getProducts().get(0).getRemark());
                            tv_remark2.setText(chanpinInfo.getData().getProducts().get(1).getRemark());
                            tv_name1.setText(chanpinInfo.getData().getProducts().get(0).getName());
                            tv_name2.setText(chanpinInfo.getData().getProducts().get(0).getName());
                        } else {
                            rl_one.setVisibility(View.GONE);
                            rl_two.setVisibility(View.GONE);
                            ToastUtils.showShort(chanpinInfo.getMessage());
                        }
                    }
                });
            }
        });


    }

    int level = 0;
    int type = 0;
    int payType = 0;
    @Bind(R.id.et_tixian)
    EditText et_tixian;
    @Bind(R.id.rl_tixian1)
    RelativeLayout rl_tixian1;

    @OnClick({R.id.tv_cancel, R.id.tv_sub, R.id.rl_back, R.id.rl_chongzhi, R.id.rl_tixian, R.id.bt_level1, R.id.bt_level2, R.id.rl_close, R.id.ll_zfb, R.id.ll_wechat, R.id.tv_right, R.id.tv_getmoney})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.tv_right:
                ZeroZeroSevenUtils.SwitchActivity(VipActivity.this, ShouyiActivity.class, null);
                break;
            case R.id.tv_getmoney:
                ZeroZeroSevenUtils.SwitchActivity(VipActivity.this, ShouyiActivity.class, null);
                break;
            case R.id.tv_cancel:
                rl_tixian1.setVisibility(View.GONE);
                break;
            case R.id.tv_sub:
                if (!TextUtils.isEmpty(et_tixian.getText().toString().trim())) {
                    rl_tixian1.setVisibility(View.GONE);
                    Tixian();
                } else {
                    ToastUtils.showShort("提现金额不能为空");
                }

                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_chongzhi:
                ZeroZeroSevenUtils.showCustonPop(VipActivity.this, "该功能暂未开放", tv_level1);

//                line1.setVisibility(View.VISIBLE);
//                et_money.setVisibility(View.VISIBLE);
//                rl_pop.setVisibility(View.VISIBLE);
//                type = 1;
                break;
            case R.id.rl_tixian:
                if (!TextUtils.isEmpty(SharePrefUtils.getString(VipActivity.this, "zfb", ""))) {
                    tv_tixian.setText("提现到支付宝(" + SharePrefUtils.getString(VipActivity.this, "zfb", "") + ")");
                    rl_tixian1.setVisibility(View.VISIBLE);
                } else {
                    ZeroZeroSevenUtils.showCustonPopToActivity(VipActivity.this, "请先绑定支付宝", iv_icon, BindZFbPayActivity.class);
                }
                break;
            case R.id.bt_level1:
                et_money.setVisibility(View.GONE);
                line1.setVisibility(View.GONE);
                rl_pop.setVisibility(View.VISIBLE);
                level = 1;
                type = 2;
                break;
            case R.id.bt_level2:
                et_money.setVisibility(View.GONE);
                line1.setVisibility(View.GONE);
                rl_pop.setVisibility(View.VISIBLE);
                level = 2;
                type = 2;
                break;
            case R.id.rl_close:
                rl_pop.setVisibility(View.GONE);
                break;
            case R.id.ll_zfb:
                payType = 0;
                rl_pop.setVisibility(View.GONE);
                xufei();
                break;
            case R.id.ll_wechat:
                payType = 1;
                rl_pop.setVisibility(View.GONE);
                xufei();
                break;
        }
    }

    private void Tixian() {
        TixianInfo tixianInfo = new TixianInfo();
        tixianInfo.setFunctionName("AlipayWithdrawalToAccount");
        TixianInfo.ParametersBean parametersBean = new TixianInfo.ParametersBean();
        parametersBean.setAmount(et_tixian.getText().toString().trim());
        tixianInfo.setParameters(parametersBean);
        okGoUtils.httpPostJSON(tixianInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                tv_name2.post(new Runnable() {
                    @Override
                    public void run() {
                        if (errorCodeInfo.getCode() == 0) {
                            ToastUtils.showShort("提现成功");
                            refreshUserInfo();
                        } else {
                            ToastUtils.showShort(errorCodeInfo.getMessage());
                        }
                    }
                });
            }
        });
    }

    public void xufei() {
        switch (type) {
            case 1://充值
                String money = et_money.getText().toString().trim();
                if (!TextUtils.isEmpty(money)) {
                    chongZhi(payType);
                } else {
                    ToastUtils.showShort("请输入金额");
                }
                break;
            case 2://续费
                payMoney(level);
                break;
        }

    }

    private void chongZhi(int payType) {
        if (payType == 1) {//wechat
            if (isPaySupported) {
                wechatPay();
            } else {
                ToastUtils.showShort("请安装微信");
            }
        } else {//zfb
            zfbPay();
        }
    }

    private void zfbPay() {
        CallNewDingDanInfo callNewDingDanInfo = new CallNewDingDanInfo();
        callNewDingDanInfo.setFunctionName("PayRechargeOrder");
        CallNewDingDanInfo.ParametersBean parametersBean = new CallNewDingDanInfo.ParametersBean();
        parametersBean.setRechargePrice((Integer.parseInt(et_money.getText().toString().trim()) * 100) + "");
        parametersBean.setPayment("AliPay");
        parametersBean.setTradeType("APP");
        callNewDingDanInfo.setParameters(parametersBean);
        okGoUtils.httpPostJSON(callNewDingDanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final CommitDingDanInfo commitDingDanInfo = JSON.parseObject(response, CommitDingDanInfo.class);
               tv_name2.post(new Runnable() {
                   @Override
                   public void run() {
                       if (commitDingDanInfo.getCode() == 0) {
                           mZFbutils.pay(commitDingDanInfo.getData().getBody(), "");
                       } else {
                           ZeroZeroSevenUtils.showCustonPop(VipActivity.this, commitDingDanInfo.getMessage(), et_money);
                       }
                   }
               });
            }
        });
    }

    private boolean isPaySupported;//判断是否支持微信支付
    private static IWXAPI api;

    private void wechatPay() {
        CallNewDingDanInfo callNewDingDanInfo = new CallNewDingDanInfo();
        callNewDingDanInfo.setFunctionName("PayRechargeOrder");
        CallNewDingDanInfo.ParametersBean parametersBean = new CallNewDingDanInfo.ParametersBean();
        parametersBean.setRechargePrice((Integer.parseInt(et_money.getText().toString().trim()) * 100) + "");
        parametersBean.setPayment("WechatPay");
        parametersBean.setTradeType("APP");
        callNewDingDanInfo.setParameters(parametersBean);
        okGoUtils.httpPostJSON(callNewDingDanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final ChongZhiInfo chongZhiInfo = JSON.parseObject(response, ChongZhiInfo.class);
                tv_name2.post(new Runnable() {
                    @Override
                    public void run() {
                        if (chongZhiInfo.getCode() == 0) {
                            PayReq req = new PayReq();
                            req.appId = chongZhiInfo.getData().getAppid();
                            req.partnerId = chongZhiInfo.getData().getPartnerid();
                            req.prepayId = chongZhiInfo.getData().getPrepayid();
                            req.nonceStr = chongZhiInfo.getData().getNoncestr();
                            req.timeStamp = chongZhiInfo.getData().getTimestamp();
                            req.packageValue = "Sign=WXPay";
                            req.sign = chongZhiInfo.getData().getSign();
                            api.sendReq(req);
                        } else {
                            ZeroZeroSevenUtils.showCustonPop(VipActivity.this, chongZhiInfo.getMessage(), et_money);
                        }
                    }
                });
            }
        });
    }

    private void payMoney(int level) {
        if (level == 1) {
            if (payType == 1) {//wechat
                WechatpayVip(0);
            } else {//zfb
                AliPayVip(0);
            }
        } else {
            if (payType == 1) {//wechat
                WechatpayVip(1);
            } else {//zfb
                AliPayVip(1);
            }
        }
    }

    private void AliPayVip(int i) {
        CallNewDingDanInfo callNewDingDanInfo = new CallNewDingDanInfo();
        callNewDingDanInfo.setFunctionName("PayMemberOrder");
        CallNewDingDanInfo.ParametersBean parametersBean = new CallNewDingDanInfo.ParametersBean();
        if (i == 0) {
            parametersBean.setProductId(chanpinInfo.getData().getProducts().get(0).getId() + "");
        } else {
            parametersBean.setProductId(chanpinInfo.getData().getProducts().get(1).getId() + "");
        }
        parametersBean.setPayment("AliPay");
        parametersBean.setTradeType("APP");
        callNewDingDanInfo.setParameters(parametersBean);
        okGoUtils.httpPostJSON(callNewDingDanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final CommitDingDanInfo commitDingDanInfo = JSON.parseObject(response, CommitDingDanInfo.class);
               tv_name2.post(new Runnable() {
                   @Override
                   public void run() {
                       if (commitDingDanInfo.getCode() == 0) {
                           mZFbutils.pay(commitDingDanInfo.getData().getBody(), "");
                       } else {
                           ZeroZeroSevenUtils.showCustonPop(VipActivity.this, commitDingDanInfo.getMessage(), et_money);
                       }
                   }
               });
            }
        });
    }

    private void WechatpayVip(int i) {
        CallNewDingDanInfo callNewDingDanInfo = new CallNewDingDanInfo();
        callNewDingDanInfo.setFunctionName("PayMemberOrder");
        CallNewDingDanInfo.ParametersBean parametersBean = new CallNewDingDanInfo.ParametersBean();
        if (i == 0) {
            parametersBean.setProductId(chanpinInfo.getData().getProducts().get(0).getId() + "");
        } else {
            parametersBean.setProductId(chanpinInfo.getData().getProducts().get(1).getId() + "");
        }
        parametersBean.setPayment("WechatPay");
        parametersBean.setTradeType("APP");
        callNewDingDanInfo.setParameters(parametersBean);
        okGoUtils.httpPostJSON(callNewDingDanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final ChongZhiInfo chongZhiInfo = JSON.parseObject(response, ChongZhiInfo.class);
               tv_name2.post(new Runnable() {
                   @Override
                   public void run() {
                       if (chongZhiInfo.getCode() == 0) {
                           PayReq req = new PayReq();
                           req.appId = chongZhiInfo.getData().getAppid();
                           req.partnerId = chongZhiInfo.getData().getPartnerid();
                           req.prepayId = chongZhiInfo.getData().getPrepayid();
                           req.nonceStr = chongZhiInfo.getData().getNoncestr();
                           req.timeStamp = chongZhiInfo.getData().getTimestamp();
                           req.packageValue = "Sign=WXPay";
                           req.sign = chongZhiInfo.getData().getSign();
                           api.sendReq(req);
                       } else {
                           ToastUtils.showShort(chongZhiInfo.getMessage());
                       }
                   }
               });
            }
        });


    }


    @Override
    protected void onRestart() {
        super.onRestart();
        refreshUserInfo();
    }

    public void refreshUserInfo() {
        VipUserInfo vipUserInfo = new VipUserInfo();
        vipUserInfo.setFunctionName("QueryAppUser");
        VipUserInfo.ParametersBean parametersBean = new VipUserInfo.ParametersBean();
        vipUserInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(VipActivity.this);
        okGoUtils.httpPostJSON(vipUserInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final ReFreShUserInfo reFreShUserInfo = JSON.parseObject(response, ReFreShUserInfo.class);
                tv_phone.post(new Runnable() {
                    @Override
                    public void run() {
                        if (reFreShUserInfo.getCode() == 0) {
                            ReFreShUserInfo.DataBean.UserBean user = reFreShUserInfo.getData().getUser();
                            if (!TextUtils.isEmpty(user.getAvatar())) {
                                Glide.with(VipActivity.this)
                                        .load(user.getAvatar())
                                        .into(iv_icon);
                            }
                            tv_phone.setText(user.getPhone());
                            tv_money.setText("余额:" + user.getBalance() + "元");
                            tv_paymoney.setText(user.getBalance() + "元");
                            tv_getmoney.setText(user.getIncome() + "元");
                            if (user.getIsMember() == 1) {
                                iv_vip.setVisibility(View.VISIBLE);
                            } else {
                                iv_vip.setVisibility(View.GONE);
                            }
                        } else {
                            finish();
                        }
                    }
                });

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        api.unregisterApp();
        api = null;

    }
}
