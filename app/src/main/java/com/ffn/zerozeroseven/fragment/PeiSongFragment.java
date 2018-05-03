package com.ffn.zerozeroseven.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.PeiSongAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.RgRefreshStatus;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.PeiSongShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.FinishInfo;
import com.ffn.zerozeroseven.bean.requsetbean.PeiSongInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.NetUtil;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.UiTipUtil;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.StateLayout;

import java.io.IOException;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.util.BGARefreshLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/12/5.
 */

@SuppressLint("ValidFragment")
public class PeiSongFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private BGARefreshLayout commonRefreshLayout;
    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;
    private StateLayout commonStateLayout;
    int id;
    private PeiSongAdapter adapter;
    private TextView tv_countstatus;
    public static PeiSongFragment newInstance(int id){
        Bundle args=new Bundle();
        args.putInt("id",id);
        PeiSongFragment fragment=new PeiSongFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id=getArguments().getInt("id");
    }

    private RecyclerView common_recyclerView;
    private final int REQUEST_CODE = 0x1001;
    private void setRefreshLayoutVis() {
        if (commonRefreshLayout.getVisibility() == View.GONE) {
            commonRefreshLayout.setVisibility(View.VISIBLE);
            commonStateLayout.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initView(View view) {
        common_recyclerView = view.findViewById(R.id.common_recyclerView);
        common_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        common_recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        tv_countstatus = view.findViewById(R.id.tv_countstatus);
        tv_countstatus.setVisibility(View.VISIBLE);
        commonStateLayout = view.findViewById(R.id.common_stateLayout);
        commonRefreshLayout = view.findViewById(R.id.common_refreshLayout);
        commonRefreshLayout.setDelegate(this);
        commonRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(bfCxt, true));
        adapter = new PeiSongAdapter(getContext());
        adapter.setOnItemImageViewClick(new PeiSongAdapter.OnItemImageClick() {
            @Override
            public void onClick(View view, int position) {
                finishorder(position);
            }
        });
        common_recyclerView.setAdapter(adapter);
        commonStateLayout.setOnStateCallListener(new StateLayout.OnStateLayoutCallListener() {
            @Override
            public void reCall() {
                commonStateLayout.setVisibility(View.GONE);
                commonRefreshLayout.setVisibility(View.VISIBLE);
                rgRefreshStatus = RgRefreshStatus.IDLE;
                requestData();
            }
        });

        adapter.setOnItemCallViewClick(new PeiSongAdapter.OnItemCallClick() {
            @Override
            public void onClick(View view, int position) {
                if (Build.VERSION.SDK_INT >= 23) {

                    //判断有没有拨打电话权限
                    if (PermissionChecker.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        //请求拨打电话权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);

                    } else {
                        callPhone(adapter.getItem(position).getReceiverPhone());
                    }

                } else {
                    callPhone(adapter.getItem(position).getReceiverPhone());
                }
            }
        });
    }

    private void finishorder(final int position) {
        showLoadProgress();
        FinishInfo finishInfo=new FinishInfo();
        finishInfo.setFunctionName("CourierFinishOrder");
        FinishInfo.ParametersBean parametersBean=new FinishInfo.ParametersBean();
        parametersBean.setCourierId(id);
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        try {
            if(TextUtils.isEmpty(adapter.getItem(position).getId()+"")){
                ToastUtils.showShort("请重新刷新列表数据");
                return;
            }
        }catch (Exception e){
            ToastUtils.showShort("请重新刷新列表数据");
        }
        parametersBean.setOrderId(adapter.getItem(position).getId());
        finishInfo.setParameters(parametersBean);
        httpPostJSON(finishInfo,true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    final ErrorCodeInfo errorCodeInfo=JSON.parseObject(response.body().string(),ErrorCodeInfo.class);
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            disLoadProgress();
                            if(errorCodeInfo.getCode()==0){
                                adapter.removeItem(adapter.getItem(position));
                                BaseAppApplication.mainHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.notifyDataSetChanged();
                                    }
                                },1000);
                            }else{
                                ToastUtils.showShort(errorCodeInfo.getMessage());
                            }
                        }
                    });
                }catch (Exception e){
                    ToastUtils.showShort("服务器异常请稍后再试");
                }
            }
        });
    }
    private void callPhone(String phoneNum) {
        //直接拨号
        Uri uri = Uri.parse("tel:" + phoneNum);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        //此处不判断就会报错
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }
    @Override
    public void initDate() {
        setRefreshLayoutVis();
        requestData();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && PermissionChecker.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            ToastUtils.showShort("授权成功,请重新拨打电话");
        }
    }

    private void disLoadState() {
        switch (rgRefreshStatus) {
            case IDLE:
                disLoadProgress();
                break;
            case REFRESHING:
                commonRefreshLayout.endRefreshing();
                disLoadProgress();
                break;
            case PULL_DOWN:
                commonRefreshLayout.endLoadingMore();
                disLoadProgress();
                break;
        }
    }

    int pageNo = 0;

    private void setLoadPage() {
        switch (rgRefreshStatus) {
            case PULL_DOWN:
                pageNo = pageNo + 1;
                break;
            case IDLE:
                showLoadProgress();
            case REFRESHING:
                pageNo = 0;
                break;
        }
    }

    private void showErrorLayout(int errType) {
        commonRefreshLayout.setVisibility(View.GONE);
        adapter.clear();
        commonStateLayout.setVisibility(View.VISIBLE);
        commonStateLayout.showError(errType);
    }

    private void requestData() {
        setLoadPage();
        PeiSongInfo peiSongInfo = new PeiSongInfo();
        peiSongInfo.setFunctionName("ListCourierGoodsOrder");
        PeiSongInfo.ParametersBean parametersBean = new PeiSongInfo.ParametersBean();
        parametersBean.setCourierId(id);
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(5);
        parametersBean.setStatus("2");
        peiSongInfo.setParameters(parametersBean);
        httpPostJSON(peiSongInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showErrorLayout(StateLayout.netError);
                        disLoadState();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final PeiSongShowInfo showInfo = JSON.parseObject(response.body().string(), PeiSongShowInfo.class);
                LogUtils.E("showInfo",JSON.toJSONString(showInfo));
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadState();
                        if (showInfo.getCode() == 0) {
                            if(!TextUtils.isEmpty(showInfo.getData().getTotal()+"")){
                                tv_countstatus.setText("当前订单数量: "+showInfo.getData().getTotal());
                            }
                            List<PeiSongShowInfo.DataBean.ListBean> orders = showInfo.getData().getList();
                            switch (rgRefreshStatus) {
                                case IDLE:
                                case REFRESHING:
                                    adapter.clear();
                                    if (orders.size() == 0) {
                                        showErrorLayout(StateLayout.noData);
                                    } else {
                                        adapter.addAll(orders);
                                    }
                                    break;
                                case PULL_DOWN:
                                    if (orders.size() == 0) {
                                        UiTipUtil.showToast(bfCxt, R.string.no_more_data);
                                    } else {
                                        adapter.addAll(orders);
                                    }

                                    break;
                            }
                        }else{
                            showErrorLayout(StateLayout.noData);
                        }
                    }
                });
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_qiangdan;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onRefreshing(BGARefreshLayout refreshLayout) {
        if (!NetUtil.hasNetConnect(bfCxt)) {
            UiTipUtil.showToast(bfCxt, R.string.check_phone_net);
            refreshLayout.endRefreshing();
        } else {
            rgRefreshStatus = RgRefreshStatus.REFRESHING;
            requestData();
        }
    }

    @Override
    public boolean onLoadingMore(BGARefreshLayout refreshLayout) {
        if (!NetUtil.hasNetConnect(bfCxt)) {
            UiTipUtil.showToast(bfCxt, R.string.check_phone_net);
            BaseAppApplication.mainHandler.post(new Runnable() {
                @Override
                public void run() {
//                    handler.sendEmptyMessageDelayed(RgConstants.load_error_net, 500);
                    ToastUtils.showShort(R.string.check_phone_net + "");
                    commonRefreshLayout.endLoadingMore();
                }
            });
        } else {
            rgRefreshStatus = RgRefreshStatus.PULL_DOWN;
            requestData();
        }
        return true;
    }
}
