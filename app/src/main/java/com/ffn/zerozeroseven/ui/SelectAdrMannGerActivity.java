package com.ffn.zerozeroseven.ui;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.AllAdrAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.ShouHuoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.AllAdrInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DeleteAdrInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.PullSwipeMenuListView;
import com.ffn.zerozeroseven.view.TitleView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/27.
 */

public class SelectAdrMannGerActivity extends BaseActivity implements View.OnClickListener {
    PullSwipeMenuListView msgPullLv;
    private AllAdrAdapter allAdrAdapter;
    private ShouHuoInfo shouHuoInfo;

    @Override
    protected int setLayout() {
        return R.layout.activity_adrmanger;
    }

    @Override
    protected void doMain() {
//        GetAllAdr();
        msgPullLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("position", "" + i);
                setResult(2, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent();
            intent.putExtra("position", "0");
            setResult(4, intent);
            finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }


    private void GetAllAdr(final boolean refrsh) {
        OkGoUtils okGoUtils = new OkGoUtils(SelectAdrMannGerActivity.this);
        AllAdrInfo allAdrInfo = new AllAdrInfo();
        allAdrInfo.setFunctionName("ListUserAddress");
        okGoUtils.httpPostJSON(allAdrInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                shouHuoInfo = JSON.parseObject(response, ShouHuoInfo.class);
                if (shouHuoInfo.getCode() == 0) {//成功
                    if (shouHuoInfo.getData().getAddresses().size() > 0) {
                        if (allAdrAdapter != null) {
                            allAdrAdapter.setList(shouHuoInfo.getData().getAddresses());
                        } else {
                            allAdrAdapter = new AllAdrAdapter(shouHuoInfo.getData().getAddresses(), SelectAdrMannGerActivity.this, true);
                            msgPullLv.setAdapter(allAdrAdapter);
                        }
                    }
                }
            }
        });

    }

    @Override
    public void initView() {
        msgPullLv = findViewById(R.id.msg_pull_lv);
        Button bt_addadr = findViewById(R.id.bt_addadr);
        bt_addadr.setOnClickListener(this);
        TitleView titleView = findViewById(R.id.titleview);
        titleView.setTopText("地址管理");
        titleView.setOnTitleListener(new TitleView.OnTitleClickListener() {
            @Override
            public void ivBack() {
                Intent intent = new Intent();
                intent.putExtra("position", "0");
                setResult(3, intent);
                finish();
            }

            @Override
            public void ivDown() {

            }

            @Override
            public void ivMessAge() {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_addadr:
                ZeroZeroSevenUtils.SwitchActivity(SelectAdrMannGerActivity.this, AddNewAdrActivity.class);
                break;
            default:

                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        GetAllAdr(false);
    }
}
