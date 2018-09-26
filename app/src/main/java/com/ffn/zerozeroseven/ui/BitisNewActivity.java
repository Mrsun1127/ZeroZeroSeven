package com.ffn.zerozeroseven.ui;

import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.BitisNewAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;
import com.ffn.zerozeroseven.bean.BitisInfo;
import com.ffn.zerozeroseven.bean.CountInfo;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.RecentliyNewsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RBitisRecentliyNewsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RClickBitisInfo;
import com.ffn.zerozeroseven.bean.requsetbean.XiaoYuanQiangInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import java.lang.ref.WeakReference;

public class BitisNewActivity extends BaseRefreshActivity {

    private BitisInfo bitisInfo;
    public static WeakReference<BitisNewActivity> mInstance;
    private BitisNewAdapter bitisNewAdapter;
    private RecentliyNewsInfo recentliyNewsInfo;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        mInstance = new WeakReference<>(this);
        tv_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZeroZeroSevenUtils.SwitchActivity(BitisNewActivity.this, RecentliyNewsActivity.class);
            }
        });
        setRight();
        bitisNewAdapter = new BitisNewAdapter(this);
        return bitisNewAdapter;
    }

    @Override
    protected void doMain() {
    }

    private void requestCount() {
        RBitisRecentliyNewsInfo rBitisRecentliyNewsInfo = new RBitisRecentliyNewsInfo();
        rBitisRecentliyNewsInfo.setFunctionName("CountLatestMessage");
        RBitisRecentliyNewsInfo.ParametersBean parametersBean = new RBitisRecentliyNewsInfo.ParametersBean();
        parametersBean.setUserId(BaseAppApplication.getInstance().getLoginUser().getId());
        rBitisRecentliyNewsInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(BitisNewActivity.this);
        okGoUtils.httpPostJSON(rBitisRecentliyNewsInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                CountInfo countInfo = JSON.parseObject(response, CountInfo.class);
                if (countInfo.getCode() == 0) {
                    if (countInfo.getData().getCount() > 0) {
                        tv_news.setVisibility(View.VISIBLE);
                        tv_news.setText(countInfo.getData().getCount() + "条新信息");
                    } else {
                        tv_news.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestCount();
    }

    public void addItemBean(BitisInfo.DataBean.ItemsBean.MessagesBean item, int position) {
        bitisNewAdapter.getItem(position).getMessages().add(item);
        bitisNewAdapter.notifyItemChanged(position);
    }

    @Override
    protected String setTopTitle() {
        return "许愿墙";
    }

    @Override
    protected void readRespones(String response) {
        bitisInfo = JSON.parseObject(response, BitisInfo.class);
    }

    @Override
    protected int setFlag() {
        return bitisInfo.getCode();
    }

    @Override
    protected int setSize() {
        return bitisInfo.getData().getItems().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(bitisInfo.getData().getItems());
    }

    @Override
    protected Object setObj(int pageNo) {
        XiaoYuanQiangInfo qiangInfo = new XiaoYuanQiangInfo();
        qiangInfo.setFunctionName("ListPost");
        XiaoYuanQiangInfo.ParametersBean parametersBean = new XiaoYuanQiangInfo.ParametersBean();
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(20);
        qiangInfo.setParameters(parametersBean);
        return qiangInfo;
    }
}
