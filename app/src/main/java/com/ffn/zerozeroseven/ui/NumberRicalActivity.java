package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.LevelAttrAdapter;
import com.ffn.zerozeroseven.adapter.LevelBrandAdapter;
import com.ffn.zerozeroseven.adapter.LevelOneAdapter;
import com.ffn.zerozeroseven.adapter.LevelTwoAdapter;
import com.ffn.zerozeroseven.adapter.LevelThreeAdapter;
import com.ffn.zerozeroseven.adapter.NumberRicalVerticalAdapter;
import com.ffn.zerozeroseven.adapter.PopAttrAdapter;
import com.ffn.zerozeroseven.adapter.PopBrandAdapter;
import com.ffn.zerozeroseven.adapter.PopSpecAdapter;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.RgRefreshStatus;
import com.ffn.zerozeroseven.bean.NumberLevelInfo;
import com.ffn.zerozeroseven.bean.NumberListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.NumberHomeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.VerticalReInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.UiTipUtil;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.FullyGridLayoutManager;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.TopView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

public class NumberRicalActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    @Bind(R.id.rc_vetical)
    RecyclerView vetical;
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
    @Bind(R.id.rc_levelattr)
    RecyclerView rc_levelattr;
    @Bind(R.id.rc_levelbrand)
    RecyclerView rc_levelbrand;
    private ArrayList<NumberLevelInfo.DataBean.CategoriesBean> level1List;
    private ArrayList<NumberLevelInfo.DataBean.CategoriesBean> level2List;
    private LevelOneAdapter oneAdapter;
    private LevelTwoAdapter twoAdapter;
    private LevelThreeAdapter threeAdapter;
    private NumberRicalVerticalAdapter verticalAdapter;
    int levelId = -1;
    int specId = -1;
    int brandId = -1;
    String attrValue = "";
    int pageIndex = 0;
    private LevelAttrAdapter attrAdapter;
    private LevelBrandAdapter brandAdapter;
    private NumberListInfo numberListInfo;
    @Bind(R.id.rc_select)
    RecyclerView rc_select;
    String clickType = "";
    private PopAttrAdapter popAttrAdapter;
    private PopSpecAdapter popSpecAdapter;
    private PopBrandAdapter popBrandAdapter;
    private int oneClickPositon = 0;
    private NumberLevelInfo levelInfo;

    @Override
    protected int setLayout() {
        return R.layout.activity_numberrical;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        badgeView = new QBadgeView(this);
        badgeView.bindTarget(iv_shopcar);
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
        refreshlayout.setOnRefreshListener(this);
        refreshlayout.setOnLoadmoreListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NumberRicalActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(NumberRicalActivity.this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(NumberRicalActivity.this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(NumberRicalActivity.this);
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(NumberRicalActivity.this);
        linearLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);

        rc_levelattr.setLayoutManager(linearLayoutManager3);
        rc_levelbrand.setLayoutManager(linearLayoutManager4);
        rc_levelthree.setLayoutManager(linearLayoutManager);
        rc_leveltwo.setLayoutManager(linearLayoutManager1);
        rl_levelone.setLayoutManager(linearLayoutManager2);
        vetical.setLayoutManager(new LinearLayoutManager(NumberRicalActivity.this));
        vetical.addItemDecoration(new SpaceItemDecoration(2));
        oneAdapter = new LevelOneAdapter(this);
        twoAdapter = new LevelTwoAdapter(this);


        rl_levelone.setAdapter(oneAdapter);
        rc_leveltwo.setAdapter(twoAdapter);
        oneAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                oneClickPositon = position;
                ll_select_pop.setVisibility(View.GONE);
                oneAdapter.setClickPosition(position);
                level2BottomList.clear();
                for (int i = 0; i < level2List.size(); i++) {
                    if (level2List.get(i).getParentId() == oneAdapter.getItem(position).getId()) {
                        level2BottomList.add(level2List.get(i));
                    }
                }
                twoAdapter.setClickPosition(-1);
                popAttrAdapter.setClickPosition(-1);
                popBrandAdapter.setClickPosition(-1);
                popSpecAdapter.setClickPosition(-1);
                twoAdapter.cleanDates();
                twoAdapter.addAll(level2BottomList);
                attrAdapter.setClickPosition(-1);
                threeAdapter.setClickPosition(-1);
                brandAdapter.setClickPosition(-1);
//                if (levelInfo.getData().getCategories().get(position).getFilterAttrList().size() > 0) {
//                    attrAdapter.cleanDates();
//                    attrAdapter.addAll(levelInfo.getData().getCategories().get(position).getFilterAttrList());
//                } else {
//                    attrAdapter.cleanDates();
//                }
//                if (levelInfo.getData().getCategories().get(position).getFilterSpecList().size() > 0) {
//                    threeAdapter.cleanDates();
//                    threeAdapter.addAll(levelInfo.getData().getCategories().get(position).getFilterSpecList());
//                } else {
//                    threeAdapter.cleanDates();
//                }
                rgRefreshStatus = RgRefreshStatus.IDLE;
                pageIndex = 0;
//                findVerticalData(oneAdapter.getItem(position).getId(), 0, 0, "", 0);
                findVerticalData(oneAdapter.getItem(position).getId(), 0);
            }
        });
        twoAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                twoAdapter.setClickPosition(position);
                ll_select_pop.setVisibility(View.GONE);
                attrAdapter.setClickPosition(-1);
                threeAdapter.setClickPosition(-1);
                brandAdapter.setClickPosition(-1);
                popAttrAdapter.setClickPosition(-1);
                popBrandAdapter.setClickPosition(-1);
                popSpecAdapter.setClickPosition(-1);
                rgRefreshStatus = RgRefreshStatus.IDLE;
                pageIndex = 0;
