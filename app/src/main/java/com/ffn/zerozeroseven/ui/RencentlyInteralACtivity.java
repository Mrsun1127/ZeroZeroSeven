package com.ffn.zerozeroseven.ui;

import com.ffn.zerozeroseven.adapter.RencentlyAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;

import java.util.ArrayList;
import java.util.List;

public class RencentlyInteralACtivity extends BaseRefreshActivity {
    @Override
    protected BaseRecyclerAdapter setAdapter() {
        RencentlyAdapter adapter=new RencentlyAdapter(RencentlyInteralACtivity.this);
        return adapter;
    }

    @Override
    protected String setTopTitle() {
        return "最新揭晓";
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
        return 4;
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        List<String> s=new ArrayList<>();
        s.add("s");
        s.add("s");
        s.add("s");
        s.add("s");
        adapter.addAll(s);
    }

    @Override
    protected Object setObj(int pageNo) {
        return userInfo;
    }
}
