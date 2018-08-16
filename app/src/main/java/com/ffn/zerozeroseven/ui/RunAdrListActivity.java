package com.ffn.zerozeroseven.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.RunAdrListAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseUpdateRefreshActivity;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.FaHuoDiZhiInfo;
import com.ffn.zerozeroseven.bean.RFaHuoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RAddRunAdrInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RdeleteRunAdrInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;

public class RunAdrListActivity extends BaseUpdateRefreshActivity {

    private RunAdrListAdapter adrListAdapter;
    private RFaHuoInfo rFaHuoInfo;
    private String type;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        type = getIntent().getStringExtra("type");
        adrListAdapter = new RunAdrListAdapter(this);
        adrListAdapter.setOnItemLongClickListener(new BaseRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(final int position, long itemId) {
                final ConfirmDialog confirmDialog = new ConfirmDialog(RunAdrListActivity.this);
                confirmDialog.setMessages("确认删除地址");
                confirmDialog.setTitles("提示");
                confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        confirmDialog.dismiss();
                        deleteAdr(position);
                    }

                    @Override
                    public void doCancel() {
                        confirmDialog.dismiss();
                    }
                });
            }
        });
        adrListAdapter.setOnItemImageViewClick(new RunAdrListAdapter.OnItemImageClick() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("saveType", "update");
                bundle.putString("jumpType", type);
                bundle.putSerializable("info", adrListAdapter.getItem(position));
                ZeroZeroSevenUtils.SwitchActivity(RunAdrListActivity.this, RunAdrUpdateActivity.class, bundle);
            }
        });
        adrListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                RAddRunAdrInfo rAddRunAdrInfo = new RAddRunAdrInfo();
                RAddRunAdrInfo.ParametersBean parametersBean = new RAddRunAdrInfo.ParametersBean();
                parametersBean.setAddress(adrListAdapter.getItem(position).getAddress());
                parametersBean.setName(adrListAdapter.getItem(position).getName());
                parametersBean.setPhone(adrListAdapter.getItem(position).getPhone());
                parametersBean.setUserId(userId);
                if ("f".equals(type)) {
                    parametersBean.setType("SEND");
                } else {
                    parametersBean.setType("RECEIVE");
                }
                rAddRunAdrInfo.setParameters(parametersBean);
                Intent intent = new Intent();
                intent.putExtra("adrInfo", rAddRunAdrInfo);
                setResult(2, intent);
                finish();
            }
        });
        return adrListAdapter;
    }

    private void deleteAdr(int position) {
        RdeleteRunAdrInfo rdeleteRunAdrInfo = new RdeleteRunAdrInfo();
        rdeleteRunAdrInfo.setFunctionName("DeleteErrandUserAddress");
        RdeleteRunAdrInfo.ParametersBean parametersBean = new RdeleteRunAdrInfo.ParametersBean();
        parametersBean.setId(String.valueOf(adrListAdapter.getItem(position).getId()));
        rdeleteRunAdrInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(RunAdrListActivity.this);
        okGoUtils.httpPostJSON(rdeleteRunAdrInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    requestData();
                } else {
                    ToastUtils.showShort(errorCodeInfo.getMessage());
                }
            }
        });
    }

    @Override
    protected void doMain() {
    }

    @Override
    public void doRight() {
        Bundle bundle = new Bundle();
        bundle.putString("saveType", "add");
        bundle.putString("jumpType", type);
        ZeroZeroSevenUtils.SwitchActivity(this, RunAdrUpdateActivity.class, bundle);
    }

    @Override
    protected String setTopTitle() {
        titleView.setTvRightText("新增");
        return "选择地址";
    }

    @Override
    protected void readRespones(String response) {
        rFaHuoInfo = JSON.parseObject(response, RFaHuoInfo.class);
    }

    @Override
    protected int setFlag() {
        return rFaHuoInfo.getCode();
    }

    @Override
    protected int setSize() {
        return rFaHuoInfo.getData().getList().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(rFaHuoInfo.getData().getList());
    }

    @Override
    protected Object setObj(int pageNo) {
        FaHuoDiZhiInfo faHuoDiZhiInfo = new FaHuoDiZhiInfo();
        faHuoDiZhiInfo.setFunctionName("ListErrandUserAddress");
        FaHuoDiZhiInfo.ParametersBean parametersBean = new FaHuoDiZhiInfo.ParametersBean();
        parametersBean.setUserId(userId);
        if ("s".equals(type)) {
            parametersBean.setType("RECEIVE");
        } else {
            parametersBean.setType("SEND");
        }
        faHuoDiZhiInfo.setParameters(parametersBean);
        return faHuoDiZhiInfo;
    }
}
