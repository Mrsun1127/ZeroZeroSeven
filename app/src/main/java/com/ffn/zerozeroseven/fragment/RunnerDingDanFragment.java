package com.ffn.zerozeroseven.fragment;

import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;

public class RunnerDingDanFragment extends BaseReFreshFragment {
    @Override
    protected BaseRecyclerAdapter setAdapter() {
        isLoadMore = false;
        return null;
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
