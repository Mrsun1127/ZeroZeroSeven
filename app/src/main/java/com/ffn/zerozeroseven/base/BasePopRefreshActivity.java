package com.ffn.zerozeroseven.base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.bean.requsetbean.NaJiangInfo;
import com.ffn.zerozeroseven.ui.IntegralDrawActivity;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.UiTipUtil;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.StateLayout;
import com.ffn.zerozeroseven.view.TitleView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2018/1/17.
 */

public abstract class BasePopRefreshActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
    @Bind(R.id.refreshlayout)
    SmartRefreshLayout commonRefreshLayout;
    @Bind(R.id.recycleview)
    RecyclerView recyclerView;
    @Bind(R.id.common_stateLayout)
    StateLayout commonStateLayout;
    @Bind(R.id.tv_title)
    TitleView titleView;
    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.et_adr)
    EditText et_adr;
    @Bind(R.id.et_phone)
    EditText et_phone;
    @Bind(R.id.rl_zhong)
    public RelativeLayout rl_zhong;
    @Bind(R.id.rl_close)
    public RelativeLayout rl_close;
    BaseRecyclerAdapter adapter;
    private KProgressHUD hud;
    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;
    int pageNo = 0;
    @Bind(R.id.tv_name)
    public TextView tv_name;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("userInfo", BaseAppApplication.getInstance().getLoginUser());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        BaseAppApplication.getInstance().setLoginUser((UserInfo.DataBean) savedInstanceState.getSerializable("userInfo"));
    }

    private void setRefreshLayoutVis() {
        if (commonRefreshLayout.getVisibility() == View.GONE) {
            commonRefreshLayout.setVisibility(View.VISIBLE);
            commonStateLayout.setVisibility(View.GONE);
        }
    }

    private void showErrorLayout(int errType) {
        commonRefreshLayout.setVisibility(View.GONE);
        adapter.clear();
        commonStateLayout.setVisibility(View.VISIBLE);
        commonStateLayout.showError(errType);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_lookpop;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        BaseAppApplication.getInstance().addActivity(this);
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
        titleView.setTopText(setTopTitle());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpaceItemDecoration(25));
        adapter = setAdapter();
        recyclerView.setAdapter(adapter);
        commonRefreshLayout.setOnRefreshListener(this);
        commonRefreshLayout.setOnLoadmoreListener(this);
        commonStateLayout.setOnStateCallListener(new StateLayout.OnStateLayoutCallListener() {
            @Override
            public void reCall() {
                commonStateLayout.setVisibility(View.GONE);
                commonRefreshLayout.setVisibility(View.VISIBLE);
                rgRefreshStatus = RgRefreshStatus.IDLE;
                requestData();
            }
        });
        setRefreshLayoutVis();
        hud = KProgressHUD.create(BasePopRefreshActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setWindowColor(getResources().getColor(R.color.text_secondary_color))
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
        rl_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_zhong.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData();
    }

    @Override
    protected void doMain() {

    }

    protected void showLoadDialog(final String txt) {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                hud.setDetailsLabel(txt)
                        .show();
            }
        });

    }

    protected void showLoadDialog() {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                hud.setDetailsLabel("正在加载中")
                        .show();
            }
        });

    }

    protected void dissMissLoad() {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (hud != null) {
                    hud.dismiss();
                } else {
                }
            }
        });

    }

    private void disLoadState() {
        switch (rgRefreshStatus) {
            case IDLE:
                dissMissLoad();
                break;
            case REFRESHING:
                commonRefreshLayout.finishRefresh();
                dissMissLoad();
                break;
            case PULL_DOWN:
                commonRefreshLayout.finishLoadmore();
                dissMissLoad();
                break;
        }
    }

    private void setLoadPage() {
        switch (rgRefreshStatus) {
            case PULL_DOWN:
                pageNo = pageNo + 1;
                break;
            case IDLE:
                showLoadDialog();
            case REFRESHING:
                pageNo = 0;
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        rgRefreshStatus = RgRefreshStatus.REFRESHING;
        requestData();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
//        rgRefreshStatus = RgRefreshStatus.PULL_DOWN;
//        requestData();
        commonRefreshLayout.finishLoadmore();
    }

    protected abstract BaseRecyclerAdapter setAdapter();

    protected abstract String setTopTitle();

    protected abstract void readRespones(String response);

    protected abstract int setFlag();

    protected abstract int setSize();

    protected abstract void addAll(BaseRecyclerAdapter adapter);

    protected abstract Object setObj(int pageNo);

    public void requestData() {
        setLoadPage();
        httpPostJSON(setObj(pageNo), true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadState();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                readRespones(response.body().string());
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadState();
                        if (setFlag() == 0) {
                            switch (rgRefreshStatus) {
                                case IDLE:
                                case REFRESHING:
                                    commonRefreshLayout.finishRefresh();
                                    adapter.clear();
                                    if (setSize() == 0) {
                                        showErrorLayout(StateLayout.noData);
                                    } else {
                                        commonRefreshLayout.setVisibility(View.VISIBLE);
                                        commonStateLayout.setVisibility(View.GONE);
                                        addAll(adapter);
                                    }
                                    break;
                                case PULL_DOWN:
                                    commonRefreshLayout.finishLoadmore();
                                    if (setSize() == 0) {
                                        UiTipUtil.showToast(BasePopRefreshActivity.this, R.string.no_more_data);
                                    } else {
                                        addAll(adapter);
                                    }
                                    break;
                            }
                        } else {
                            showErrorLayout(StateLayout.noData);
                        }
                    }
                });
            }
        });
    }

    @OnClick({R.id.bt_sub})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_sub:
                String name = et_name.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                String adr = et_adr.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    if (!TextUtils.isEmpty(phone)) {
                        if (!TextUtils.isEmpty(adr)) {
                            lingJiangLa(name, phone, adr);
                        } else {
                            ToastUtils.showShort("请输入收货地址");
                        }
                    } else {
                        ToastUtils.showShort("请输入手机号码");
                    }
                } else {
                    ToastUtils.showShort("请填写姓名");
                }
                break;

        }
    }

    protected abstract void lingJiangLa(String name, String phone, String adr);


    @Override
    protected void onDestroy() {
        BaseAppApplication.getInstance().finishActivity(this);
        super.onDestroy();
    }
}
