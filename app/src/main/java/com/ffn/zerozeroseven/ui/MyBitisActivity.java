package com.ffn.zerozeroseven.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.BitisAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.QiangShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.XiaoYuanQiangInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyBitisActivity extends BaseActivity {
    @Bind(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    @Bind(R.id.topView)
    TopView topView;
    private BitisAdapter bitisAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activtity_mybitis;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("许愿墙");
        topView.setTvRightDrawable(R.drawable.bitits_post);
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {
                Bundle bundle=new Bundle();
                bundle.putString("showType","01");
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this, MineWantGoQiangActivity.class,bundle);
            }

            @Override
            public void Back() {
                finish();
            }
        });
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        bitisAdapter = new BitisAdapter(this);
        recycleview.setAdapter(bitisAdapter);
    }

    @Override
    protected void doMain() {
        requestDate("");
    }
    @OnClick({R.id.rl_love,R.id.rl_good,R.id.rl_find,R.id.rl_friend})
    void setOnClicks(View v) {
        Bundle bundle=new Bundle();
        switch (v.getId()) {
            case R.id.rl_love:
                bundle.putString("showType","01");
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this, MineWantGoQiangActivity.class,bundle);
            break;
            case R.id.rl_good:
                bundle.putString("showType","02");
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this, MineWantGoQiangActivity.class,bundle);
                break;
            case R.id.rl_find:
                bundle.putString("showType","03");
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this, MineWantGoQiangActivity.class,bundle);
                break;
            case R.id.rl_friend:
                bundle.putString("showType","04");
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this, MineWantGoQiangActivity.class,bundle);
                break;

        }
    }
    private void requestDate(String type) {
        XiaoYuanQiangInfo qiangInfo = new XiaoYuanQiangInfo();
        qiangInfo.setFunctionName("ListPost");
        XiaoYuanQiangInfo.ParametersBean parametersBean = new XiaoYuanQiangInfo.ParametersBean();
        parametersBean.setPageIndex(0);
        parametersBean.setPageSize(20);
        parametersBean.setPostType(type);
        qiangInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(MyBitisActivity.this);
        okGoUtils.httpPostJSON(qiangInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                QiangShowInfo showInfo = JSON.parseObject(response, QiangShowInfo.class);
                if (showInfo.getCode() == 0 && showInfo.getData().getItems().size() > 0) {
                    bitisAdapter.addAll(showInfo.getData().getItems());
                }
            }
        });
    }
}
