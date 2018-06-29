package com.ffn.zerozeroseven.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.InteralAdapter;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseFullActivity;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.RgRefreshStatus;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.JiangChiInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.bean.WinnerDanmu;
import com.ffn.zerozeroseven.bean.ZhongLeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.InteraglSignInfo;
import com.ffn.zerozeroseven.bean.requsetbean.NaJiangInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ShareUserInfo;
import com.ffn.zerozeroseven.bean.requsetbean.TongyongInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ZhongMaInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.GridSpacingItemDecoration;
import com.ffn.zerozeroseven.view.TopView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

public class IntegralDrawActivity extends BaseFullActivity implements OnRefreshListener {
    @Bind(R.id.view_bot)
    View view_bot;
    @Bind(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    @Bind(R.id.recyclerView_with_recyclerView_in_coordinatorLayout)
    RecyclerView recycleview;
    private InteralAdapter adapter;
    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.rl_pop)
    RelativeLayout rl_pop;
    @Bind(R.id.rl_zhong)
    RelativeLayout rl_zhong;
    @Bind(R.id.ib_close)
    ImageButton ib_close;
    @Bind(R.id.bt_share)
    Button bt_share;
    @Bind(R.id.tv_one)
    TextView tv_one;
    @Bind(R.id.tv_two)
    TextView tv_two;
    @Bind(R.id.tv_three)
    TextView tv_three;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.et_phone)
    EditText et_phone;
    @Bind(R.id.et_adr)
    EditText et_adr;
    @Bind(R.id.tv_pao)
    TextView tv_pao;
    private ZhongLeInfo zhongLeInfo;


    @Override
    protected int setLayout() {
        return R.layout.activity_integraldraw;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("积分抽奖");
        topView.setTvRightText("我的中奖");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {
                ZeroZeroSevenUtils.SwitchActivity(IntegralDrawActivity.this, MySignGoodActivity.class);
            }

            @Override
            public void Back() {
                finish();
            }
        });
        refreshlayout.setOnRefreshListener(this);
        recycleview.setLayoutManager(new GridLayoutManager(this, 2));
        recycleview.addItemDecoration(new GridSpacingItemDecoration(2, 2, false));
        adapter = new InteralAdapter(this);
        recycleview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                try {
                    bundle.putInt("prizeId", adapter.getItem(position).getId());
                    bundle.putInt("issuePrizeId", adapter.getItem(position).getIssuePrizeId());
                } catch (Exception e) {
                }
                ZeroZeroSevenUtils.SwitchActivity(IntegralDrawActivity.this, ProductDetilsActivity.class, bundle);
            }
        });
    }

    int jump = 0;

    @OnClick({R.id.bt_sign, R.id.bt_bestnew, R.id.ib_close, R.id.bt_share, R.id.bt_sub, R.id.rl_close})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.rl_close:
                rl_zhong.setVisibility(View.GONE);
                break;
            case R.id.bt_sub:
                String name = et_name.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                String adr = et_adr.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    if (!TextUtils.isEmpty(phone)) {
                        if (!TextUtils.isEmpty(adr)) {
                            if(ZeroZeroSevenUtils.isMobileNO(phone)){
                                lingJiangLa(name, phone, adr);
                            }else{
                                ToastUtils.showShort("请输入正确的手机号码");
                            }
                        } else {
                            ToastUtils.showShort("请填写地址");
                        }
                    } else {
                        ToastUtils.showShort("请填写手机号码");
                    }
                } else {
                    ToastUtils.showShort("请填写姓名");
                }
                break;
            case R.id.bt_sign:
                sign();
                break;
            case R.id.bt_bestnew:
                ZeroZeroSevenUtils.SwitchActivity(IntegralDrawActivity.this, BestNewActivity.class);
                break;
            case R.id.ib_close:
                rl_pop.setVisibility(View.GONE);
                break;
            case R.id.bt_share:
                if (jump == 0) {
                    BaseAppApplication.mainHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rl_pop.setVisibility(View.VISIBLE);
                            tv_one.setText("分享成功");
                            tv_two.setText("分享获得3积分");
                            tv_three.setText("今天积分已领完，明天再来");
                            bt_share.setText("去抽奖");
                            jump = 1;
                        }
                    }, 5000);
                    rl_pop.setVisibility(View.GONE);
                    showShare(IntegralDrawActivity.this);
                    shareSuccess();
                } else {
                    rl_pop.setVisibility(View.GONE);
                }
                break;

        }
    }

    private void lingJiangLa(String name, String phone, String adr) {
        NaJiangInfo naJiangInfo = new NaJiangInfo();
        naJiangInfo.setFunctionName("AddPrizeAwardAddress");
        NaJiangInfo.ParametersBean parametersBean = new NaJiangInfo.ParametersBean();
        parametersBean.setContactAddress(adr);
        parametersBean.setContactName(name);
        parametersBean.setContactPhone(phone);
        parametersBean.setIssuePrizeId(zhongLeInfo.getData().getPointPrizeWinners().getIssuePrizeId());
        parametersBean.setUserPhone(userInfo.getPhone());
        naJiangInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(IntegralDrawActivity.this);
        okGoUtils.httpPostJSON(naJiangInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                if (JsonUtil.getFieldValue(response, "code").equals("0")) {
                    rl_zhong.setVisibility(View.GONE);
                    ToastUtils.showShort("后台将尽快安排配送");
                }
            }
        });
    }

    private void shareSuccess() {
        ShareUserInfo shareUserInfo = new ShareUserInfo();
        shareUserInfo.setFunctionName("AddUserSharePoint");
        ShareUserInfo.ParametersBean parametersBean = new ShareUserInfo.ParametersBean();
        parametersBean.setUserId(Integer.parseInt(userId));
        shareUserInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(IntegralDrawActivity.this);
        okGoUtils.httpPostJSON(shareUserInfo, true, false);
    }

    private static void showShare(Context context) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("零零7签到领积分啦");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(AppConfig.SHAREURL);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("零零7签到领积分啦");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(AppConfig.LOGOURL);//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(AppConfig.SHAREURL);
        // 启动分享GUI
        oks.show(context);
    }

    private void sign() {
        InteraglSignInfo signInfo = new InteraglSignInfo();
        signInfo.setFunctionName("AddUserSignInPoint");
        InteraglSignInfo.ParametersBean parametersBean = new InteraglSignInfo.ParametersBean();
        parametersBean.setUserId(userId);
        signInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(IntegralDrawActivity.this);
        okGoUtils.httpPostJSON(signInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo codeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (codeInfo.getCode() == 0) {
                    rl_pop.setVisibility(View.VISIBLE);
                } else {
                    //分享
                    rl_pop.setVisibility(View.VISIBLE);
                    tv_one.setText("您已签过到了");
                    tv_two.setText("签到获得2积分");
                    tv_three.setText("分享可获得更多积分");
                    bt_share.setText("去分享");
                    jump=0;
                }
            }
        });
    }

    @Override
    protected void doMain() {
        requestData();
        checkZhongMa();
        requestDanMu();
    }

    String sb="";

    private void requestDanMu() {
        TongyongInfo tongyongInfo = new TongyongInfo();
        tongyongInfo.setFunctionName("ListPointPrizeWinner");
        OkGoUtils okGoUtils = new OkGoUtils(IntegralDrawActivity.this);
        okGoUtils.httpPostJSON(tongyongInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                WinnerDanmu winnerDanmu = JSON.parseObject(response, WinnerDanmu.class);
                if (winnerDanmu.getCode() == 0) {
                    if (winnerDanmu.getData().getPointPrizeWinner() != null) {
                        if (winnerDanmu.getData().getPointPrizeWinner().size() > 0) {
                            for (int i = 0; i < winnerDanmu.getData().getPointPrizeWinner().size(); i++) {
                                sb=sb+"恭喜"+ ZeroZeroSevenUtils.phoneClose(winnerDanmu.getData().getPointPrizeWinner().get(i).getUserPhone())+"获得了"+
                                        winnerDanmu.getData().getPointPrizeWinner().get(i).getPrizeName()+"    ";
                            }
                            if(!TextUtils.isEmpty(sb)){
                                tv_pao.setText(sb);
                            }else{
                                tv_pao.setText("暂无中奖信息");
                            }

                        }
                    }
                }
            }
        });
    }

    private void checkZhongMa() {
        ZhongMaInfo zhongMaInfo = new ZhongMaInfo();
        zhongMaInfo.setFunctionName("QueryPrizeWinning");
        ZhongMaInfo.ParametersBean parametersBean = new ZhongMaInfo.ParametersBean();
        parametersBean.setUserPhone(userInfo.getPhone());
        zhongMaInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(IntegralDrawActivity.this);
        okGoUtils.httpPostJSON(zhongMaInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                zhongLeInfo = JSON.parseObject(response, ZhongLeInfo.class);
                if (zhongLeInfo.getCode() == 0) {
                    if (zhongLeInfo.getData().getPointPrizeWinners().isAccept()) {
                        rl_zhong.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }


    private void requestData() {
        InteraglSignInfo interaglSignInfo = new InteraglSignInfo();
        interaglSignInfo.setFunctionName("ListPointJackpotPrize");
        OkGoUtils okGoUtils = new OkGoUtils(IntegralDrawActivity.this);
        okGoUtils.httpPostJSON(interaglSignInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                JiangChiInfo jiangChiInfo = JSON.parseObject(response, JiangChiInfo.class);
                refreshlayout.finishRefresh();
                if (jiangChiInfo.getCode() == 0) {
                    if(jiangChiInfo.getData().getJackpotPrizes().size()>0){
                        if(jiangChiInfo.getData().getJackpotPrizes().size()>=4){
                            view_bot.setVisibility(View.VISIBLE);
                        }else{
                            view_bot.setVisibility(View.GONE);
                        }
                        adapter.cleanDates();
                        adapter.addAll(jiangChiInfo.getData().getJackpotPrizes());
                    }
                } else {
                    ToastUtils.showShort("奖池暂无信息");
                }
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        requestData();
    }
}
