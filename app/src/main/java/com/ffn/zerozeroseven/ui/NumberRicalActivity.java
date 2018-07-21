package com.ffn.zerozeroseven.ui;

import android.support.v7.widget.GridLayoutManager;
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
import com.ffn.zerozeroseven.adapter.LevelPriceAdapter;
import com.ffn.zerozeroseven.adapter.LevelTwoAdapter;
import com.ffn.zerozeroseven.adapter.LevelThreeAdapter;
import com.ffn.zerozeroseven.adapter.NumberRicalVerticalAdapter;
import com.ffn.zerozeroseven.adapter.PopAttrAdapter;
import com.ffn.zerozeroseven.adapter.PopBrandAdapter;
import com.ffn.zerozeroseven.adapter.PopPriceAdapter;
import com.ffn.zerozeroseven.adapter.PopSpecAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.NumberLevelInfo;
import com.ffn.zerozeroseven.bean.NumberListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.NumberHomeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.VerticalReInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;

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
    @Bind(R.id.rc_levelattr)
    RecyclerView rc_levelattr;
    @Bind(R.id.rc_levelbrand)
    RecyclerView rc_levelbrand;
    @Bind(R.id.rc_levelprice)
    RecyclerView rc_levelprice;
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
    private LevelPriceAdapter priceAdapter;
    private NumberListInfo numberListInfo;
    @Bind(R.id.rc_select)
    RecyclerView rc_select;
    String clickType = "";
    private PopAttrAdapter popAttrAdapter;
    private PopSpecAdapter popSpecAdapter;
    private PopBrandAdapter popBrandAdapter;
    private PopPriceAdapter popPriceAdapter;
    private ArrayList<String> listOne;
    private ArrayList<String> listTwo;

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
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(NumberRicalActivity.this);
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(NumberRicalActivity.this);
        linearLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager5 = new LinearLayoutManager(NumberRicalActivity.this);
        linearLayoutManager5.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc_levelattr.setLayoutManager(linearLayoutManager3);
        rc_levelbrand.setLayoutManager(linearLayoutManager4);
        rc_levelprice.setLayoutManager(linearLayoutManager5);
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
                ll_select_pop.setVisibility(View.GONE);
                oneAdapter.setClickPosition(position);
                level2BottomList.clear();
                for (int i = 0; i < level2List.size(); i++) {
                    if (level2List.get(i).getParentId() == oneAdapter.getItem(position).getId()) {
                        level2BottomList.add(level2List.get(i));
                    }
                }
                twoAdapter.cleanDates();
                twoAdapter.addAll(level2BottomList);
                findVerticalData(oneAdapter.getItem(position).getId(), 0, 0, "", 0);
            }
        });
        twoAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                twoAdapter.setClickPosition(position);
                ll_select_pop.setVisibility(View.GONE);
                findVerticalData(twoAdapter.getItem(position).getId(), 0, 0, "", 0);
            }
        });

        verticalAdapter = new NumberRicalVerticalAdapter(this);
        vetical.setAdapter(verticalAdapter);


        attrAdapter = new LevelAttrAdapter(this);
        rc_levelattr.setAdapter(attrAdapter);
        attrAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                attrAdapter.setClickPosition(position);
                ll_select_pop.setVisibility(View.VISIBLE);
                clickType = "attr";
                rc_select.setAdapter(popAttrAdapter);
                popAttrAdapter.cleanDates();
                popAttrAdapter.addAll(numberListInfo.getData().getFilter_attr().get(position).getItems());
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
                popSpecAdapter.addAll(numberListInfo.getData().getFilter_spec().get(position).getItems());

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
                popBrandAdapter.addAll(numberListInfo.getData().getFilter_brand());
            }
        });

        priceAdapter = new LevelPriceAdapter(this);
        rc_levelprice.setAdapter(priceAdapter);
        priceAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                priceAdapter.setClickPosition(position);
                ll_select_pop.setVisibility(View.VISIBLE);
                clickType = "price";
                rc_select.setAdapter(popPriceAdapter);
                popPriceAdapter.cleanDates();
                popPriceAdapter.addAll(numberListInfo.getData().getFilter_price());
            }
        });
        listOne = new ArrayList<>();
        listOne.add("品牌");
        listTwo = new ArrayList<>();
        listTwo.add("价格");
        brandAdapter.addAll(listOne);
        priceAdapter.addAll(listTwo);


        rc_select.setLayoutManager(new GridLayoutManager(this, 3));
        rc_select.addItemDecoration(new SpaceItemDecoration(25));
        popAttrAdapter = new PopAttrAdapter(this);
        popSpecAdapter = new PopSpecAdapter(this);
        popBrandAdapter = new PopBrandAdapter(this);
        popPriceAdapter = new PopPriceAdapter(this);
        popAttrAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                popAttrAdapter.setClickPosition(position);
            }
        });
        popSpecAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                popSpecAdapter.setClickPosition(position);
            }
        });
        popBrandAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                popBrandAdapter.setClickPosition(position);
            }
        });
        popPriceAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                popPriceAdapter.setClickPosition(position);
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
        OkGoUtils okGoUtils = new OkGoUtils(NumberRicalActivity.this);
        okGoUtils.httpPostJSON(homeInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                NumberLevelInfo levelInfo = JSON.parseObject(response, NumberLevelInfo.class);
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
                    findVerticalData(levelId, specId, brandId, attrValue, pageIndex);
                }
            }
        });
    }

    private void findVerticalData(int levelId, int specId, int brandId, final String attrValue, int pageIndex) {
        VerticalReInfo verticalReInfo = new VerticalReInfo();
        verticalReInfo.setFunctionName("ListDigitalGoods");
        VerticalReInfo.ParametersBean parametersBean = new VerticalReInfo.ParametersBean();
        parametersBean.setCategoryId(levelId);
        if (specId != -1) {
            parametersBean.setSpecId(specId);
        }
        if (brandId != -1) {
            parametersBean.setBrandId(brandId);
        }
        parametersBean.setAttrValue(attrValue);
        parametersBean.setPageSize(6);
        parametersBean.setPageIndex(pageIndex);
        verticalReInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(NumberRicalActivity.this);
        okGoUtils.httpPostJSON(verticalReInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                numberListInfo = JSON.parseObject(response, NumberListInfo.class);
                if (numberListInfo.getCode() == 0) {
                    if (numberListInfo.getData().getFilter_attr().size() > 0) {
                        attrAdapter.cleanDates();
                        attrAdapter.addAll(numberListInfo.getData().getFilter_attr());
                    } else {
                        attrAdapter.cleanDates();
                    }
                    if (numberListInfo.getData().getFilter_spec().size() > 0) {
                        threeAdapter.cleanDates();
                        threeAdapter.addAll(numberListInfo.getData().getFilter_spec());
                    } else {
                        threeAdapter.cleanDates();
                    }
                    if (numberListInfo.getData().getFilter_price().size() == 0) {
                        priceAdapter.cleanDates();
                    }else{
                        priceAdapter.cleanDates();
                        priceAdapter.addAll(listTwo);
                    }
                    if (numberListInfo.getData().getFilter_brand().size() == 0) {
                        brandAdapter.cleanDates();
                    }else{
                        brandAdapter.cleanDates();
                        brandAdapter.addAll(listOne);
                    }
                    verticalAdapter.cleanDates();
                    if (numberListInfo.getData().getGoods_list().getList().size() > 0) {
                        iv_nodate.setVisibility(View.GONE);
                        vetical.setVisibility(View.VISIBLE);
                        verticalAdapter.addAll(numberListInfo.getData().getGoods_list().getList());
                    } else {
                        iv_nodate.setVisibility(View.VISIBLE);
                        vetical.setVisibility(View.GONE);
                    }
                }
            }
        });

    }

    @OnClick({R.id.iv_up, R.id.iv_shopcar, R.id.bt_close, R.id.bt_sure})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_close:
                ll_select_pop.setVisibility(View.GONE);
                if (twoAdapter.getClickPosition() != -1) {
                    levelId = twoAdapter.getItem(twoAdapter.getClickPosition()).getId();
                } else {
                    levelId = oneAdapter.getItem(oneAdapter.getClickPosition()).getId();
                }
                findVerticalData(levelId, 0, 0, "", 0);
                break;
            case R.id.bt_sure:
                pageIndex = 0;
                ll_select_pop.setVisibility(View.GONE);
                if (twoAdapter.getClickPosition() != -1) {
                    levelId = twoAdapter.getItem(twoAdapter.getClickPosition()).getId();
                } else {
                    levelId = oneAdapter.getItem(oneAdapter.getClickPosition()).getId();
                }
                switch (clickType) {
                    case "attr":
                        if (popSpecAdapter.getClickPosition() != -1) {
                            specId = numberListInfo.getData().getFilter_spec().get(threeAdapter.getClickPosition()).getItems().get(popSpecAdapter.getClickPosition()).getSpecId();
                        }
                        if (popBrandAdapter.getClickPosition() != -1) {
                            brandId = numberListInfo.getData().getFilter_brand().get(popBrandAdapter.getClickPosition()).getId();
                        }
                        if (popAttrAdapter.getClickPosition() != -1) {
                            attrValue = numberListInfo.getData().getFilter_attr().get(attrAdapter.getClickPosition()).getItems().get(popAttrAdapter.getClickPosition()).getAttrId() + "_" + numberListInfo.getData().getFilter_attr().get(attrAdapter.getClickPosition()).getItems().get(popAttrAdapter.getClickPosition()).getAttrValue();
                            findVerticalData(levelId, specId, brandId, attrValue, pageIndex);
                        }

                        break;
                    case "spec":
                        if (popBrandAdapter.getClickPosition() != -1) {
                            brandId = numberListInfo.getData().getFilter_brand().get(popBrandAdapter.getClickPosition()).getId();
                        }
                        if (popAttrAdapter.getClickPosition() != -1) {
                            attrValue = numberListInfo.getData().getFilter_attr().get(attrAdapter.getClickPosition()).getItems().get(popAttrAdapter.getClickPosition()).getAttrId() + "_" + numberListInfo.getData().getFilter_attr().get(attrAdapter.getClickPosition()).getItems().get(popAttrAdapter.getClickPosition()).getAttrValue();
                        }
                        if (popSpecAdapter.getClickPosition() != -1) {
                            specId = numberListInfo.getData().getFilter_spec().get(threeAdapter.getClickPosition()).getItems().get(popSpecAdapter.getClickPosition()).getSpecId();
                            findVerticalData(levelId, specId, brandId, attrValue, pageIndex);
                        }
                        break;
                    case "brand":

                        if (popAttrAdapter.getClickPosition() != -1) {
                            attrValue = numberListInfo.getData().getFilter_attr().get(attrAdapter.getClickPosition()).getItems().get(popAttrAdapter.getClickPosition()).getAttrId() + "_" + numberListInfo.getData().getFilter_attr().get(attrAdapter.getClickPosition()).getItems().get(popAttrAdapter.getClickPosition()).getAttrValue();
                        }
                        if (popSpecAdapter.getClickPosition() != -1) {
                            specId = numberListInfo.getData().getFilter_spec().get(threeAdapter.getClickPosition()).getItems().get(popSpecAdapter.getClickPosition()).getSpecId();
                        }
                        if (popBrandAdapter.getClickPosition() != -1) {
                            brandId = numberListInfo.getData().getFilter_brand().get(popBrandAdapter.getClickPosition()).getId();
                            findVerticalData(levelId, specId, brandId, attrValue, pageIndex);
                        }
                        break;
                    case "price":
                        break;
                }
                break;
            case R.id.iv_up:
//                scrollview.scrollTo(0, 0);
                break;
            case R.id.iv_shopcar:
                ZeroZeroSevenUtils.SwitchActivity(NumberRicalActivity.this, NumberRicalShopCarActivity.class);
                break;
        }
    }
}
