package com.ffn.zerozeroseven.ui;

import android.Manifest;
import android.content.pm.PackageManager;
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
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
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
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.Util;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.WaitingDialog;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import me.wangyuwei.loadingview.LoadingView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/22.
 */

public class SearchSchoolActivity extends BaseActivity implements View.OnClickListener, OnGetPoiSearchResultListener {
    private String[] mItems;
    private SchoolJsonInfo jsonInfo;
    private TextView tv_select_shen;
    private RecyclerView rc_city;
    private RecyclerView rc_school;
    private RelativeLayout rl_nodata;
    private CityAdapter cityAdapter;
    private RelativeLayout et_top;
    private SchoolListAdapter schoolListAdapter;
    private LoadingView loadingView;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private double latitude;
    private double longitude;
    private PoiSearch mPoiSearch;
//    private WaitingDialog waitingDialog1;
    TextView tv_name;
    TextView tv_relocate;
    private List<SchoolListInfo.DataBean.SchoolsBean> schools;
    public static WeakReference<SearchSchoolActivity> inStance;

    @Override
    protected int setLayout() {
        return R.layout.activity_searchschool;
    }

    @Override
    protected void doMain() {
        BaseAppApplication.getInstance().addActivity(this);
        MobclickAgent.onEvent(this, "选择学校");
        inStance=new WeakReference<SearchSchoolActivity>(this);
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
        doGoLocation();

    }
    public void doGoLocation(){
        mLocationClient = new LocationClient(BaseAppApplication.context);
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedLocationPoiList(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        //可选，是否需要周边POI信息，默认为不需要，即参数为false
        //如果开发者需要获得周边POI信息，此处必须为true
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }
    @Override
    public void onGetPoiResult(final PoiResult poiResult) {
        if (poiResult != null) {
            if (poiResult.getAllPoi() != null && poiResult.getAllPoi().size() > 0) {
//                FindSchoolId(poiResult.getAllPoi().get(0).name);
                for (int i = 0; i < poiResult.getAllPoi().size(); i++) {
                    LogUtils.D("SearCherName",poiResult.getAllPoi().get(i).name+":::"+poiResult.getAllPoi().get(i).describeContents());

                }
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
//                        waitingDialog1.dismiss();
                        tv_name.setText(poiResult.getAllPoi().get(0).name.substring(0,poiResult.getAllPoi().get(0).name.indexOf("学")+1));
                    }
                });

            } else {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_name.setText("请手动定位");
//                        waitingDialog1.dismiss();
                    }
                });
            }
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取周边POI信息相关的结果
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            searchNeayBy(latitude, longitude);
        }
    }

    private void searchNeayBy(double latitude1, double longitude1) {
//        waitingDialog1.showInfo("正在获取学校定位中...");
        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption();
        nearbySearchOption.location(new LatLng(latitude1, longitude1));
        nearbySearchOption.keyword("大学");
        nearbySearchOption.radius(3000);
        nearbySearchOption.sortType(PoiSortType.distance_from_near_to_far);
        mPoiSearch.searchNearby(nearbySearchOption);
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
            case R.id.tv_relocate:
                LogUtils.D("SearchSchoolActivity","我走了点击方法");
                doGoLocation();
                break;
        }
    }

    @Override
    public void initView() {
        loadingView = findViewById(R.id.loadingView);
        et_top = findViewById(R.id.et_top);
        et_top.setOnClickListener(this);
        findViewById(R.id.rl_back).setOnClickListener(this);
        Spinner spCity = findViewById(R.id.sp_city);
        tv_select_shen = findViewById(R.id.tv_select_shen);
        tv_name = findViewById(R.id.tv_name);
        tv_relocate = findViewById(R.id.tv_relocate);
        tv_relocate.setOnClickListener(this);
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
                if(schools.size()>0){
                    userInfo.setSchoolName(schoolListAdapter.getItem(position).getFullName());
                    userInfo.setSchoolId(schoolListAdapter.getItem(position).getId()+"");
                    BaseAppApplication.getInstance().setLoginUser(userInfo);
                    tv_name.setText(schoolListAdapter.getItem(position).getFullName());
                    SharePrefUtils.saveObject(SearchSchoolActivity.this,"userInfo",userInfo);
                    SharePrefUtils.setInt(SearchSchoolActivity.this,"isLocation",1);
                    SharePrefUtils.saveObject(SearchSchoolActivity.this, "carShopInfo",null);
                    finish();
                    MainFragment.mInstance.reQuest();
                    try {
                        ShopFragment.mInstance.initTabs();
                    }catch (Exception e){

                    }
                }
            }
        });
        jsonInfo = JSON.parseObject(Util.getTextFromAssets(SearchSchoolActivity.this, "cityone.json"), SchoolJsonInfo.class);
        mItems = new String[jsonInfo.getPlaces().size()];
        for (int i = 0; i < jsonInfo.getPlaces().size(); i++) {
            mItems[i] = jsonInfo.getPlaces().get(i).getName();
        }
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spCity.setAdapter(adapter);
//        tv_select_shen.setText(mItems[17]);
//        List<SchoolJsonInfo.PlacesBean.ChildrenBeanX> children = jsonInfo.getPlaces().get(17).getChildren();
//        cityAdapter.cleanDates();
//        cityAdapter.addAll(children);
//        if (children.size() > 0) {
//            cityAdapter.setClickPosition(0);
//            showSchoolList(children.get(0).getId());
//        }
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
        spCity.setSelection(17);
//        cityAdapter.setClickPosition(17);
//        rl_nodata.setVisibility(View.GONE);
//        rc_school.setVisibility(View.GONE);
//        showSchoolList(430100+"");
    }

    private void showSchoolList(String id) {
        loadingView.setVisibility(View.VISIBLE);
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
                        loadingView.setVisibility(View.GONE);
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
                        loadingView.setVisibility(View.GONE);
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
//        myListener=null;
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
