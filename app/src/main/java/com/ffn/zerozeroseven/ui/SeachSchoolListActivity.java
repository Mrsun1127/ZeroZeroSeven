package com.ffn.zerozeroseven.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.SchoolLikeAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.SchoolLikeListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.IdSearchInfo;
import com.ffn.zerozeroseven.fragment.MainFragment;
import com.ffn.zerozeroseven.fragment.ShopFragment;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ClearEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/12/2.
 */

public class SeachSchoolListActivity extends BaseActivity {

    private RecyclerView rc_school;
    private ClearEditText ct_school;
    private SchoolLikeAdapter adapter;
    private RelativeLayout rl_back;
    private RelativeLayout rl_nodata;
    private String json;

    @Override
    protected int setLayout() {
        return R.layout.activity_seachschoollist;
    }

    @Override
    protected void doMain() {
        requestAllSchoolData();
    }

    private void requestAllSchoolData() {
        IdSearchInfo searchInfo = new IdSearchInfo();
        searchInfo.setFunctionName("ListSchool");
        OkGoUtils okGoUtils = new OkGoUtils(SeachSchoolListActivity.this);
        okGoUtils.httpPostJSON(searchInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                SchoolLikeListInfo info = JSON.parseObject(response, SchoolLikeListInfo.class);
                if (info.getCode() == 0 && info.getData() != null) {
                    final SchoolLikeListInfo.DataBean data = info.getData();
                    json = JSON.toJSONString(data);
                    adapter.addAll(data.getSchools());
                }
            }
        });

    }

    @Override
    public void initView() {
        rl_back = findViewById(R.id.rl_back);
        rl_nodata = findViewById(R.id.rl_nodata);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rc_school = findViewById(R.id.rc_school);
        rc_school.setLayoutManager(new LinearLayoutManager(SeachSchoolListActivity.this));
        adapter = new SchoolLikeAdapter(SeachSchoolListActivity.this);
        rc_school.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                if (!TextUtils.isEmpty(getIntent().getStringExtra("errand"))) {
                    Intent intent = new Intent();
                    intent.putExtra("school", "" + adapter.getItem(position).getName());
                    intent.putExtra("id", "" + adapter.getItem(position).getId());
                    setResult(3, intent);
                    finish();
                } else {
                    userInfo.setSchoolName(adapter.getItem(position).getName());
                    userInfo.setSchoolId(adapter.getItem(position).getId() + "");
                    BaseAppApplication.getInstance().setLoginUser(userInfo);
                    CarShopInfo carShopInfo = BaseAppApplication.getInstance().getCarShopInfo();
                    carShopInfo.getShopInfos().clear();
                    BaseAppApplication.getInstance().setCarShopInfo(carShopInfo);
                    SharePrefUtils.saveObject(SeachSchoolListActivity.this, "userInfo", userInfo);
                    SharePrefUtils.saveObject(SeachSchoolListActivity.this, "carShopInfo", carShopInfo);
                    SearchSchoolActivity.inStance.get().finish();
                    finish();
                    MainFragment.mInstance.get().reQuest();
                    try {
                        ShopFragment.mInstance.get().initTabs();
                        ShopFragment.mInstance.get().getshangchangInfo();
                    } catch (Exception e) {

                    }
                }

            }
        });
        ct_school = findViewById(R.id.ct_school);
        ct_school.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i0, int i1, int i2) {
                if (TextUtils.isEmpty(json)) {
                    ZeroZeroSevenUtils.showCustonPop(SeachSchoolListActivity.this, "网络连接异常，请稍后再试", ct_school);
                } else {
                    SearCher();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void SearCher() {
        String searchName = ct_school.getText().toString().trim();
        char[] chars = searchName.toCharArray();
        SchoolLikeListInfo.DataBean dataBean = JSON.parseObject(json, SchoolLikeListInfo.DataBean.class);
        List<SchoolLikeListInfo.DataBean.SchoolsBean> showList = new ArrayList();
        for (int i = 0; i < dataBean.getSchools().size(); i++) {
            boolean isAdd = true;
            for (int i1 = 0; i1 < chars.length; i1++) {
                if (!dataBean.getSchools().get(i).getName().contains(chars[i1] + "")) {
                    isAdd = false;
                }
            }
            if (isAdd) {
                showList.add(dataBean.getSchools().get(i));
            }
        }
        if (adapter != null) {
            if (showList.size() > 0) {
                rl_nodata.setVisibility(View.GONE);
                rc_school.setVisibility(View.VISIBLE);
                adapter.cleanDates();
                adapter.addAll(showList);
            } else {
                rl_nodata.setVisibility(View.VISIBLE);
                rc_school.setVisibility(View.GONE);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
