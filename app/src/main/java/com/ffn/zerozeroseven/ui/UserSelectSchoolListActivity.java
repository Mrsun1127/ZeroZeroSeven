package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
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
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.SchoolLikeListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.IdSearchInfo;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ClearEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GT on 2017/12/2.
 */

public class UserSelectSchoolListActivity extends BaseActivity {

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
        json = MrsunAppCacheUtils.get(UserSelectSchoolListActivity.this).getAsString("allSchoolList");
        if (TextUtils.isEmpty(json)) {
            requestAllSchoolData();
        } else {
            try {
                SearCher();
            } catch (Exception e) {
            }
        }
    }

    private void requestAllSchoolData() {
        IdSearchInfo searchInfo = new IdSearchInfo();
        searchInfo.setFunctionName("ListSchool");
        OkGoUtils okGoUtils = new OkGoUtils(UserSelectSchoolListActivity.this);
        okGoUtils.httpPostJSON(searchInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                SchoolLikeListInfo info = JSON.parseObject(response, SchoolLikeListInfo.class);
                if (info.getCode() == 0 && info.getData() != null) {
                    final SchoolLikeListInfo.DataBean data = info.getData();
                    MrsunAppCacheUtils.get(UserSelectSchoolListActivity.this).put("allSchoolList", JSON.toJSONString(data));
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
        rc_school.setLayoutManager(new LinearLayoutManager(UserSelectSchoolListActivity.this));
        adapter = new SchoolLikeAdapter(UserSelectSchoolListActivity.this);
        rc_school.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putString("schoolId", adapter.getItem(position).getId() + "");
                bundle.putString("schoolName", adapter.getItem(position).getName() + "");
                ZeroZeroSevenUtils.SwitchActivity(UserSelectSchoolListActivity.this, UserSelectYearActivity.class, bundle);
                finish();
            }
        });
        ct_school = findViewById(R.id.ct_school);
        ct_school.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i0, int i1, int i2) {
                json = MrsunAppCacheUtils.get(UserSelectSchoolListActivity.this).getAsString("allSchoolList");
                if (TextUtils.isEmpty(json)) {
                    ZeroZeroSevenUtils.showCustonPop(UserSelectSchoolListActivity.this, "网络连接异常，请稍后再试", ct_school);
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
        String json = MrsunAppCacheUtils.get(UserSelectSchoolListActivity.this).getAsString("allSchoolList");
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
