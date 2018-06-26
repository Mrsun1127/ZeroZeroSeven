package com.ffn.zerozeroseven.ui;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.MySignGoodAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;
import com.ffn.zerozeroseven.bean.ZhongJiangListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.MySignGoodInfo;

public class MySignGoodActivity extends BaseRefreshActivity {

    private ZhongJiangListInfo zhongJiangListInfo;
    private MySignGoodAdapter mySignGoodAdapter;

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

                }else{

                }
            }
        });
    }

    @Override
    protected String setTopTitle() {
        return "我的中奖记录1";
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
}
