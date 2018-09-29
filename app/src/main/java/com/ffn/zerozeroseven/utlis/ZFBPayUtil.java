package com.ffn.zerozeroseven.utlis;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.NumberRicalInfo;
import com.ffn.zerozeroseven.bean.requsetbean.CancelOrderInfo;
import com.ffn.zerozeroseven.fragment.ErrandMineRunFragment;
import com.ffn.zerozeroseven.ui.AllDingDanActivity;
import com.ffn.zerozeroseven.ui.CommitSuccessActivity;
import com.ffn.zerozeroseven.ui.DriverClassTypeDetilsActivity;
import com.ffn.zerozeroseven.ui.DriverCommitActivity;
import com.ffn.zerozeroseven.ui.DriverDingDanListActivity;
import com.ffn.zerozeroseven.ui.DrivingDetilsActivity;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;
import com.ffn.zerozeroseven.ui.NumberRicalCommitDingDanActivity;
import com.ffn.zerozeroseven.ui.NumberRicalShopCarActivity;
import com.ffn.zerozeroseven.ui.PayMoneyActivity;
import com.ffn.zerozeroseven.ui.PayMoneyNewActivity;

public class ZFBPayUtil {
    private Context mContext;
    private String type;

    public ZFBPayUtil(Context context) {
        mContext = context;
    }

