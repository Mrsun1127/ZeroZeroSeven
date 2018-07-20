package com.ffn.zerozeroseven.ui;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.LevelOneAdapter;
import com.ffn.zerozeroseven.adapter.LevelTwoAdapter;
import com.ffn.zerozeroseven.adapter.NumberRicalScrollAdapter;
import com.ffn.zerozeroseven.adapter.NumberRicalVerticalAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.NumberLevelInfo;
import com.ffn.zerozeroseven.bean.requsetbean.NumberHomeInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

public class NumberRicalActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;

    @Bind(R.id.rc_vetical)
    RecyclerView vetical;
    @Bind(R.id.iv_up)
    ImageView iv_up;
    @Bind(R.id.iv_shopcar)
    ImageView iv_shopcar;
    //    @Bind(R.id.scrollview)
//    NestedScrollView scrollview;
    @Bind(R.id.ll_select_pop)
    LinearLayout ll_select_pop;
    private QBadgeView badgeView;
    @Bind(R.id.rc_leveltwo)
    RecyclerView rc_leveltwo;
    @Bind(R.id.rl_levelone)
    RecyclerView rl_levelone;
    @Bind(R.id.rc_levelthree)
    RecyclerView rc_levelthree;
    @Bind(R.id.iv_nodate)
    ImageView iv_nodate;
    private ArrayList<NumberLevelInfo.DataBean.CategoriesBean> level1List;
    private ArrayList<NumberLevelInfo.DataBean.CategoriesBean> level2List;
    private ArrayList<NumberLevelInfo.DataBean.CategoriesBean> level3List;
    private LevelOneAdapter oneAdapter;
    private LevelTwoAdapter twoAdapter;
    private NumberRicalScrollAdapter threeAdapter;
    private NumberRicalVerticalAdapter verticalAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_numberrical;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        badgeView = new QBadgeView(this);
        badgeView.bindTarget(iv_shopcar);
        badgeView.setBadgeNumber(9);
        topView.setTopText("数码市场");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NumberRicalActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(NumberRicalActivity.this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(NumberRicalActivity.this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc_levelthree.setLayoutManager(linearLayoutManager);
        rc_leveltwo.setLayoutManager(linearLayoutManager1);
        rl_levelone.setLayoutManager(linearLayoutManager2);
        vetical.setLayoutManager(new LinearLayoutManager(NumberRicalActivity.this));
        vetical.addItemDecoration(new SpaceItemDecoration(2));
        rc_levelthree.setNestedScrollingEnabled(false);
        rc_leveltwo.setNestedScrollingEnabled(false);
        rl_levelone.setNestedScrollingEnabled(false);
        oneAdapter = new LevelOneAdapter(this);
        twoAdapter = new LevelTwoAdapter(this);
        threeAdapter = new NumberRicalScrollAdapter(this);
        rl_levelone.setAdapter(oneAdapter);
        rc_leveltwo.setAdapter(twoAdapter);
        rc_levelthree.setAdapter(threeAdapter);
        oneAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                oneAdapter.setClickPosition(position);
            }
        });
        twoAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                twoAdapter.setClickPosition(position);
            }
        });
        threeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                threeAdapter.setClickPosition(position);
            }
        });
        verticalAdapter = new NumberRicalVerticalAdapter(this);
        vetical.setAdapter(verticalAdapter);

    }

    @Override
    protected void doMain() {
        FindLevel();
    }

    private void FindLevel() {
        NumberHomeInfo homeInfo = new NumberHomeInfo();
        homeInfo.setFunctionName("ListDigitalCategory");
        OkGoUtils okGoUtils = new OkGoUtils(NumberRicalActivity.this);
        okGoUtils.httpPostJSON(homeInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                NumberLevelInfo levelInfo = JSON.parseObject(response, NumberLevelInfo.class);
                if (levelInfo.getCode() == 0) {
                    level1List = new ArrayList<>();
                    level2List = new ArrayList<>();
                    level3List = new ArrayList<>();
                    for (int i = 0; i < levelInfo.getData().getCategories().size(); i++) {
                        switch (levelInfo.getData().getCategories().get(i).getParentId()) {
                            case 0:
                                LogUtils.D("level1List", "i=" + i);
                                level1List.add(levelInfo.getData().getCategories().get(i));
                                break;
                            case 1:
                                LogUtils.D("leve21List", "i=" + i);
                                level2List.add(levelInfo.getData().getCategories().get(i));
                                break;
                            case 2:
                                LogUtils.D("leve31List", "i=" + i);
                                level3List.add(levelInfo.getData().getCategories().get(i));
                                break;
                        }
                    }
                    oneAdapter.addAll(level1List);
                    twoAdapter.addAll(level2List);
                    threeAdapter.addAll(level3List);
                    LogUtils.D("size", "1 size = " + level1List.size() + "2 size = " + level2List.size() + " 3 size = " + level3List.size());
                    if (level3List.size() > 0) {
                        vetical.setVisibility(View.VISIBLE);
                        iv_nodate.setVisibility(View.GONE);
                        verticalAdapter.addAll(level3List);
                    } else {
                        vetical.setVisibility(View.GONE);
                        iv_nodate.setVisibility(View.VISIBLE);
                    }


                }
            }
        });
    }

    @OnClick({R.id.iv_up, R.id.iv_shopcar})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.iv_up:
//                scrollview.scrollTo(0, 0);
                break;
            case R.id.iv_shopcar:
                ZeroZeroSevenUtils.SwitchActivity(NumberRicalActivity.this, NumberRicalShopCarActivity.class);
                break;
        }
    }
}
