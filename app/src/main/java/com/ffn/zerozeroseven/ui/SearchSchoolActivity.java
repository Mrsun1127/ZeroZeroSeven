package com.ffn.zerozeroseven.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.CityAdapter;
import com.ffn.zerozeroseven.adapter.SchoolListAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.SchoolJsonInfo;
import com.ffn.zerozeroseven.bean.SchoolListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.IdSearchInfo;
import com.ffn.zerozeroseven.fragment.MainFragment;
import com.ffn.zerozeroseven.fragment.ShopFragment;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.Util;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/22.
 */

public class SearchSchoolActivity extends BaseActivity implements View.OnClickListener {
    private String[] mItems;
    private SchoolJsonInfo jsonInfo;
    private TextView tv_select_shen;
    private RecyclerView rc_city;
    private RecyclerView rc_school;
    private RelativeLayout rl_nodata;
    private CityAdapter cityAdapter;
    private RelativeLayout et_top;
    private SchoolListAdapter schoolListAdapter;
    //    public LocationClient mLocationClient = null;
    //    private MyLocationListener myListener = new MyLocationListener();
//    private double latitude;
//    private double longitude;
//    private PoiSearch mPoiSearch;
//    private WaitingDialog waitingDialog1;
    private List<SchoolListInfo.DataBean.SchoolsBean> schools;
    public static WeakReference<SearchSchoolActivity> inStance;
    private Spinner spCity;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_searchschool;
    }

    @Override
    protected void doMain() {
        MobclickAgent.onEvent(this, "选择学校");
        inStance = new WeakReference<SearchSchoolActivity>(this);
        initDate();
    }

    private void initDate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//如果 API level 是大于等于 23(Android 6.0) 时
            //判断是否具有权限
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //判断是否需要向用户解释为什么需要申请该权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    Toast.makeText(this, "自Android 6.0开始需要打开位置权限", Toast.LENGTH_SHORT).show();
                }
                //请求权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            }
        }
//        waitingDialog1=new WaitingDialog(this);
//        doGoLocation();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.et_top:
                ZeroZeroSevenUtils.SwitchActivity(SearchSchoolActivity.this, SeachSchoolListActivity.class);
                break;

        }
    }

    @Override
    public void initView() {
        et_top = findViewById(R.id.et_top);
        et_top.setOnClickListener(this);
        findViewById(R.id.rl_back).setOnClickListener(this);
        spCity = findViewById(R.id.sp_city);
        tv_select_shen = findViewById(R.id.tv_select_shen);
        rc_city = findViewById(R.id.rc_city);
        rl_nodata = findViewById(R.id.rl_nodata);
        rc_city.setLayoutManager(new LinearLayoutManager(SearchSchoolActivity.this));
        cityAdapter = new CityAdapter(SearchSchoolActivity.this);
        rc_city.setAdapter(cityAdapter);
        cityAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                cityAdapter.setClickPosition(position);
                rl_nodata.setVisibility(View.GONE);
                rc_school.setVisibility(View.GONE);
                String id = cityAdapter.getItem(position).getId();
                showSchoolList(id);

            }
        });

        rc_school = findViewById(R.id.rc_school);
        rc_school.setLayoutManager(new LinearLayoutManager(SearchSchoolActivity.this));
        schoolListAdapter = new SchoolListAdapter(SearchSchoolActivity.this);
        rc_school.setAdapter(schoolListAdapter);
        schoolListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                schoolListAdapter.setClickPosition(position);
                if (schools.size() > 0) {
                    userInfo.setSchoolName(schoolListAdapter.getItem(position).getName());
                    userInfo.setSchoolId(schoolListAdapter.getItem(position).getId() + "");
                    BaseAppApplication.getInstance().setLoginUser(userInfo);
                    SharePrefUtils.saveObject(SearchSchoolActivity.this, "userInfo", userInfo);
                    SharePrefUtils.saveObject(SearchSchoolActivity.this, "carShopInfo", null);
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
        MyFristTask myFristTask = new MyFristTask();
        myFristTask.execute("");

        // 建立Adapter并且绑定数据源
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spCity.setAdapter(arrayAdapter);
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                tv_select_shen.setText(mItems[pos]);
                List<SchoolJsonInfo.PlacesBean.ChildrenBeanX> children = jsonInfo.getPlaces().get(pos).getChildren();
                cityAdapter.cleanDates();
                cityAdapter.addAll(children);
                if (children.size() > 0) {
                    cityAdapter.setClickPosition(0);
                    showSchoolList(children.get(0).getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private class MyFristTask extends AsyncTask<String, Object, Object> {

        @Override
        protected Object doInBackground(String... strings) {
            jsonInfo = JSON.parseObject(Util.getTextFromAssets(BaseAppApplication.context, "cityone.json"), SchoolJsonInfo.class);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoadProgress();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            disLoadProgress();
            mItems = new String[jsonInfo.getPlaces().size()];
            for (int i = 0; i < jsonInfo.getPlaces().size(); i++) {
                mItems[i] = jsonInfo.getPlaces().get(i).getName();
            }
            arrayAdapter.addAll(mItems);
            spCity.setSelection(17);
        }
    }

    private void showSchoolList(String id) {
        showLoadProgress();
        IdSearchInfo idSearchInfo = new IdSearchInfo();
        idSearchInfo.setFunctionName("ListSchool");
        IdSearchInfo.ParametersBean parametersBean = new IdSearchInfo.ParametersBean();
        parametersBean.setCity(id);
        idSearchInfo.setParameters(parametersBean);
        httpPostJSON(idSearchInfo);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        rc_school.setVisibility(View.GONE);
                        rl_nodata.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final SchoolListInfo schoolListInfo = JSON.parseObject(response.body().string(), SchoolListInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        if (schoolListInfo.getCode() == 0 && schoolListInfo.getData() != null) {
                            schools = schoolListInfo.getData().getSchools();
                            if (schools.size() > 0) {
                                schoolListAdapter.setClickPosition(0);
                                schoolListAdapter.cleanDates();
                                schoolListAdapter.addAll(schools);
                                rc_school.setVisibility(View.VISIBLE);
                                rl_nodata.setVisibility(View.GONE);
                            } else {
                                rc_school.setVisibility(View.GONE);
                                rl_nodata.setVisibility(View.VISIBLE);
                            }
                        } else {
                            rc_school.setVisibility(View.GONE);
                            rl_nodata.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
