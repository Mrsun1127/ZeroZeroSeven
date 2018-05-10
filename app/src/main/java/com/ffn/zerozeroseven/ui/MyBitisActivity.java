package com.ffn.zerozeroseven.ui;


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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyBitisActivity extends BaseActivity {
@Bind(R.id.tv_top)
TextView tv_top;
@Bind(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
@Bind(R.id.recycleview)
    RecyclerView recycleview;
    private BitisAdapter bitisAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activtity_mybitis;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        bitisAdapter = new BitisAdapter(this);
        recycleview.setAdapter(bitisAdapter);
    }

    @Override
    protected void doMain() {
        requestDate("");
    }

    private void requestDate(String type) {
        XiaoYuanQiangInfo qiangInfo = new XiaoYuanQiangInfo();
        qiangInfo.setFunctionName("ListPost");
        XiaoYuanQiangInfo.ParametersBean parametersBean = new XiaoYuanQiangInfo.ParametersBean();
        parametersBean.setPageIndex(0);
        parametersBean.setPageSize(20);
        parametersBean.setPostType(type);
        qiangInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils=new OkGoUtils(MyBitisActivity.this);
        okGoUtils.httpPostJSON(qiangInfo,true,true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                QiangShowInfo showInfo = JSON.parseObject(response, QiangShowInfo.class);
                if(showInfo.getCode()==0 && showInfo.getData().getItems().size()>0){
                    bitisAdapter.addAll(showInfo.getData().getItems());
                }
            }
        });
    }

    @OnClick({R.id.rl_back,R.id.rl_post})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
            break;
            case R.id.rl_post:
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this,MineWantGoQiangActivity.class);
                break;
        }
    }
}