    // 商户PID
    public static final String PARTNER = "2088221705803208";
    // 商户收款账号
    public static final String SELLER = "chh6663132@163.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKAVtdAHnkBpb8oEBybaNtBW7OUQITFNa6NHD1WEThQE42zYenpZulm6qUOt+JOXD23i1iwlp0kSnrTVZ9H3csGE78akiPdUDVRIWm1XOzSihzRKqTvEH+EBEaxzKs2SmTu6vtgzPEbrqYc4RAj25ev1hez7RCRr1IJJUQQ5FN5ZAgMBAAECgYBuRsZaKgVP5dIGXcP3dbbwyhCisvvKlMSjU54ykNOgYsWwA2hLxGfky/syDjQAp70PO4XCH6YfZl5BwAim0UkRA3N/vcEoyHNgutYdsxv8TuC0VaXmTICFYkPjdwwx5uKRHcEIs6w2WPiz/qZd6dBnDV9GbZQcwEOjwzhLbNwgSQJBAMqz9I+155WIw3D26943GJYhE5gPBUZFgr0j31SxThhh+SVCv76pKwTG/9FOvf68iJts1AxOZa1XoiUXuXaTzhcCQQDKLRrIAcK37YUXRITm70Sk7e3aon34t3BLBWop5ZstY67Ga5SZ4Zx04R39zXLjfDqjm8ILmqzVpNRmEbg9NG0PAkEAmUiiPPaq7KpiWQ9waDcz0XurzIY3T9+KsSrqXKpDyy5zcJjcVPqqPE+b7hTkmjjJ+PbAF5pFS2MEi5Y4OpIk+wJBAJjwFdpGuq24F33Kb17ikOLSuaMyWGjVGzUlG3ImJoTna0beCsN9T4V65d0glVBQoWsyYE+26heTYLj1npFuHzECQQCZqF7LICEPVV6eVqCfNpzpi3YP4yalKyWKfWv8QdCgBCVufxQp4eAu69TE5CsERje5fZSsRhRq/NgNgq7Ki26V";

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Bundle bundle = new Bundle();
                        // 零食购物车 “carpay” 零食直接购买 “zhijie” 数码付尾款 “numberweikuan” 数码购物车 “numbercar” 数码直接预约或者直接购买 “numberzhijie”
                        String pay = BaseAppApplication.clearType;
                        if ("carpay".equals(pay)) {
                            PayMoneyActivity.mInstance.get().finish();
                            CarShopInfo carShopInfo = BaseAppApplication.getInstance().getCarShopInfo();
                            carShopInfo.getShopInfos().clear();
                            BaseAppApplication.getInstance().setCarShopInfo(carShopInfo);
                            SharePrefUtils.saveObject(mContext, "carShopInfo", BaseAppApplication.getInstance().getCarShopInfo());
                            gotoVp(0);
                        } else if ("food".equals(pay)) {
                            PayMoneyActivity.mInstance.get().finish();
                            CarShopInfo carShopInfo = BaseAppApplication.getInstance().getCarShopInfo();
                            carShopInfo.getShopInfos().clear();
                            BaseAppApplication.getInstance().setCarShopInfo(carShopInfo);
                            SharePrefUtils.saveObject(mContext, "carShopInfo", BaseAppApplication.getInstance().getCarShopInfo());
                            gotoVp(0);
                        } else if ("leasezhijie".equals(pay)) {
                            PayMoneyActivity.mInstance.get().finish();
                            gotoVp(2);
                        } else if ("lease".equals(pay)) {
                            PayMoneyActivity.mInstance.get().finish();
                            CarShopInfo carShopInfo = BaseAppApplication.getInstance().getLeasecarShopInfo();
                            carShopInfo.getShopInfos().clear();
                            BaseAppApplication.getInstance().setLeasecarShopInfo(carShopInfo);
                            SharePrefUtils.saveObject(mContext, "leasecarShopInfo", BaseAppApplication.getInstance().getLeasecarShopInfo());
                            gotoVp(2);
                        } else if ("numbercar".equals(pay)) {
                            PayMoneyNewActivity.mInstance.get().finish();
                            NumberRicalCommitDingDanActivity.mInstance.get().finish();
                            NumberRicalShopCarActivity.mInstance.get().finish();
                            NumberRicalInfo numberRicalInfo = BaseAppApplication.getInstance().getNumberRicalInfo();
                            for (int i = 0; i < numberRicalInfo.getNumberRicalListInfo().size(); i++) {
                                if (numberRicalInfo.getNumberRicalListInfo().get(i).isChecked()) {
                                    numberRicalInfo.getNumberRicalListInfo().remove(i);
                                }
                            }
                            BaseAppApplication.getInstance().setNumberRicalInfo(numberRicalInfo);
                            SharePrefUtils.saveObject(mContext, "numberRicalInfo", numberRicalInfo);
                            gotoVp(1);
                        } else if ("zhijie".equals(pay)) {
                            PayMoneyActivity.mInstance.get().finish();
                            gotoVp(0);
                        } else if ("numberweikuan".equals(pay)) {
                            PayMoneyActivity.mInstance.get().finish();
                            ZeroZeroSevenUtils.SwitchActivity(mContext, CommitSuccessActivity.class, bundle);
                        } else if ("numberzhijie".equals(pay)) {
                            PayMoneyNewActivity.mInstance.get().finish();
                            NumberRicalCommitDingDanActivity.mInstance.get().finish();
                            gotoVp(1);
                        } else if ("run".equals(pay)) {
                            PayMoneyNewActivity.mInstance.get().finish();
                            bundle.putString("info", "请等待跑腿人员接单，如五分钟内无人接单将会自动退款");
                            ZeroZeroSevenUtils.SwitchActivity(mContext, CommitSuccessActivity.class, bundle);

                        } else if ("renzheng".equals(pay)) {
                            PayMoneyNewActivity.mInstance.get().finish();
                            ErrandAuitActivity.mInstance.get().goVp(2);
                            ErrandMineRunFragment.mInstance.get().requestData();
                            bundle.putString("info", "请耐心等待工作人员审核，请留意App通知");
                            ZeroZeroSevenUtils.SwitchActivity(mContext, CommitSuccessActivity.class, bundle);

                        } else if ("driver".equals(pay)) {
                            PayMoneyNewActivity.mInstance.get().finish();
                            DrivingDetilsActivity.mInstance.get().finish();
                            DriverClassTypeDetilsActivity.mInstacne.get().finish();
                            DriverCommitActivity.mInstance.get().finish();
                            ZeroZeroSevenUtils.SwitchActivity(mContext, DriverDingDanListActivity.class);
                        }
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(mContext, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                            cancelPay();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

    private void gotoVp(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        ZeroZeroSevenUtils.SwitchActivity(mContext, AllDingDanActivity.class, bundle);
    }

    private void cancelPay() {
        CancelOrderInfo cancelOrderInfo = new CancelOrderInfo();
        cancelOrderInfo.setFunctionName("CancelOrderPay");
        OkGoUtils okGoUtils = new OkGoUtils(mContext);
        okGoUtils.httpPostJSON(cancelOrderInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
            }
        });
    }


    public void pay(String body, String payType) {
        type = payType;
        final String payInfo = body;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask((Activity) mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * 创建订单信息
     *
     * @param subject 商品名称
     * @param body    商品详情
     * @param price   商品金额
     * @param tradeno 商户网站唯一订单号
     * @return
     */
    private String getOrderInfo(String subject, String body, String price, String tradeno, String notify_url) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + tradeno + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + notify_url + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }


    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

}