//                findVerticalData(twoAdapter.getItem(position).getId(), 0, 0, "", 0);
                findVerticalData(twoAdapter.getItem(position).getId(), 0);
            }
        });

        verticalAdapter = new NumberRicalVerticalAdapter(this);
        vetical.setAdapter(verticalAdapter);


        attrAdapter = new LevelAttrAdapter(this);
        rc_levelattr.setAdapter(attrAdapter);
        final List<String> attrList = new ArrayList<>();
        attrAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                attrAdapter.setClickPosition(position);
                ll_select_pop.setVisibility(View.VISIBLE);
                clickType = "attr";
                rc_select.setAdapter(popAttrAdapter);
                popAttrAdapter.cleanDates();
                String attrValues = levelInfo.getData().getCategories().get(oneClickPositon).getFilterAttrList().get(position).getAttrValues();
                String[] split = attrValues.split(",");
                attrList.clear();
                for (int i = 0; i < split.length; i++) {
                    attrList.add(split[i]);
                }
                popAttrAdapter.cleanDates();
                popAttrAdapter.addAll(attrList);
            }
        });


        threeAdapter = new LevelThreeAdapter(this);
        rc_levelthree.setAdapter(threeAdapter);
        threeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                threeAdapter.setClickPosition(position);
                ll_select_pop.setVisibility(View.VISIBLE);
                clickType = "spec";
                rc_select.setAdapter(popSpecAdapter);
                popSpecAdapter.cleanDates();
                popSpecAdapter.addAll(levelInfo.getData().getCategories().get(oneClickPositon).getFilterSpecList().get(position).getItems());

            }
        });

        brandAdapter = new LevelBrandAdapter(this);
        rc_levelbrand.setAdapter(brandAdapter);
        brandAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                brandAdapter.setClickPosition(position);
                ll_select_pop.setVisibility(View.VISIBLE);
                clickType = "brand";
                rc_select.setAdapter(popBrandAdapter);

                popBrandAdapter.cleanDates();
                popBrandAdapter.addAll(levelInfo.getData().getCategories().get(oneClickPositon).getFilterBrandList());
            }
        });


        rc_select.setLayoutManager(new FullyGridLayoutManager(this, 3));
        popAttrAdapter = new PopAttrAdapter(this);
        popSpecAdapter = new PopSpecAdapter(this);
        popBrandAdapter = new PopBrandAdapter(this);
        popAttrAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                if (popAttrAdapter.getClickPosition() == position) {
                    popAttrAdapter.setClickPosition(-1);
                    return;
                }
                popAttrAdapter.setClickPosition(position);
            }
        });
        popSpecAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                if (popSpecAdapter.getClickPosition() == position) {
                    popSpecAdapter.setClickPosition(-1);
                    return;
                }
                popSpecAdapter.setClickPosition(position);
            }
        });
        popBrandAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                if (popBrandAdapter.getClickPosition() == position) {
                    popBrandAdapter.setClickPosition(-1);
                    return;
                }
                popBrandAdapter.setClickPosition(position);
            }
        });
        verticalAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putString("url", AppConfig.NUMBERICALURL + verticalAdapter.getItem(position).getId());
                bundle.putString("title", "商品详情");
                ZeroZeroSevenUtils.SwitchActivity(NumberRicalActivity.this, WebViewActivity.class, bundle);
            }
        });
    }

    @Override
    protected void doMain() {
        FindLevel();
    }

    private ArrayList<NumberLevelInfo.DataBean.CategoriesBean> level2BottomList;

    private void FindLevel() {
        NumberHomeInfo homeInfo = new NumberHomeInfo();
        homeInfo.setFunctionName("ListDigitalCategory");
        NumberHomeInfo.ParametersBean parametersBean = new NumberHomeInfo.ParametersBean();
        homeInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(NumberRicalActivity.this);
        okGoUtils.httpPostJSON(homeInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                levelInfo = JSON.parseObject(response, NumberLevelInfo.class);
                if (levelInfo.getCode() == 0) {
                    level1List = new ArrayList<>();
                    level2List = new ArrayList<>();
                    level2BottomList = new ArrayList<>();
                    for (int i = 0; i < levelInfo.getData().getCategories().size(); i++) {
                        switch (levelInfo.getData().getCategories().get(i).getParentId()) {
                            case 0:
                                level1List.add(levelInfo.getData().getCategories().get(i));
                                break;
                            default:
                                level2List.add(levelInfo.getData().getCategories().get(i));
                                break;
                        }
                    }
                    for (int i = 0; i < level2List.size(); i++) {
                        if (level2List.get(i).getParentId() == level1List.get(0).getId()) {
                            level2BottomList.add(level2List.get(i));
                        }
                    }
                    oneAdapter.addAll(level1List);
                    twoAdapter.addAll(level2BottomList);
                    levelId = level1List.get(0).getId();

//                    if (levelInfo.getData().getCategories().get(0).getFilterAttrList().size() > 0) {
//                        attrAdapter.cleanDates();
//                        attrAdapter.addAll(levelInfo.getData().getCategories().get(0).getFilterAttrList());
//                    } else {
//                        attrAdapter.cleanDates();
//                    }
//                    if (levelInfo.getData().getCategories().get(0).getFilterSpecList().size() > 0) {
//                        threeAdapter.cleanDates();
//                        threeAdapter.addAll(levelInfo.getData().getCategories().get(0).getFilterSpecList());
//                    } else {
//                        threeAdapter.cleanDates();
//                    }
//                    List<String> strings = new ArrayList<>();
//                    strings.add("品牌");
//                    brandAdapter.cleanDates();
//                    brandAdapter.addAll(strings);
                    findVerticalData(levelId, pageIndex);
                }
            }
        });
    }

    //    private void findVerticalData(int levelId, int specId, int brandId, final String attrValue, int pageIndex) {
    private void findVerticalData(int levelId, int pageIndex) {
        VerticalReInfo verticalReInfo = new VerticalReInfo();
        verticalReInfo.setFunctionName("ListDigitalGoods");
        VerticalReInfo.ParametersBean parametersBean = new VerticalReInfo.ParametersBean();
        parametersBean.setCategoryId(levelId);
//        if (specId != -1) {
//            parametersBean.setSpecItemId(specId);
//        }
//        if (brandId != -1) {
//            parametersBean.setBrandId(brandId);
//        }
//        parametersBean.setAttrValue(attrValue);
        parametersBean.setPageSize(6);
        parametersBean.setSchoolId(schoolIId);
        parametersBean.setPageIndex(pageIndex);
        verticalReInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(NumberRicalActivity.this);
        okGoUtils.httpPostJSON(verticalReInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                disLoadState();
                numberListInfo = JSON.parseObject(response, NumberListInfo.class);
                if (numberListInfo.getCode() == 0) {
                    switch (rgRefreshStatus) {
                        case IDLE:
                        case REFRESHING:
                            verticalAdapter.cleanDates();
                            if (numberListInfo.getData().getList().size() > 0) {
                                iv_nodate.setVisibility(View.GONE);
                                vetical.setVisibility(View.VISIBLE);
                                verticalAdapter.addAll(numberListInfo.getData().getList());
                            } else {
                                iv_nodate.setVisibility(View.VISIBLE);
                                vetical.setVisibility(View.GONE);
                            }
                            break;
                        case PULL_DOWN:
                            if (numberListInfo.getData().getList().size() == 0) {
                                UiTipUtil.showToast(NumberRicalActivity.this, R.string.no_more_data);
                            } else {
                                verticalAdapter.addAll(numberListInfo.getData().getList());
                            }
                            break;
                    }
                } else {
                    vetical.setVisibility(View.GONE);
                    verticalAdapter.cleanDates();
                    iv_nodate.setVisibility(View.VISIBLE);
                }
            }
        });
        //breakl;
    }

    @OnClick({R.id.iv_shopcar, R.id.bt_close, R.id.bt_sure})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_close:
                ll_select_pop.setVisibility(View.GONE);
                popAttrAdapter.setClickPosition(-1);
                popBrandAdapter.setClickPosition(-1);
                popSpecAdapter.setClickPosition(-1);
                if (twoAdapter.getClickPosition() != -1) {
                    levelId = twoAdapter.getItem(twoAdapter.getClickPosition()).getId();
                } else {
                    levelId = oneAdapter.getItem(oneAdapter.getClickPosition()).getId();
                }
                rgRefreshStatus = RgRefreshStatus.IDLE;
