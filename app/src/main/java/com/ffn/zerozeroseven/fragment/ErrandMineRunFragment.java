package com.ffn.zerozeroseven.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ErrandMineRunAdapter;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.RequestRunnerInfo;
import com.ffn.zerozeroseven.bean.RunnerInfo;
import com.ffn.zerozeroseven.bean.RunnerListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RQiangDanInfo;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;
import com.ffn.zerozeroseven.ui.PeopleMessAgeActivity;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ErrandMineRunFragment extends BaseFragment {
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    @Bind(R.id.ll_audit)
    LinearLayout ll_audit;
    @Bind(R.id.ll_verifile)
    LinearLayout ll_verifile;
    @Bind(R.id.clv_icon)
    CircleImageView clv_icon;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_school)
    TextView tv_school;
    @Bind(R.id.tv_satisficing)
    TextView tv_satisficing;
    private ErrandMineRunAdapter errandMineRunAdapter;

    public static ErrandMineRunFragment newInstance() {
        return new ErrandMineRunFragment();
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        recycleview.setLayoutManager(new FullyLinearLayoutManager(bfCxt));
        errandMineRunAdapter = new ErrandMineRunAdapter(bfCxt);
        recycleview.setAdapter(errandMineRunAdapter);
        errandMineRunAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                showTypeDialog(errandMineRunAdapter.getItem(position));
            }
        });
    }

    @Override
    public void initDate() {
        requestData();
        requestList();
    }

    private void requestList() {
        RequestRunnerInfo requestRunnerInfo = new RequestRunnerInfo();
        requestRunnerInfo.setFunctionName("ListSchoolErrandOrder");
        RequestRunnerInfo.ParametersBean parametersBean = new RequestRunnerInfo.ParametersBean();
        parametersBean.setUserPhone(userInfo.getPhone());
        requestRunnerInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(requestRunnerInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                RunnerListInfo runnerListInfo = JSON.parseObject(response, RunnerListInfo.class);
                if (runnerListInfo.getCode() == 0) {
                    if (runnerListInfo.getData().getErrandOrders() != null) {
                        if (runnerListInfo.getData().getErrandOrders().size() > 0) {
                            errandMineRunAdapter.addAll(runnerListInfo.getData().getErrandOrders());
                        } else {
                            errandMineRunAdapter.cleanDates();
                        }
                    }
                }
            }
        });
    }

    private void requestData() {
        RequestRunnerInfo requestRunnerInfo = new RequestRunnerInfo();
        requestRunnerInfo.setFunctionName("QueryErrandUser");
        RequestRunnerInfo.ParametersBean parametersBean = new RequestRunnerInfo.ParametersBean();
        parametersBean.setUserPhone(userInfo.getPhone());
        requestRunnerInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(requestRunnerInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                RunnerInfo runnerInfo = JSON.parseObject(response, RunnerInfo.class);
                if (runnerInfo.getCode() == 0) {
                    if (runnerInfo.getData() != null) {
                        ll_audit.setVisibility(View.GONE);
                        ll_verifile.setVisibility(View.VISIBLE);
//                            Glide.with(bfCxt).load(runnerInfo.getData().getItem().getSex())
                        tv_name.setText(runnerInfo.getData().getRealName());
                        tv_phone.setText(runnerInfo.getData().getPhone());
                        tv_satisficing.setText(runnerInfo.getData().getStarLevel() + "星级");
                    } else {
                        ll_audit.setVisibility(View.VISIBLE);
                        ll_verifile.setVisibility(View.GONE);
                    }
                } else {
                    ToastUtils.showShort(runnerInfo.getMessage());
                }
            }
        });
    }

    private void showTypeDialog(final RunnerListInfo.DataBean.ErrandOrdersBean ordersBean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bfCxt);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(bfCxt, R.layout.pop_qiangdan, null);
        Button bt_left = view.findViewById(R.id.bt_left);
        Button bt_right = view.findViewById(R.id.bt_right);
        TextView tv_time = view.findViewById(R.id.tv_time);
        TextView tv_money = view.findViewById(R.id.tv_money);
        TextView tv_letadr = view.findViewById(R.id.tv_letadr);
        TextView tv_letinfo = view.findViewById(R.id.tv_letinfo);
        TextView tv_getadr = view.findViewById(R.id.tv_getadr);
        TextView tv_getinfo = view.findViewById(R.id.tv_getinfo);
        TextView tv_picktime = view.findViewById(R.id.tv_picktime);
        TextView tv_remark = view.findViewById(R.id.tv_remark);
        tv_time.setText(ordersBean.getCreateTime());
        tv_picktime.setText(ordersBean.getPickupTime());
        tv_remark.setText(ordersBean.getRemark());
        tv_money.setText(String.valueOf(ordersBean.getShippingFee()));
        tv_letadr.setText(ordersBean.getDeliveryAddress());
        tv_getadr.setText(ordersBean.getReceiverAddress());
        tv_letinfo.setText(ordersBean.getDeliveryName() + "  " + ordersBean.getDeliveryPhone());
        tv_getinfo.setText(ordersBean.getReceiverName() + "  " + ordersBean.getReceiverPhone());

        bt_left.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        bt_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                QiangDan(ordersBean.getOrderNo());
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private void QiangDan(String orderNo) {
        RQiangDanInfo qiangDanInfo = new RQiangDanInfo();
        qiangDanInfo.setFunctionName("GrabErrandOrder");
        RQiangDanInfo.ParametersBean parametersBean = new RQiangDanInfo.ParametersBean();
        parametersBean.setOrderNo(orderNo);
        parametersBean.setUserId(userId);
        qiangDanInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(qiangDanInfo,true,true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {

            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.errand_fragment_minerun;
    }

    @OnClick({R.id.iv_audit})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.iv_audit:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, ErrandAuitActivity.class);
                break;

        }
    }

    @Override
    protected void lazyLoad() {

    }
}
