package com.ffn.zerozeroseven.ui;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.BestNewAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;
import com.ffn.zerozeroseven.bean.BestNewInfo;
import com.ffn.zerozeroseven.bean.requsetbean.TongyongInfo;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

public class BestNewActivity extends BaseRefreshActivity {

    private BestNewInfo bestNewInfo;
    private BestNewAdapter bestNewAdapter;
    

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        bestNewAdapter = new BestNewAdapter(BestNewActivity.this);
        return bestNewAdapter;
    }

    @Override
    protected void doMain() {
        bestNewAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                try {
                    bundle.putInt("prizeId",bestNewAdapter.getItem(position).getJackpotId());
                }catch (Exception e){}
                ZeroZeroSevenUtils.SwitchActivity(BestNewActivity.this, ProductDetilsActivity.class,bundle);
            }
        });
    }

    @Override
    protected String setTopTitle() {
        return "最新揭晓";
    }

    @Override
    protected void readRespones(String response) {
        bestNewInfo = JSON.parseObject(response,BestNewInfo.class);
    }

    @Override
    protected int setFlag() {
        return bestNewInfo.getCode();
    }

    @Override
    protected int setSize() {
        return bestNewInfo.getData().getJackpotPrizes().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(bestNewInfo.getData().getJackpotPrizes());
    }

    @Override
    protected Object setObj(int pageNo) {
        TongyongInfo tongyongInfo = new TongyongInfo();
        tongyongInfo.setFunctionName("ListPointLatestAnnouncePrize");
        return tongyongInfo;
    }
}