//                findVerticalData(levelId, 0, 0, "", 0);
                findVerticalData(levelId, 0);
                break;
            case R.id.bt_sure:
                pageIndex = 0;
                ll_select_pop.setVisibility(View.GONE);
                if (twoAdapter.getClickPosition() != -1) {
                    levelId = twoAdapter.getItem(twoAdapter.getClickPosition()).getId();
                } else {
                    levelId = oneAdapter.getItem(oneAdapter.getClickPosition()).getId();
                }
                rgRefreshStatus = RgRefreshStatus.IDLE;
                try {
                    if (popSpecAdapter.getClickPosition() != -1) {
                        specId = levelInfo.getData().getCategories().get(oneClickPositon).getFilterSpecList().get(threeAdapter.getClickPosition()).getItems().get(popSpecAdapter.getClickPosition()).getId();
                    } else {
                        specId = 0;
                    }
                } catch (Exception e) {
                    specId = 0;
                }
                try {
                    if (popBrandAdapter.getClickPosition() != -1) {
                        brandId = levelInfo.getData().getCategories().get(oneClickPositon).getFilterBrandList().get(popBrandAdapter.getClickPosition()).getId();
                    } else {
                        brandId = 0;
                    }
                } catch (Exception e) {
                    brandId = 0;
                }

                try {
                    if (popAttrAdapter.getClickPosition() != -1) {
                        String s = levelInfo.getData().getCategories().get(oneClickPositon).getFilterAttrList().get(attrAdapter.getClickPosition()).getAttrValues();
                        String[] split = s.split(",");
                        attrValue = levelInfo.getData().getCategories().get(oneClickPositon).getFilterAttrList().get(attrAdapter.getClickPosition()).getId() + "_" + split[popAttrAdapter.getClickPosition()];
                    } else {
                        attrValue = "";
                    }
                } catch (Exception e) {
                    attrValue = "";
                }
