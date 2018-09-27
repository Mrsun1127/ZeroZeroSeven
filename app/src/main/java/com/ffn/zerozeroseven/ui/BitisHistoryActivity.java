package com.ffn.zerozeroseven.ui;


import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.BitisNewAdapter;
import com.ffn.zerozeroseven.adapter.HistoryNewAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;
import com.ffn.zerozeroseven.bean.BitisHistoryInfo;
import com.ffn.zerozeroseven.bean.BitisInfo;
import com.ffn.zerozeroseven.bean.requsetbean.XiaoYuanQiangInfo;

import java.lang.ref.WeakReference;

public class BitisHistoryActivity extends BaseRefreshActivity {

    private BitisHistoryInfo bitisInfo;
    public static WeakReference<BitisHistoryActivity> mInstance;
    private HistoryNewAdapter bitisNewAdapter;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        mInstance = new WeakReference<>(this);
        bitisNewAdapter = new HistoryNewAdapter(this);
        return bitisNewAdapter;
    }

    @Override
    protected void doMain() {
    }

    public void addItemBean(BitisHistoryInfo.DataBean.PostsBean.MessagesBean item, int position) {
        bitisNewAdapter.getItem(position).getMessages().add(item);
        bitisNewAdapter.notifyItemChanged(position);
    }

    public void removeItemBean(int position) {
        bitisNewAdapter.getItem(bitisNewAdapter.clickPosition).getMessages().remove(position);
        bitisNewAdapter.notifyItemChanged(bitisNewAdapter.clickPosition);
    }

    @Override
    protected String setTopTitle() {
        return "我的发表";
    }

    @Override
    protected void readRespones(String response) {
        bitisInfo = JSON.parseObject(response, BitisHistoryInfo.class);
    }

    @Override
    protected int setFlag() {
        return bitisInfo.getCode();
    }

    @Override
    protected int setSize() {
        return bitisInfo.getData().getPosts().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(bitisInfo.getData().getPosts());
    }

    @Override
    protected Object setObj(int pageNo) {
        XiaoYuanQiangInfo qiangInfo = new XiaoYuanQiangInfo();
        qiangInfo.setFunctionName("ListUserPost");
        XiaoYuanQiangInfo.ParametersBean parametersBean = new XiaoYuanQiangInfo.ParametersBean();
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(6);
        qiangInfo.setParameters(parametersBean);
        return qiangInfo;
    }
}
