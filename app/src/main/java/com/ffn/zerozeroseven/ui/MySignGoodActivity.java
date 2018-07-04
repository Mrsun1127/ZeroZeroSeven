package com.ffn.zerozeroseven.ui;

import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.MySignGoodAdapter;
import com.ffn.zerozeroseven.base.BasePopRefreshActivity;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.ZhongJiangListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.MySignGoodInfo;
import com.ffn.zerozeroseven.bean.requsetbean.NaJiangInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;

public class MySignGoodActivity extends BasePopRefreshActivity {

    private ZhongJiangListInfo zhongJiangListInfo;
    private MySignGoodAdapter mySignGoodAdapter;
    int issueId;
    @Override
    protected BaseRecyclerAdapter setAdapter() {
        mySignGoodAdapter = new MySignGoodAdapter(MySignGoodActivity.this);
        return mySignGoodAdapter;
    }

    @Override
    protected void doMain() {
        mySignGoodAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                if(mySignGoodAdapter.getItem(position).isAccept()){//去填写信息
                    rl_zhong.setVisibility(View.VISIBLE);
                    issueId=mySignGoodAdapter.getItem(position).getIssuePrizeId();
                }else{
                    rl_zhong.setVisibility(View.VISIBLE);
                    issueId=mySignGoodAdapter.getItem(position).getIssuePrizeId();
                }
                tv_name.setText(mySignGoodAdapter.getItem(position).getPrizeName());
            }
        });
    }

    @Override
    protected String setTopTitle() {
        return "我的中奖记录";
    }

    @Override
    protected void readRespones(String response) {
        zhongJiangListInfo = JSON.parseObject(response,ZhongJiangListInfo.class);
    }

    @Override
    protected int setFlag() {
        return zhongJiangListInfo.getCode();
    }

    @Override
    protected int setSize() {
        return zhongJiangListInfo.getData().getPointPrizeWinnerList().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(zhongJiangListInfo.getData().getPointPrizeWinnerList());
    }

    @Override
    protected Object setObj(int pageNo) {
        MySignGoodInfo mySignGoodInfo = new MySignGoodInfo();
        mySignGoodInfo.setFunctionName("ListWinningRecord");
        MySignGoodInfo.ParametersBean parametersBean = new MySignGoodInfo.ParametersBean();
        parametersBean.setUserPhone(userInfo.getPhone());
        mySignGoodInfo.setParameters(parametersBean);
        return mySignGoodInfo;
    }

    @Override
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
        okGoUtils.httpPostJSON(naJiangInfo,true,true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                if(JsonUtil.getFieldValue(response,"code").equals("0")){
                    rl_zhong.setVisibility(View.GONE);
                    ToastUtils.showShort("后台将尽快安排配送");
                }else{
                    rl_zhong.setVisibility(View.GONE);
                    ToastUtils.showShort(JsonUtil.getFieldValue(response,"message"));
                }
            }
        });
    }
}