//                findVerticalData(levelId, specId, brandId, attrValue, pageIndex);
                findVerticalData(levelId, pageIndex);
                break;
            case R.id.iv_shopcar:
                ZeroZeroSevenUtils.SwitchActivity(NumberRicalActivity.this, NumberRicalShopCarActivity.class);
                break;
        }
    }

    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;

    private void disLoadState() {
        switch (rgRefreshStatus) {
            case IDLE:
                break;
            case REFRESHING:
                refreshlayout.finishRefresh();
                break;
            case PULL_DOWN:
                refreshlayout.finishLoadmore();
                break;
        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        rgRefreshStatus = RgRefreshStatus.REFRESHING;
        pageIndex = 0;
        twoAdapter.setClickPosition(-1);
        popAttrAdapter.setClickPosition(-1);
        popBrandAdapter.setClickPosition(-1);
        popSpecAdapter.setClickPosition(-1);
//        findVerticalData(oneAdapter.getItem(oneClickPositon).getId(), 0, 0, "", pageIndex);
        findVerticalData(oneAdapter.getItem(oneClickPositon).getId(), pageIndex);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        rgRefreshStatus = RgRefreshStatus.PULL_DOWN;
        pageIndex = pageIndex + 1;
//        findVerticalData(levelId, specId, brandId, attrValue, pageIndex);
        findVerticalData(levelId, pageIndex);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BaseAppApplication.getInstance().getNumberRicalInfo().getNumberRicalListInfo() != null) {
            if (BaseAppApplication.getInstance().getNumberRicalInfo().getNumberRicalListInfo().size() > 0) {
                badgeView.setBadgeNumber(BaseAppApplication.getInstance().getNumberRicalInfo().getNumberRicalListInfo().size());
            } else {
                badgeView.setBadgeNumber(0);
            }
        }
    }
}
