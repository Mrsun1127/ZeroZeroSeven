package com.ffn.zerozeroseven.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.RequestRunnerInfo;
import com.ffn.zerozeroseven.bean.RunnerCountInfo;
import com.ffn.zerozeroseven.bean.RunnerInfo;
import com.ffn.zerozeroseven.bean.RunnerListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RQiangDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RrunnerCountInfo;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;
import com.ffn.zerozeroseven.ui.ErrandHomeActivity;
import com.ffn.zerozeroseven.ui.PeopleMessAgeActivity;
import com.ffn.zerozeroseven.ui.RenzhengStatusActivity;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ScreenUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;
import com.ffn.zerozeroseven.view.StateLayout;
import com.willy.ratingbar.ScaleRatingBar;

import java.lang.ref.WeakReference;
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
    public static WeakReference<ErrandMineRunFragment> mInstance;

    public static ErrandMineRunFragment newInstance() {
        return new ErrandMineRunFragment();
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        mInstance = new WeakReference<>(this);
        recycleview.setLayoutManager(new FullyLinearLayoutManager(bfCxt));
        errandMineRunAdapter = new ErrandMineRunAdapter(bfCxt);
        recycleview.setAdapter(errandMineRunAdapter);
        errandMineRunAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                showTypeDialog(errandMineRunAdapter.getItem(position));
            }
        });
        common_stateLayout.setOnStateCallListener(new StateLayout.OnStateLayoutCallListener() {
            @Override
            public void reCall() {
                common_stateLayout.setVisibility(View.GONE);
                requestList();
            }
        });
    }

    @Override
    public void initDate() {
        requestData();
        requestList();
        requestCount();
    }

    @Bind(R.id.tv_running_count)
    TextView tv_running_count;
    @Bind(R.id.tv_runfinishcount)
    TextView tv_runfinishcount;
    @Bind(R.id.tv_runmoney)
    TextView tv_runmoney;
    @Bind(R.id.simpleRatingBar)
    ScaleRatingBar scaleRatingBar;
    @Bind(R.id.common_stateLayout)
    StateLayout common_stateLayout;

    private void requestCount() {
        RrunnerCountInfo rrunnerCountInfo = new RrunnerCountInfo();
        rrunnerCountInfo.setFunctionName("QueryErrandUserDailyStatistics");
        RrunnerCountInfo.ParametersBean parametersBean = new RrunnerCountInfo.ParametersBean();
        parametersBean.setUserId(userId);
        rrunnerCountInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(rrunnerCountInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                RunnerCountInfo runnerCountInfo = JSON.parseObject(response, RunnerCountInfo.class);
                if (runnerCountInfo.getCode() == 0) {
                    tv_running_count.setText(String.valueOf(runnerCountInfo.getData().getReceiveOrderCount()));
                    tv_runfinishcount.setText(String.valueOf(runnerCountInfo.getData().getHaveOrderCount()));
                    tv_runmoney.setText(runnerCountInfo.getData().getIncome() + "元");
                } else {
                    ToastUtils.showShort(runnerCountInfo.getMessage());
                }
            }
        });
    }

    private void requestList() {
        RequestRunnerInfo requestRunnerInfo = new RequestRunnerInfo();
        requestRunnerInfo.setFunctionName("ListSchoolErrandOrder");
        RequestRunnerInfo.ParametersBean parametersBean = new RequestRunnerInfo.ParametersBean();
        parametersBean.setUserPhone(userInfo.getPhone());
        parametersBean.setSchoolId(schoolIId);
        requestRunnerInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(requestRunnerInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrandHomeActivity.mInstance.get().refreshlayout.finishRefresh();
                RunnerListInfo runnerListInfo = JSON.parseObject(response, RunnerListInfo.class);
                if (runnerListInfo.getCode() == 0) {
                    if (runnerListInfo.getData().getErrandOrders() != null) {
                        if (runnerListInfo.getData().getErrandOrders().size() > 0) {
                            recycleview.setVisibility(View.VISIBLE);
                            common_stateLayout.setVisibility(View.GONE);
                            errandMineRunAdapter.cleanDates();
                            errandMineRunAdapter.addAll(runnerListInfo.getData().getErrandOrders());
                        } else {
                            recycleview.setVisibility(View.GONE);
                            errandMineRunAdapter.cleanDates();
                            showErrorLayout(StateLayout.noData);
                        }
                    } else {
                        recycleview.setVisibility(View.GONE);
                        showErrorLayout(StateLayout.noData);
                    }
                }
            }
        });
    }

    private void showErrorLayout(int errType) {
        common_stateLayout.setVisibility(View.VISIBLE);
        common_stateLayout.showError(errType);
    }

    public void requestData() {
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
                        if (!TextUtils.isEmpty(runnerInfo.getData().getPhone())) {
                            ll_audit.setVisibility(View.GONE);
                            ll_verifile.setVisibility(View.VISIBLE);
                            Glide.with(bfCxt).load(runnerInfo.getData().getAvatar()).override(ScreenUtils.getScreenWidth() / 6, ScreenUtils.getScreenWidth() / 6).into(clv_icon);
                            tv_name.setText(runnerInfo.getData().getRealName());
                            tv_school.setText(runnerInfo.getData().getSchoolName());
                            tv_phone.setText(runnerInfo.getData().getPhone());
                            tv_satisficing.setText(runnerInfo.getData().getStarLevel() + "星级");
                            scaleRatingBar.setRating(runnerInfo.getData().getStarLevel());
                        } else {
                            ll_audit.setVisibility(View.VISIBLE);
                            ll_verifile.setVisibility(View.GONE);
                        }

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
        okGoUtils.httpPostJSON(qiangDanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    ToastUtils.showShort("抢单成功,请到右上角订单点击查看");
                    requestList();
                } else {
                    ToastUtils.showShort(errorCodeInfo.getMessage());
                }
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.errand_fragment_minerun;
    }

    @OnClick({R.id.iv_audit, R.id.ll_verifile})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.iv_audit:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, ErrandAuitActivity.class);
                break;
            case R.id.ll_verifile:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, RenzhengStatusActivity.class);
                break;
        }
    }

    @Override
    protected void lazyLoad() {

    }
}
