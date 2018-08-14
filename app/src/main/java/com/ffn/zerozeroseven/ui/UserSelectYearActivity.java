package com.ffn.zerozeroseven.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.SlectYearAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.RselectSchoolInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserSelectYearActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.recycleview)
    RecyclerView recyclerView;
    private List<String> stringList = new ArrayList<>();
    private String schoolName;
    private String schoolId;
    private SlectYearAdapter slectYearAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_selectyear;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("选择入学年份");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        schoolName = getIntent().getStringExtra("schoolName");
        schoolId = getIntent().getStringExtra("schoolId");
        stringList.add("2019");
        stringList.add("2018");
        stringList.add("2017");
        stringList.add("2016");
        stringList.add("2015");
        stringList.add("2014");
        stringList.add("2013");
        stringList.add("2012");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        slectYearAdapter = new SlectYearAdapter(this);
        recyclerView.setAdapter(slectYearAdapter);
        slectYearAdapter.addAll(stringList);
        slectYearAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                sureGo(position);
            }
        });
    }

    private void sureGo(final int position) {
        RselectSchoolInfo rselectSchoolInfo = new RselectSchoolInfo();
        rselectSchoolInfo.setFunctionName("UpdateUserSchool");
        RselectSchoolInfo.ParametersBean parametersBean = new RselectSchoolInfo.ParametersBean();
        parametersBean.setSchoolId(schoolId);
        parametersBean.setEnrollmentYear(slectYearAdapter.getItem(position));
        parametersBean.setUserId(userId);
        rselectSchoolInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(UserSelectYearActivity.this);
        okGoUtils.httpPostJSON(rselectSchoolInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    userInfo.setSchoolId(schoolId);
                    userInfo.setLocationSchoolId(schoolId);
                    userInfo.setSchoolName(schoolName);
                    userInfo.setEnrollmentYear(slectYearAdapter.getItem(position));
                    BaseAppApplication.getInstance().setLoginUser(userInfo);
                    SharePrefUtils.saveObject(UserSelectYearActivity.this, "userInfo", userInfo);
                    ZeroZeroSevenUtils.SwitchActivity(UserSelectYearActivity.this, HomeActivity.class);
                } else {
                    ToastUtils.showShort(errorCodeInfo.getMessage());
                }
            }
        });
    }

    @Override
    protected void doMain() {

    }
}
