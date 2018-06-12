package com.ffn.zerozeroseven.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.MyRunDingdanAdapter;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.MyRunDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.MyrunnerInfo;
import com.ffn.zerozeroseven.fragment.LookMoreRunFragment;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.TitleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2018/2/1.
 */

public class LookMoreRunActivity extends BaseActivity {
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.titleview)
    TitleView titleView;
    @Bind(R.id.ll_hot)
    LinearLayout ll_hot;
    @Bind(R.id.rc_myrun)
    RecyclerView rc_myrun;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private MyRunDingdanAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_lookmore;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        titleView.setTopText("我来跑腿");
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
        rc_myrun.setLayoutManager(new LinearLayoutManager(this));
        rc_myrun.addItemDecoration(new SpaceItemDecoration(15));
        adapter = new MyRunDingdanAdapter(this);
        rc_myrun.setAdapter(adapter);
    }

    @Override
    protected void doMain() {
        fragmentList.add(new LookMoreRunFragment());
        titleList.add("1");
        ShopViewPagerAdapter viewPagerAdapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(viewPagerAdapter);
        requestMyRunTask();
    }

    private MyRunDingDanInfo dingDanInfo;

    private void requestMyRunTask() {
        showLoadProgress();
        MyrunnerInfo myrunnerInfo = new MyrunnerInfo();
        myrunnerInfo.setFunctionName("ListSubscriberErrandOrder");
        MyrunnerInfo.ParametersBean parametersBean = new MyrunnerInfo.ParametersBean();
        parametersBean.setPageIndex(0);
        parametersBean.setStatus("2");
        parametersBean.setPageSize(1);
        myrunnerInfo.setParameters(parametersBean);
        httpPostJSON(myrunnerInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        ll_hot.setVisibility(View.VISIBLE);
                        rc_myrun.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dingDanInfo = JSON.parseObject(response.body().string(), MyRunDingDanInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        rc_myrun.setVisibility(View.VISIBLE);
                        ll_hot.setVisibility(View.GONE);
                        if (dingDanInfo.getCode() == 0 && dingDanInfo.getData().getList().size() > 0) {
                            adapter.addAll(dingDanInfo.getData().getList());
                        }else{
                            rc_myrun.setVisibility(View.GONE);
                            ll_hot.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }
}
