package com.ffn.zerozeroseven.ui;

import com.ffn.zerozeroseven.adapter.MySignGoodAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;
import com.ffn.zerozeroseven.bean.requsetbean.MySignGoodInfo;

public class MySignGoodActivity extends BaseRefreshActivity {

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        MySignGoodAdapter mySignGoodAdapter = new MySignGoodAdapter(MySignGoodActivity.this);
        return mySignGoodAdapter;
    }

    @Override
    protected String setTopTitle() {
        return "我的中奖记录";
    }

    @Override
    protected void readRespones(String response) {

    }

    @Override
    protected int setFlag() {
        return 0;
    }

    @Override
    protected int setSize() {
        return 5;
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {

    }

    @Override
    protected Object setObj(int pageNo) {
        MySignGoodInfo mySignGoodInfo = new MySignGoodInfo();
        mySignGoodInfo.setFunctionName("ListPointPrizeWinningRecord");
        MySignGoodInfo.ParametersBean parametersBean = new MySignGoodInfo.ParametersBean();
        parametersBean.setUserPhone(userInfo.getPhone());
        mySignGoodInfo.setParameters(parametersBean);
        return mySignGoodInfo;
    }
}
