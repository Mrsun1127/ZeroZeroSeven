package com.ffn.zerozeroseven.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.LouDongAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.LouDongInfo;
import com.ffn.zerozeroseven.bean.requsetbean.AddNewAdrInfo;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TitleView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/12/14.
 */

public class LouDongActivity extends BaseActivity {

    private RecyclerView rc_lou;
    private LouDongAdapter adapter;
    private LouDongInfo louDongInfo;

    @Override
    protected int setLayout() {
        return R.layout.activity_loudong;
    }

    @Override
    public void initView() {
        TitleView titleView = findViewById(R.id.titleview);
        titleView.setTopText("选择楼栋");
        titleView.setOnTitleListener(new TitleView.OnTitleClickListener() {
            @Override
            public void ivBack() {
                Intent intent = new Intent();
                intent.putExtra("loudong", "");
                setResult(0, intent);
                finish();
            }

            @Override
            public void ivDown() {

            }

            @Override
            public void ivMessAge() {

            }
        });
        rc_lou = findViewById(R.id.rc_view);
        rc_lou.setLayoutManager(new LinearLayoutManager(LouDongActivity.this));
        adapter = new LouDongAdapter(this);
        rc_lou.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                String louDong = adapter.getItem(position);
                Intent intent = new Intent();
//                intent.putExtra("id", louDongInfo.getData().get);
                intent.putExtra("loudong", louDong);
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    protected void doMain() {
        requestData();
    }

    private void requestData() {
        showLoadProgress();
        AddNewAdrInfo adrInfo = new AddNewAdrInfo();
        adrInfo.setFunctionName("ListSchoolBuilding");
        AddNewAdrInfo.ParametersBean parametersBean = new AddNewAdrInfo.ParametersBean();
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        adrInfo.setParameters(parametersBean);
        httpPostJSON(adrInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                louDongInfo = JSON.parseObject(response.body().string(), LouDongInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        if (louDongInfo.getCode() == 0) {
                            if (louDongInfo.getData() != null && louDongInfo.getData().getBuildingNames().size() > 0) {
                                adapter.addAll(louDongInfo.getData().getBuildingNames());
                            }
                        } else {
                            ZeroZeroSevenUtils.showCustonPop(LouDongActivity.this, louDongInfo.getMessage(), rc_lou);
                        }
                    }
                });
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent();
            intent.putExtra("loudong", "");
            setResult(0, intent);
            finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
