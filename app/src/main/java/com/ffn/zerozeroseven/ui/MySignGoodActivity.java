package com.ffn.zerozeroseven.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.GoInMySignGoodAdapter;
import com.ffn.zerozeroseven.adapter.MySignGoodAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.RgRefreshStatus;
import com.ffn.zerozeroseven.bean.ZhongJiangListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.MySignGoodInfo;
import com.ffn.zerozeroseven.bean.requsetbean.NaJiangInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.TopView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySignGoodActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
    @Bind(R.id.recycleview)
    RecyclerView mySignView;
    @Bind(R.id.recycleviewgoin)
    RecyclerView goInView;
    private ZhongJiangListInfo zhongJiangListInfo;
    private MySignGoodAdapter mySignGoodAdapter;
    private GoInMySignGoodAdapter goInMySignGoodAdapter;
    int issueId;
    @Bind(R.id.rl_zhong)
    RelativeLayout rl_zhong;
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.et_phone)
    EditText et_phone;
    @Bind(R.id.et_adr)
    EditText et_adr;
    @Bind(R.id.et_name)
    EditText et_name;
    int curPosition;
    @Bind(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.rl_bot)
    RelativeLayout rl_bot;
    @Bind(R.id.rl_top)
    RelativeLayout rl_top;
    @Bind(R.id.bt_sub)
    Button bt_sub;
    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;
    int pageNo = 0;

    private void setLoadPage() {
        switch (rgRefreshStatus) {
            case PULL_DOWN:
                pageNo = pageNo + 1;
                break;
            case IDLE:
            case REFRESHING:
                pageNo = 0;
                break;
        }
    }

    private void disLoadState() {
        switch (rgRefreshStatus) {
            case IDLE:
                break;
            case REFRESHING:
                refreshLayout.finishRefresh();
                break;
            case PULL_DOWN:
                refreshLayout.finishLoadmore();
                break;
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_lookpop;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("参与记录");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {
            }

            @Override
            public void Back() {
                finish();
            }
        });
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        mySignGoodAdapter = new MySignGoodAdapter(this);
        goInMySignGoodAdapter = new GoInMySignGoodAdapter(this);
        mySignView.setNestedScrollingEnabled(false);
        goInView.setNestedScrollingEnabled(false);
        mySignView.setLayoutManager(new LinearLayoutManager(this));
        goInView.setLayoutManager(new LinearLayoutManager(this));
        mySignView.addItemDecoration(new SpaceItemDecoration(2));
        goInView.addItemDecoration(new SpaceItemDecoration(2));
        mySignView.setAdapter(mySignGoodAdapter);
        goInView.setAdapter(goInMySignGoodAdapter);
        mySignGoodAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                rl_zhong.setVisibility(View.VISIBLE);
                tv_name.setText("【第" + mySignGoodAdapter.getItem(position).getPrizeIssue() + "期】" + mySignGoodAdapter.getItem(position).getPrizeName());
                curPosition = position;
                issueId = mySignGoodAdapter.getItem(position).getIssuePrizeId();
                if (mySignGoodAdapter.getItem(position).isAccept()) {
                    bt_sub.setVisibility(View.GONE);
                    et_phone.setText(mySignGoodAdapter.getItem(position).getAwardAddress().getContactPhone());
                    et_adr.setText(mySignGoodAdapter.getItem(position).getAwardAddress().getContactAddress());
                    et_name.setText(mySignGoodAdapter.getItem(position).getAwardAddress().getContactName());
                } else {
                    bt_sub.setVisibility(View.VISIBLE);
                    et_phone.setText("");
                    et_adr.setText("");
                    et_name.setText("");
                }
            }
        });
        goInMySignGoodAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "sign");
                bundle.putInt("issuePrizeId", goInMySignGoodAdapter.getItem(position).getIssuePrizeId());
                ZeroZeroSevenUtils.SwitchActivity(MySignGoodActivity.this, ProductDetilsActivity.class, bundle);
            }
        });
    }

    public void vetify(String name, String adr, String phone) {
        if (!TextUtils.isEmpty(name)) {
            if (!TextUtils.isEmpty(adr)) {
                if (!TextUtils.isEmpty(phone)) {
                    if (ZeroZeroSevenUtils.isMobileNO(phone)) {
                        lingJiangLa(name, phone, adr);
                    } else {
                        ToastUtils.showShort("请填写正确的手机号码");
                    }
                } else {
                    ToastUtils.showShort("请填写手机号码");
                }
            } else {
                ToastUtils.showShort("请填写地址");
            }
        } else {
            ToastUtils.showShort("请填写姓名");
        }
    }

    @OnClick({R.id.bt_sub, R.id.rl_close})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.rl_close:
                et_adr.setText("");
                et_name.setText("");
                et_phone.setText("");
                rl_zhong.setVisibility(View.GONE);
                break;
            case R.id.bt_sub:
                String name = et_name.getText().toString().trim();
                String adr = et_adr.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                if (!mySignGoodAdapter.getItem(curPosition).isAccept()) {
                    vetify(name, adr, phone);
                } else {
                    vetify(name, adr, phone);
                }
                break;

        }
    }

    @Override
    protected void doMain() {
        requestDate();
    }

    private void requestDate() {
        setLoadPage();
        OkGoUtils okGoUtils = new OkGoUtils(MySignGoodActivity.this);
        MySignGoodInfo mySignGoodInfo = new MySignGoodInfo();
        mySignGoodInfo.setFunctionName("ListWinningRecord");
        MySignGoodInfo.ParametersBean parametersBean = new MySignGoodInfo.ParametersBean();
        parametersBean.setUserPhone(userInfo.getPhone());
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(10);
        mySignGoodInfo.setParameters(parametersBean);
        okGoUtils.httpPostJSON(mySignGoodInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                zhongJiangListInfo = JSON.parseObject(response, ZhongJiangListInfo.class);

                        disLoadState();
                        if (zhongJiangListInfo.getCode() == 0) {
                            switch (rgRefreshStatus) {
                                case IDLE:
                                case REFRESHING:
                                    goInMySignGoodAdapter.clear();
                                    if (zhongJiangListInfo.getData().getParticipateList().getList().size() == 0) {
                                        goInView.setVisibility(View.GONE);
                                        rl_bot.setVisibility(View.VISIBLE);
                                    } else {
                                        goInView.setVisibility(View.VISIBLE);
                                        rl_bot.setVisibility(View.GONE);
                                        goInMySignGoodAdapter.addAll(zhongJiangListInfo.getData().getParticipateList().getList());
                                    }
                                    break;
                                case PULL_DOWN:
                                    refreshLayout.finishLoadmore();
                                    if (zhongJiangListInfo.getData().getParticipateList().getList().size() == 0) {
                                        ToastUtils.showShort("没有更多数据了");
                                    } else {
                                        goInMySignGoodAdapter.addAll(zhongJiangListInfo.getData().getParticipateList().getList());
                                    }
                                    break;
                            }
                            if (zhongJiangListInfo.getData().getPointPrizeList().size() > 0) {
                                mySignGoodAdapter.clear();
                                mySignGoodAdapter.addAll(zhongJiangListInfo.getData().getPointPrizeList());
                            } else {
                                rl_top.setVisibility(View.VISIBLE);
                                mySignView.setVisibility(View.GONE);
                            }
                        } else {
                            goInView.setVisibility(View.GONE);
                            rl_bot.setVisibility(View.VISIBLE);
                            rl_top.setVisibility(View.VISIBLE);
                            mySignView.setVisibility(View.GONE);
                        }
                    }
                });


    }


    protected void lingJiangLa(String name, String phone, String adr) {
        NaJiangInfo naJiangInfo = new NaJiangInfo();
        naJiangInfo.setFunctionName("AddPrizeAwardAddress");
        NaJiangInfo.ParametersBean parametersBean = new NaJiangInfo.ParametersBean();
        parametersBean.setContactAddress(adr);
        parametersBean.setContactName(name);
        parametersBean.setContactPhone(phone);
        parametersBean.setIssuePrizeId(issueId);
        parametersBean.setUserPhone(userInfo.getPhone());
        naJiangInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(MySignGoodActivity.this);
        okGoUtils.httpPostJSON(naJiangInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(final String response) {

                        rl_zhong.setVisibility(View.GONE);
                        et_adr.setText("");
                        et_name.setText("");
                        et_phone.setText("");
                        if (JsonUtil.getFieldValue(response, "code").equals("0")) {
                            ToastUtils.showShort("后台将尽快安排配送");
                            requestDate();
                        } else {
                            ToastUtils.showShort(JsonUtil.getFieldValue(response, "message"));
                        }
                    }
                });

    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        rgRefreshStatus = RgRefreshStatus.REFRESHING;
        requestDate();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        rgRefreshStatus = RgRefreshStatus.PULL_DOWN;
        requestDate();
    }
}
