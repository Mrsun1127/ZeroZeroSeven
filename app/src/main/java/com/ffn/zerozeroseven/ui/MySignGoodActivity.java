package com.ffn.zerozeroseven.ui;

import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;

public class MySignGoodActivity extends BaseRefreshActivity {

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        return null;
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
        return 0;
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {

    }

    @Override
    protected Object setObj(int pageNo) {
        return null;
    }
}
